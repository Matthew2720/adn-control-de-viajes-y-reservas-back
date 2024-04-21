package com.ceiba.reservas.reservahotel;

import com.ceiba.reservas.cliente.ClienteTestDataBuilder;
import com.ceiba.reservas.cliente.entidad.Cliente;
import com.ceiba.reservas.habitacion.HabitacionTestDataBuilder;
import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;
import com.ceiba.reservas.hotel.HotelTestDataBuilder;
import com.ceiba.reservas.hotel.modelo.entidad.Hotel;
import com.ceiba.reservas.reservahotel.modelo.entidad.ReservaHotel;

import java.time.LocalDateTime;

public class ReservaHotelTestDataBuilder {

    private Long id;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Hotel hotel;
    private Habitacion habitacion;
    private Cliente cliente;
    private double costoTotal;
    private String estado;

    public ReservaHotelTestDataBuilder conReservaHotelporDefecto() {
        this.id = 1L;
        this.fechaEntrada = LocalDateTime.now();
        this.fechaSalida = LocalDateTime.now().plusDays(8);
        this.hotel = new HotelTestDataBuilder().conHotelPorDefecto().reconstruir();
        this.habitacion = new HabitacionTestDataBuilder().conHabitacionPorDefecto().reconstruir();
        this.cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        this.costoTotal = 80000;
        this.estado = "RESERVADO";
        return this;
    }

    public ReservaHotelTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ReservaHotelTestDataBuilder conFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public ReservaHotelTestDataBuilder conFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
        return this;
    }

    public ReservaHotelTestDataBuilder conHotel(Hotel hotel) {
        this.hotel = hotel;
        return this;
    }

    public ReservaHotelTestDataBuilder conHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
        return this;
    }

    public ReservaHotelTestDataBuilder conCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public ReservaHotelTestDataBuilder conCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
        return this;
    }

    public ReservaHotelTestDataBuilder conEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public ReservaHotel crear(){
        return ReservaHotel.crear(new SolicitudReservaHotelTestDataBuilder()
                .ConSolicitudReservaHotelporDefecto().reconstruir());
    }

    public ReservaHotel reconstruir() {
        return ReservaHotel.reconstruir(id, fechaEntrada, fechaSalida, hotel, habitacion, cliente, costoTotal, estado);
    }
}
