package com.ceiba.reservas.habitacion;

import com.ceiba.reservas.core.BasePrueba;
import com.ceiba.reservas.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HabitacionTest {

    @Test
    void deberiaReconstruirHabitacionExitosamente(){
        var habitacion = new HabitacionTestDataBuilder()
                .conId(1L)
                .conNumeroHabitacion(101)
                .conDisponibilidad(true).reconstruir();

        Assertions.assertEquals(1L, habitacion.getId());
        Assertions.assertEquals(101, habitacion.getNumeroHabitacion());
        assertTrue(habitacion.isDisponibilidad());
    }

    @Test
    void reconstruirSinIdDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new HabitacionTestDataBuilder()
                .conNumeroHabitacion(101)
                .conDisponibilidad(true).reconstruir(),
                ExcepcionValorObligatorio.class,
                "El id es requerido para la habitacion");
    }

    @Test
    void deberiaLiberarHabitacion() {
        var habitacion = new HabitacionTestDataBuilder()
                .conId(1L)
                .conNumeroHabitacion(101)
                .conDisponibilidad(false).reconstruir();

        habitacion.liberarHabitacion();

        assertTrue(habitacion.estaDisponible());
    }

    @Test
    void deberiaOcuparHabitacion() {
        var habitacion = new HabitacionTestDataBuilder()
                .conId(1L)
                .conNumeroHabitacion(101)
                .conDisponibilidad(true).reconstruir();

        habitacion.ocuparHabitacion();

        assertFalse(habitacion.estaDisponible());
    }
}
