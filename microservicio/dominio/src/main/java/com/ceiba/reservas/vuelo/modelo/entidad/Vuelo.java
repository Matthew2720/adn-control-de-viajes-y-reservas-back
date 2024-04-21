package com.ceiba.reservas.vuelo.modelo.entidad;

import com.ceiba.reservas.dominio.ValidadorArgumento;
import com.ceiba.reservas.dominio.excepcion.ExcepcionAsientosNoDisponibles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public final class Vuelo {

    public static final double DESCUENTO_ANTICIPACION_UN_MES = 0.1;
    public static final double DESCUENTO_ANTICIPACION_MAS_DE_60_DIAS = 0.3;

    private Long id;
    private final Ciudad origen;
    private final Ciudad destino;
    private LocalDateTime fechaSalida;
    private final LocalDateTime fechaLlegada;
    private int asientosDisponibles;
    private BigDecimal precioBase;

    private Vuelo(Long id, Ciudad origen, Ciudad destino, LocalDateTime fechaSalida, LocalDateTime fechaLlegada, int asientosDisponibles, BigDecimal precioBase) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.asientosDisponibles = asientosDisponibles;
        this.precioBase = precioBase;
    }

    public static Vuelo reconstruir(Long id, Ciudad origen, Ciudad destino, LocalDateTime fechaSalida, LocalDateTime fechaLlegada, int asientosDisponibles, BigDecimal precioBase) {
        ValidadorArgumento.validarObligatorio(id, "id requerido para el Vuelo");
        ValidadorArgumento.validarObligatorio(origen, "origen requerido para el Vuelo");
        ValidadorArgumento.validarObligatorio(destino, "destino requerido para el Vuelo");
        ValidadorArgumento.validarObligatorio(fechaSalida, "fecha de salida requerido para el Vuelo");
        ValidadorArgumento.validarObligatorio(fechaLlegada, "fecha de llegada requerido para el Vuelo");
        ValidadorArgumento.validarObligatorio(asientosDisponibles, "asientos disponibles requerido para el Vuelo");
        ValidadorArgumento.validarObligatorio(precioBase, "precio base requerido para el Vuelo");
        return new Vuelo(id,origen,destino,fechaSalida,fechaLlegada,asientosDisponibles,precioBase);
    }

    public BigDecimal aplicarDescuento(LocalDateTime fechaReserva){
        long diferencia = fechaReserva.until(fechaSalida, ChronoUnit.DAYS);
        if(diferencia > 60){
            this.precioBase = precioBase.subtract(precioBase.multiply(BigDecimal.valueOf(DESCUENTO_ANTICIPACION_MAS_DE_60_DIAS)));
        } else if (diferencia > 30){
            this.precioBase = precioBase.subtract(precioBase.multiply(BigDecimal.valueOf(DESCUENTO_ANTICIPACION_UN_MES)));
        }
        return this.precioBase;
    }

    public boolean hayAsientosSuficientes(int numeroPasajeros){
        return this.asientosDisponibles > numeroPasajeros;
    }
    public boolean hayAsientosDisponibles(){
        return this.asientosDisponibles > 0;
    }

    public void actualizarAsientosDisponibles(int numeroPasajeros){
        this.asientosDisponibles -= numeroPasajeros;
    }

    public void actualizarAsientosLiberados(int numeroPasajeros){
        this.asientosDisponibles = this.asientosDisponibles + numeroPasajeros;
    }

    public Long getId(){ return this.id; }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void reservarAsientos(int numeroPasajeros){
        if (hayAsientosSuficientes(numeroPasajeros)){
            actualizarAsientosDisponibles(numeroPasajeros);
        } else {
            throw new ExcepcionAsientosNoDisponibles("No hay asientos disponibles");
        }
    }

    public boolean sePuedeReservar(){
        final boolean FECHA_MAXIMA_ANTICIPACION = LocalDateTime.now().isBefore(getFechaSalida().plusDays(1));
        return hayAsientosDisponibles() && FECHA_MAXIMA_ANTICIPACION;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }
}
