package com.ceiba.reservas.vuelo;
import com.ceiba.reservas.vuelo.modelo.entidad.Ciudad;
import com.ceiba.reservas.vuelo.modelo.entidad.Vuelo;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VueloTest {
    @Test
    void deberiaCrearVueloConCeroAsientosDisponibles() {
        Vuelo vuelo = new VueloTestDataBuilder()
                .conVueloElMismoDiaCeroAsientosDisponibles().reconstruir();

        assertNotNull(vuelo);
        assertEquals(0, vuelo.getAsientosDisponibles());
    }

    @Test
    void deberiaAplicarDescuentoCorrectamenteMasDeTresMeses() throws InterruptedException {
        BigDecimal precioBase = new BigDecimal("10000");
        LocalDateTime fechaActual = LocalDateTime.now();
        Vuelo vuelo = new VueloTestDataBuilder()
                .conId(2L)
                .conOrigen(Ciudad.BOGOTA)
                .conDestino(Ciudad.MEDELLIN)
                .conFechaSalida(fechaActual.plusMonths(4))
                .conFechaLlegada(fechaActual.plusMonths(5))
                .conAsientosDisponibles(20)
                .conPrecioBase(precioBase)
                .reconstruir();

        BigDecimal precioConDescuento = vuelo.aplicarDescuento(fechaActual);

        BigDecimal precioEsperado = new BigDecimal("7000.0");
        assertEquals(precioEsperado, precioConDescuento);
    }

    @Test
    void noDeberiaAplicarDescuento() {
        BigDecimal precioBase = new BigDecimal("10000");
        LocalDateTime fechaActual = LocalDateTime.now();

        Vuelo vuelo = new VueloTestDataBuilder()
                .conId(2L)
                .conOrigen(Ciudad.BOGOTA)
                .conDestino(Ciudad.MEDELLIN)
                .conFechaSalida(fechaActual.plusDays(4))
                .conFechaLlegada(fechaActual.plusMonths(5))
                .conAsientosDisponibles(20)
                .conPrecioBase(precioBase)
                .reconstruir();

        BigDecimal precioConDescuento = vuelo.aplicarDescuento(fechaActual);

        assertEquals(precioBase, precioConDescuento);
    }



    @Test
    void deberiaActualizarAsientosDisponiblesCorrectamente() {
        int asientosReservados = 7;
        int asientosEsperados = 3;

        Vuelo vuelo = new VueloTestDataBuilder()
                .conVueloEnDiezDiasDiezAsientosDisponibles()
                .reconstruir();

        vuelo.actualizarAsientosDisponibles(asientosReservados);

        assertEquals(asientosEsperados, vuelo.getAsientosDisponibles());
    }

    @Test
    void deberiaTenerCeroAsientosDisponiblesSiSeReservanTodosLosAsientos() {
        int asientosReservados = 10;

        Vuelo vuelo = new VueloTestDataBuilder()
                .conVueloEnDiezDiasDiezAsientosDisponibles()
                .reconstruir();

        vuelo.actualizarAsientosDisponibles(asientosReservados);

        assertEquals(0, vuelo.getAsientosDisponibles());
    }

}
