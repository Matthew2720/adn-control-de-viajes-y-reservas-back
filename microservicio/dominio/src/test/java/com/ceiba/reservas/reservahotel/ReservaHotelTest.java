package com.ceiba.reservas.reservahotel;

import com.ceiba.reservas.cliente.ClienteTestDataBuilder;
import com.ceiba.reservas.core.BasePrueba;
import com.ceiba.reservas.dominio.excepcion.ExcepcionCancelacionNoDisponible;
import com.ceiba.reservas.dominio.excepcion.ExcepcionHabitacionNoDisponible;
import com.ceiba.reservas.habitacion.HabitacionTestDataBuilder;
import com.ceiba.reservas.hotel.HotelTestDataBuilder;
import com.ceiba.reservas.reservahotel.modelo.entidad.ReservaHotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ReservaHotelTest {

    @Test
    void deberiaReconstruirConDataBuilder(){
        var cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        var hotel = new HotelTestDataBuilder().conHotelPorDefecto().reconstruir();
        var habitacion = new HabitacionTestDataBuilder().conHabitacionPorDefecto().reconstruir();

        var reservaHotel = new ReservaHotelTestDataBuilder()
                .conId(1L)
                .conFechaEntrada(LocalDateTime.now())
                .conFechaSalida(LocalDateTime.now().plusDays(6))
                .conHotel(hotel)
                .conCliente(cliente)
                .conHabitacion(habitacion)
                .conEstado("RESERVADO")
                .conCostoTotal(90000)
                .reconstruir();

        Assertions.assertEquals(cliente, reservaHotel.getCliente());
        Assertions.assertEquals(hotel, reservaHotel.getHotel());
        Assertions.assertEquals(habitacion, reservaHotel.getHabitacion());
    }

    @Test
    void deberiaCrearLaReservaCorrectamente(){
        var cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        var hotel = new HotelTestDataBuilder().conHotelPorDefecto().reconstruir();
        var habitacion = new HabitacionTestDataBuilder().conHabitacionPorDefecto().reconstruir();

        var reserva = new ReservaHotelTestDataBuilder().crear();

        Assertions.assertEquals(cliente.getNombre(), reserva.getCliente().getNombre());
        Assertions.assertEquals(hotel.getId(), reserva.getHotel().getId());
        Assertions.assertEquals(habitacion.getNumeroHabitacion(), reserva.getHabitacion().getNumeroHabitacion());
        Assertions.assertEquals(108000, reserva.getCostoTotal());
    }

    @Test
    void deberiaPermitirCancelarReserva() {
        var cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        var hotel = new HotelTestDataBuilder().conHotelPorDefecto().reconstruir();
        var habitacion = new HabitacionTestDataBuilder().conHabitacionPorDefecto().reconstruir();

        var reservaHotel = new ReservaHotelTestDataBuilder()
                .conId(1L)
                .conFechaEntrada(LocalDateTime.now().plusDays(2))
                .conFechaSalida(LocalDateTime.now().plusDays(8))
                .conHotel(hotel)
                .conCliente(cliente)
                .conHabitacion(habitacion)
                .conEstado("RESERVADO")
                .conCostoTotal(90000)
                .reconstruir();

        reservaHotel.cancelarReserva();

        Assertions.assertTrue(reservaHotel.esCancelado());
        Assertions.assertEquals("CANCELADO", reservaHotel.getEstado());
    }

    @Test
    void noDeberiaPermitirCancelarReserva() {
        var cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        var hotel = new HotelTestDataBuilder().conHotelPorDefecto().reconstruir();
        var habitacion = new HabitacionTestDataBuilder().conHabitacionPorDefecto().reconstruir();

        var reservaHotel = new ReservaHotelTestDataBuilder()
                .conId(1L)
                .conFechaEntrada(LocalDateTime.now())
                .conFechaSalida(LocalDateTime.now().plusDays(6))
                .conHotel(hotel)
                .conCliente(cliente)
                .conHabitacion(habitacion)
                .conEstado("RESERVADO")
                .conCostoTotal(90000)
                .reconstruir();

        Assertions.assertThrows(ExcepcionCancelacionNoDisponible.class, reservaHotel::cancelarReserva);
    }

    @Test
    void deberiaLiberarHabitacionReserva() {
        var cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        var hotel = new HotelTestDataBuilder().conHotelPorDefecto().reconstruir();
        var habitacion = new HabitacionTestDataBuilder().conHabitacionPorDefecto().reconstruir();

        var reservaHotel = new ReservaHotelTestDataBuilder()
                .conId(1L)
                .conFechaEntrada(LocalDateTime.now())
                .conFechaSalida(LocalDateTime.now().plusDays(6))
                .conHotel(hotel)
                .conCliente(cliente)
                .conHabitacion(habitacion)
                .conEstado("RESERVADO")
                .conCostoTotal(90000)
                .reconstruir();

        reservaHotel.liberarHabitacionReserva();

        Assertions.assertTrue(reservaHotel.getHabitacion().estaDisponible());
    }

    @Test
    void deberiaLanzarExcepcionSiHabitacionNoEstaDisponible() {
        var habitacion = new HabitacionTestDataBuilder().conHabitacionPorDefecto().conDisponibilidad(false).reconstruir();

        BasePrueba.assertThrows(() -> ReservaHotel.validarDisponibilidadHabitacion(habitacion),
                ExcepcionHabitacionNoDisponible.class, "La habitacion no esta disponible");
    }


}
