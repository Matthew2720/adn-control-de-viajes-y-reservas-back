package com.ceiba.reservas.reservavuelo;

import com.ceiba.reservas.cliente.ClienteTestDataBuilder;
import com.ceiba.reservas.cliente.entidad.Cliente;
import com.ceiba.reservas.dominio.excepcion.*;
import com.ceiba.reservas.reservavuelo.modelo.entidad.EstadoReserva;
import com.ceiba.reservas.reservavuelo.modelo.entidad.ReservaVuelo;
import com.ceiba.reservas.reservavuelo.modelo.entidad.SolicitudReservar;
import com.ceiba.reservas.vuelo.VueloTestDataBuilder;
import com.ceiba.reservas.vuelo.modelo.entidad.Vuelo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservaVueloTest {

    @Test
    void deberiaReconstruirCorrectamente() {
        Vuelo vuelo = new VueloTestDataBuilder().conVueloEnDiezDiasDiezAsientosDisponibles().reconstruir();
        Cliente cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        LocalDateTime fechaHoraReserva = LocalDateTime.now();
        int pasajeros = 5;
        BigDecimal precioFinal = new BigDecimal(10000);

        ReservaVueloTestDataBuilder reservaVueloTestDataBuilder = new ReservaVueloTestDataBuilder()
                .conId(1L)
                .conVuelo(vuelo)
                .conFechaHoraReserva(fechaHoraReserva)
                .conPasajeros(pasajeros)
                .conPrecioFinal(precioFinal)
                .conEstado(EstadoReserva.ACTIVA)
                .conCliente(cliente);

        ReservaVuelo reservaVuelo = reservaVueloTestDataBuilder.reconstruir();

        assertEquals(1L, reservaVuelo.getId().longValue());
        assertEquals(vuelo, reservaVuelo.getVuelo());
        assertEquals(fechaHoraReserva, reservaVuelo.getFechaHoraReserva());
        assertEquals(pasajeros, reservaVuelo.getPasajeros());
        assertEquals(precioFinal, reservaVuelo.getPrecioFinal());
    }

    @Test
    void deberiaReservarCorrectamente() {
        SolicitudReservar solicitudReservar = new SolicitudReservarTestDataBuilder()
                .conCliente(new ClienteTestDataBuilder().conClientePorDefecto().reconstruir())
                .conVuelo(new VueloTestDataBuilder().conVueloEnDiezDiasDiezAsientosDisponibles().reconstruir())
                .conPasajeros(2).reconstruir();

        ReservaVuelo reservaVuelo = ReservaVuelo.crear(solicitudReservar);

        assertEquals(solicitudReservar.getVuelo(), reservaVuelo.getVuelo());
        assertEquals(solicitudReservar.getPasajeros(), reservaVuelo.getPasajeros());
    }


    @Test
    void deberiaLanzarExcepcionCuandoVueloEsNulo() {
        SolicitudReservar solicitudReservar = new SolicitudReservarTestDataBuilder()
                .conCliente(new ClienteTestDataBuilder().conClientePorDefecto().reconstruir())
                .conVuelo(null)
                .conPasajeros(2).reconstruir();

        assertThrows(ExcepcionValorObligatorio.class, () -> {
            ReservaVuelo reservaVuelo = ReservaVuelo.crear(solicitudReservar);
        });
    }

    @Test
    void deberiaLanzarExcepcionCuandoPasajerosEsCero() {
        SolicitudReservar solicitudReservar = new SolicitudReservarTestDataBuilder()
                .conCliente(new ClienteTestDataBuilder().conClientePorDefecto().reconstruir())
                .conVuelo(new VueloTestDataBuilder().conVueloEnDiezDiasDiezAsientosDisponibles().reconstruir())
                .conPasajeros(0).reconstruir();

        assertThrows(ExcepcionValorInvalido.class, () -> {
            ReservaVuelo reservaVuelo = ReservaVuelo.crear(solicitudReservar);
        });
    }

    @Test
    void deberiaLanzarExcepcionCuandoPasajerosEsNegativo() {
        SolicitudReservar solicitudReservar = new SolicitudReservarTestDataBuilder()
                .conCliente(new ClienteTestDataBuilder().conClientePorDefecto().reconstruir())
                .conVuelo(new VueloTestDataBuilder().conVueloEnDiezDiasDiezAsientosDisponibles().reconstruir())
                .conPasajeros(-2).reconstruir();
        assertThrows(ExcepcionValorInvalido.class, () -> {
            ReservaVuelo reservaVuelo = ReservaVuelo.crear(solicitudReservar);
        });
    }

    @Test
    void deberiaLanzarExcepcionCuandoVueloNoTieneAsientosDisponibles() {
        Vuelo vuelo = new VueloTestDataBuilder().conVueloElMismoDiaCeroAsientosDisponibles().reconstruir();
        SolicitudReservar solicitudReservar = new SolicitudReservarTestDataBuilder()
                .conCliente(new ClienteTestDataBuilder().conClientePorDefecto().reconstruir())
                .conVuelo(vuelo)
                .conPasajeros(5).reconstruir();

        assertThrows(ExcepcionVueloNoDisponible.class, () -> {
            ReservaVuelo reservaVuelo = ReservaVuelo.crear(solicitudReservar);
        });
    }

    @Test
    void deberiaLanzarExcepcionCuandoClienteEsNulo() {
        Vuelo vuelo = new VueloTestDataBuilder().conVueloEnDiezDiasDiezAsientosDisponibles().reconstruir();
        SolicitudReservar solicitudReservar = new SolicitudReservar(null, vuelo, 2);

        assertThrows(ExcepcionValorObligatorio.class, () -> {
            ReservaVuelo reservaVuelo = ReservaVuelo.crear(solicitudReservar);
        });
    }

    @Test
    void deberiaCambiarEstadoCorrectamente(){
        ReservaVuelo reservaVuelo = new ReservaVueloTestDataBuilder().conDosPasajeros().reconstruir();
        EstadoReserva esperado = EstadoReserva.CANCELADA;

        reservaVuelo.actualizarEstadoReserva();

        assertEquals(reservaVuelo.getEstadoReserva(), esperado);
    }

    @Test
    void deberiaCalcularReembolsoCorrectamente() {
        ReservaVuelo reservaVuelo = new ReservaVueloTestDataBuilder()
                .conDosPasajeros()
                .reconstruir();

        reservaVuelo.calcularReembolsoReserva();

        BigDecimal precioEsperado = new BigDecimal("5000.0");
        assertEquals(precioEsperado, reservaVuelo.getPrecioFinal());
    }

    @Test
    void deberiaAnularReservaConCobro() {
        ReservaVuelo reservaVuelo = new ReservaVueloTestDataBuilder()
                .conDosPasajeros()
                .conFechaHoraReserva(LocalDateTime.now().minusHours(26))
                .reconstruir();
        BigDecimal reembolsoEsperado = new BigDecimal("5000.0");

        ReservaVuelo.anular(reservaVuelo);

        assertEquals(EstadoReserva.CANCELADA, reservaVuelo.getEstadoReserva());
        assertEquals(reservaVuelo.getPrecioFinal() , reembolsoEsperado);
    }

    @Test
    void deberiaAnularReservaSinCobro() {
        ReservaVuelo reservaVuelo = new ReservaVueloTestDataBuilder()
                .conDosPasajeros()
                .conFechaHoraReserva(LocalDateTime.now().minusHours(2))
                .reconstruir();

        BigDecimal reembolsoEsperado = reservaVuelo.getPrecioFinal();

        ReservaVuelo.anular(reservaVuelo);

        assertEquals(EstadoReserva.CANCELADA, reservaVuelo.getEstadoReserva());
        assertEquals(reembolsoEsperado, reservaVuelo.getPrecioFinal());
    }

}
