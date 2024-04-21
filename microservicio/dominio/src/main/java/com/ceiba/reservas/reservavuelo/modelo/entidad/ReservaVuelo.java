package com.ceiba.reservas.reservavuelo.modelo.entidad;

import com.ceiba.reservas.cliente.entidad.Cliente;
import com.ceiba.reservas.dominio.ValidadorArgumento;
import com.ceiba.reservas.dominio.excepcion.ExcepcionCancelacionNoDisponible;
import com.ceiba.reservas.dominio.excepcion.ExcepcionVueloNoDisponible;
import com.ceiba.reservas.vuelo.modelo.entidad.Vuelo;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public final class ReservaVuelo {

    private Long id;
    private Cliente cliente;
    private Vuelo vuelo;
    private LocalDateTime fechaHoraReserva;
    private int pasajeros;
    private BigDecimal precioFinal;
    private EstadoReserva estadoReserva;

    private ReservaVuelo(Vuelo vuelo, int pasajeros, Cliente cliente) {
        sePuedeReservarHayAsientosDisponibles(vuelo);
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.pasajeros = pasajeros;
        this.fechaHoraReserva = LocalDateTime.now();
        this.precioFinal = vuelo.aplicarDescuento(this.fechaHoraReserva).multiply(BigDecimal.valueOf(this.pasajeros));
        this.estadoReserva = EstadoReserva.ACTIVA;
    }

    private ReservaVuelo(Long id, Vuelo vuelo, LocalDateTime fechaHoraReserva, int pasajeros, BigDecimal precioFinal, EstadoReserva estadoReserva, Cliente cliente) {
        this.id = id;
        this.vuelo = vuelo;
        this.fechaHoraReserva = fechaHoraReserva;
        this.pasajeros = pasajeros;
        this.precioFinal = precioFinal;
        this.estadoReserva = estadoReserva;
        this.cliente = cliente;
    }

    public static ReservaVuelo reconstruir(Long id, Vuelo vuelo, LocalDateTime fechaHoraReserva, int pasajeros, BigDecimal precioFinal, EstadoReserva estadoReserva, Cliente cliente) {
        ValidadorArgumento.validarObligatorio(id, "Id requerido para la reserva");
        ValidadorArgumento.validarObligatorio(vuelo, "Vuelo requerido para la reserva");
        ValidadorArgumento.validarObligatorio(fechaHoraReserva, "Fecha de la reserva requerida");
        ValidadorArgumento.validarPositivo(pasajeros, "Los pasajeros deben ser mayor a cero");
        ValidadorArgumento.validarPositivo(precioFinal, "El precio debe ser mayor a cero");
        ValidadorArgumento.validarObligatorio(cliente, "El cliente es obligatorio para asignar la reserva");

        return new ReservaVuelo(id,vuelo,fechaHoraReserva,pasajeros,precioFinal, estadoReserva, cliente);
    }

    public static ReservaVuelo crear(SolicitudReservar solicitudReservar){
        ValidadorArgumento.validarObligatorio(solicitudReservar.getCliente(), "El cliente es requerido para la reserva");
        ValidadorArgumento.validarObligatorio(solicitudReservar.getVuelo(), "Vuelo requerido para la reserva");
        ValidadorArgumento.validarObligatorio(solicitudReservar.getPasajeros(), "Pasajeros requeridos para la reserva");
        ValidadorArgumento.validarPositivo(solicitudReservar.getPasajeros(), "Pasajeros requeridos para la reserva");
        return new ReservaVuelo(solicitudReservar.getVuelo(), solicitudReservar.getPasajeros(), solicitudReservar.getCliente());
    }

    public static void anular(ReservaVuelo reservaVuelo){
        LocalDateTime fechaHoraSalida = reservaVuelo.vuelo.getFechaSalida();
        LocalDateTime fechaActual = LocalDateTime.now();
        if (fechaActual.isBefore(fechaHoraSalida)){
            if (fechaActual.isAfter(reservaVuelo.getFechaHoraReserva().plusDays(1))){
                reservaVuelo.calcularReembolsoReserva();
            }
            reservaVuelo.actualizarEstadoReserva();
        } else {
            throw new ExcepcionCancelacionNoDisponible("No se puede anular esta reserva, la fecha de salida del vuelo ya ha pasado.");
        }
    }

    public void calcularReembolsoReserva(){
        final double COBRO_REEMBOLSO_MAYOR_24_HORAS = 0.5;
        BigDecimal reembolso = precioFinal.subtract(precioFinal.multiply(BigDecimal.valueOf(COBRO_REEMBOLSO_MAYOR_24_HORAS)));
        this.precioFinal = precioFinal.subtract(reembolso).max(BigDecimal.ZERO);
    }

    public void actualizarEstadoReserva(){
        this.estadoReserva = EstadoReserva.CANCELADA;
    }

    private void sePuedeReservarHayAsientosDisponibles(Vuelo vuelo){
        if (!vuelo.sePuedeReservar() || !vuelo.hayAsientosSuficientes(pasajeros)) {
            throw new ExcepcionVueloNoDisponible("No se puede realizar la reserva para este vuelo.");
        }
    }

}
