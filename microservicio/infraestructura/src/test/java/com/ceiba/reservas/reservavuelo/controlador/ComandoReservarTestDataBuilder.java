package com.ceiba.reservas.reservavuelo.controlador;

import com.ceiba.reservas.reservavuelo.comando.ComandoSolicitudReservar;

public class ComandoReservarTestDataBuilder {
    private Long idCliente;
    private Long idVuelo;
    private int pasajeros;


    public ComandoReservarTestDataBuilder crearPorDefecto(){
        this.idCliente = 1l;
        this.idVuelo = 1l;
        this.pasajeros = 10;
        return this;
    }

    public ComandoSolicitudReservar build(){
        return new ComandoSolicitudReservar(this.idCliente, this.idVuelo, this.pasajeros);
    }
}
