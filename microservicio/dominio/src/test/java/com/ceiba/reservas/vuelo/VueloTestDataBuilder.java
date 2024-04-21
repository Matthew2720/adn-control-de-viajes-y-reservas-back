package com.ceiba.reservas.vuelo;

import com.ceiba.reservas.vuelo.modelo.entidad.Ciudad;
import com.ceiba.reservas.vuelo.modelo.entidad.Vuelo;
import static com.ceiba.reservas.vuelo.modelo.entidad.Ciudad.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VueloTestDataBuilder {
    private Long id;
    private Ciudad origen;
    private Ciudad destino;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private int asientosDisponibles;
    private BigDecimal precioBase;

    public VueloTestDataBuilder conVueloEnDiezDiasDiezAsientosDisponibles(){
        id = 1L;
        origen = BOGOTA;
        destino = MEDELLIN;
        fechaSalida = LocalDateTime.now().plusDays(5);
        fechaLlegada = LocalDateTime.now().plusDays(6);
        asientosDisponibles = 10;
        precioBase = new BigDecimal(500);
        return this;
    }

    public VueloTestDataBuilder conVueloElMismoDiaCeroAsientosDisponibles() {
        id = 2L;
        origen = BOGOTA;
        destino = MEDELLIN;
        fechaSalida = LocalDateTime.now();
        fechaLlegada = LocalDateTime.now().plusDays(6);
        asientosDisponibles = 0;
        precioBase = new BigDecimal(500);
        return this;
    }

    public VueloTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public VueloTestDataBuilder conOrigen(Ciudad origen) {
        this.origen = origen;
        return this;
    }

    public VueloTestDataBuilder conDestino(Ciudad destino) {
        this.destino = destino;
        return this;
    }

    public VueloTestDataBuilder conFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
        return this;
    }

    public VueloTestDataBuilder conFechaLlegada(LocalDateTime fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
        return this;
    }

    public VueloTestDataBuilder conAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
        return this;
    }

    public VueloTestDataBuilder conPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
        return this;
    }

    public Vuelo reconstruir() {
        return Vuelo.reconstruir(id,origen,destino,fechaSalida,fechaLlegada,asientosDisponibles,precioBase);
    }
}
