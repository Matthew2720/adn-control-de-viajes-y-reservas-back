package com.ceiba.reservas.hotel;

import com.ceiba.reservas.core.BasePrueba;
import com.ceiba.reservas.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.reservas.habitacion.HabitacionTestDataBuilder;
import com.ceiba.reservas.vuelo.modelo.entidad.Ciudad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HotelTest {

    @Test
    void deberiaReconstruirCorrectamente(){
        var habitacion = new HabitacionTestDataBuilder()
                .conId(1L)
                .conNumeroHabitacion(101)
                .conDisponibilidad(true).reconstruir();

        var hotel = new HotelTestDataBuilder()
                .conId(1L)
                .conNombre("Hilton")
                .conUbicacion(Ciudad.BOGOTA)
                .conHabitacion(habitacion)
                .conCostoPorNoche(25000).reconstruir();

        Assertions.assertEquals(1L, hotel.getId());
        Assertions.assertEquals("Hilton", hotel.getNombre());
        Assertions.assertEquals(Ciudad.BOGOTA, hotel.getUbicacion());
        Assertions.assertEquals(habitacion, hotel.getHabitaciones().get(0));
        Assertions.assertEquals(25000, hotel.getCostoPorNoche());
    }

    @Test
    void deberiaLanzarErrorConCostoNegativo(){

        var habitacion = new HabitacionTestDataBuilder()
                .conId(1L)
                .conNumeroHabitacion(101)
                .conDisponibilidad(true).reconstruir();

        BasePrueba.assertThrows(() -> new HotelTestDataBuilder()
                .conId(1L)
                .conNombre("Hilton")
                .conUbicacion(Ciudad.BOGOTA)
                .conHabitacion(habitacion)
                .conCostoPorNoche(-50).reconstruir(),
                ExcepcionValorInvalido.class,
                "El valor debe ser positivo"
        );
    }


}
