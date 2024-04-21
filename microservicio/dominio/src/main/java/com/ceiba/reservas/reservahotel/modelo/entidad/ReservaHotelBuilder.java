package com.ceiba.reservas.reservahotel.modelo.entidad;

import com.ceiba.reservas.cliente.entidad.Cliente;
import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;
import com.ceiba.reservas.hotel.modelo.entidad.Hotel;

import java.time.LocalDateTime;

public class ReservaHotelBuilder {
    private Long id;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Hotel hotel;
    private Habitacion habitacion;
    private Cliente cliente;
    private double costoTotal;
    private String estado;


    public ReservaHotelBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ReservaHotelBuilder conFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public ReservaHotelBuilder conFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
        return this;
    }

    public ReservaHotelBuilder conHotel(Hotel hotel) {
        this.hotel = hotel;
        return this;
    }

    public ReservaHotelBuilder conHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
        return this;
    }

    public ReservaHotelBuilder conCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public ReservaHotelBuilder conCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
        return this;
    }

    public ReservaHotelBuilder conEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public ReservaHotel build() {
        return new ReservaHotel(id, fechaEntrada, fechaSalida, hotel, habitacion, cliente, costoTotal, estado);
    }
}