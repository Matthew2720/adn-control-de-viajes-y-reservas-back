package com.ceiba.reservas.reservahotel.controlador;

import com.ceiba.reservas.reservahotel.ComandoSolicitudReservaHotel;

import java.time.LocalDateTime;

public class ComandoReservarHotelTestDataBuilder {
    private Long idCliente;
    private Long idHotel;
    private Long idHabitacion;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;

    public ComandoReservarHotelTestDataBuilder crearPorDefecto(){
        this.idCliente = 1L;
        this.idHotel = 1L;
        this.idHabitacion = 1L;
        this.fechaEntrada = LocalDateTime.now().plusDays(2);
        this.fechaSalida = LocalDateTime.now().plusDays(15);
        return this;
    }

    public ComandoSolicitudReservaHotel build(){
        return new ComandoSolicitudReservaHotel(idCliente,idHotel,idHabitacion,fechaEntrada,fechaSalida);
    }
}
