package com.ceiba.reservas.reservavuelo;

import com.ceiba.reservas.cliente.entidad.Cliente;
import com.ceiba.reservas.reservavuelo.modelo.entidad.SolicitudReservar;
import com.ceiba.reservas.vuelo.modelo.entidad.Vuelo;

public class SolicitudReservarTestDataBuilder {

    private Cliente cliente;
    private Vuelo vuelo;
    private int pasajeros;

    public SolicitudReservarTestDataBuilder conCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }
    public SolicitudReservarTestDataBuilder conVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
        return this;
    }
    public SolicitudReservarTestDataBuilder conPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
        return this;
    }

    public SolicitudReservar reconstruir() {
        return new SolicitudReservar(cliente, vuelo, pasajeros);
    }

}
