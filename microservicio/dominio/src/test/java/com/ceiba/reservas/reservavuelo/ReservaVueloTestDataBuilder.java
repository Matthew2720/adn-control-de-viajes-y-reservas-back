package com.ceiba.reservas.reservavuelo;

import com.ceiba.reservas.cliente.ClienteTestDataBuilder;
import com.ceiba.reservas.cliente.entidad.Cliente;
import com.ceiba.reservas.reservavuelo.modelo.entidad.EstadoReserva;
import com.ceiba.reservas.reservavuelo.modelo.entidad.ReservaVuelo;
import com.ceiba.reservas.vuelo.VueloTestDataBuilder;
import com.ceiba.reservas.vuelo.modelo.entidad.Vuelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservaVueloTestDataBuilder {
    private Long id;
    private Vuelo vuelo;
    private LocalDateTime fechaHoraReserva;
    private int pasajeros;
    private BigDecimal precioFinal;
    private EstadoReserva estadoReserva;
    private Cliente cliente;

    public ReservaVueloTestDataBuilder conDosPasajeros(){
        id = 1L;
        vuelo = new VueloTestDataBuilder().conVueloEnDiezDiasDiezAsientosDisponibles().reconstruir();
        fechaHoraReserva = LocalDateTime.now();
        pasajeros = 2;
        precioFinal = new BigDecimal(10000);
        estadoReserva = EstadoReserva.ACTIVA;
        cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        return this;
    }

    public ReservaVueloTestDataBuilder conVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
        return this;
    }

    public ReservaVueloTestDataBuilder conCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public ReservaVueloTestDataBuilder conEstado(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
        return this;
    }

    public ReservaVueloTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public ReservaVueloTestDataBuilder conFechaHoraReserva(LocalDateTime fechaHoraReserva) {
        this.fechaHoraReserva = fechaHoraReserva;
        return this;
    }

    public ReservaVueloTestDataBuilder conPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
        return this;
    }

    public ReservaVueloTestDataBuilder conPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
        return this;
    }

    public ReservaVuelo reconstruir() {
        return ReservaVuelo.reconstruir(id, vuelo, fechaHoraReserva, pasajeros, precioFinal,estadoReserva, cliente);
    }
}
