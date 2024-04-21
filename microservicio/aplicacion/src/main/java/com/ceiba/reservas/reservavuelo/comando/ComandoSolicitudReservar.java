package com.ceiba.reservas.reservavuelo.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudReservar {
    private Long idCliente;
    private Long idVuelo;
    private int pasajeros;
}
