package com.ceiba.reservas.reservahotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudReservaHotel {
    private Long idCliente;
    private Long idHotel;
    private Long idHabitacion;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
}
