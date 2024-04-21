package com.ceiba.reservas.reservahotel;

import com.ceiba.reservas.cliente.ClienteTestDataBuilder;
import com.ceiba.reservas.cliente.entidad.Cliente;
import com.ceiba.reservas.habitacion.HabitacionTestDataBuilder;
import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;
import com.ceiba.reservas.hotel.HotelTestDataBuilder;
import com.ceiba.reservas.hotel.modelo.entidad.Hotel;
import com.ceiba.reservas.reservahotel.modelo.entidad.SolicitudReservaHotel;

import java.time.LocalDateTime;

public class SolicitudReservaHotelTestDataBuilder {
    private Cliente cliente;
    private Hotel hotel;
    private Habitacion habitacion;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;

    public SolicitudReservaHotelTestDataBuilder ConSolicitudReservaHotelporDefecto(){
        var cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        var hotel = new HotelTestDataBuilder().conHotelPorDefecto().reconstruir();
        var habitacion = new HabitacionTestDataBuilder().conHabitacionPorDefecto().reconstruir();
        var fechaEntrada = LocalDateTime.now().plusDays(1);
        var fechaSalida = LocalDateTime.now().plusDays(9);

        this.cliente = cliente;
        this.hotel = hotel;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        return this;
    }

    public SolicitudReservaHotelTestDataBuilder conCliente(Cliente cliente){
        this.cliente = cliente;
        return this;
    }

    public SolicitudReservaHotelTestDataBuilder conHotel(Hotel hotel){
        this.hotel = hotel;
        return this;
    }

    public SolicitudReservaHotelTestDataBuilder conHabitacion(Habitacion habitacion){
        this.habitacion = habitacion;
        return this;
    }

    public SolicitudReservaHotelTestDataBuilder conFechaEntrada(LocalDateTime fechaEntrada){
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public SolicitudReservaHotelTestDataBuilder conFechaSalida(LocalDateTime fechaSalida){
        this.fechaSalida = fechaSalida;
        return this;
    }

    public SolicitudReservaHotel reconstruir(){
        return new SolicitudReservaHotel(cliente, hotel, habitacion, fechaEntrada,fechaSalida);
    }

}
