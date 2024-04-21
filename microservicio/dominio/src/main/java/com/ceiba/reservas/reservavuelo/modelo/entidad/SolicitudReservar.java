package com.ceiba.reservas.reservavuelo.modelo.entidad;

import com.ceiba.reservas.cliente.entidad.Cliente;
import com.ceiba.reservas.vuelo.modelo.entidad.Vuelo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SolicitudReservar {
    private Cliente cliente;
    private Vuelo vuelo;
    private int pasajeros;
}
