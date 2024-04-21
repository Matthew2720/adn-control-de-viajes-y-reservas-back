package com.ceiba.reservas.reservahotel.modelo.entidad;

import com.ceiba.reservas.cliente.entidad.Cliente;
import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;
import com.ceiba.reservas.hotel.modelo.entidad.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
public class SolicitudReservaHotel {
    private Cliente cliente;
    private Hotel hotel;
    private Habitacion habitacion;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
}
