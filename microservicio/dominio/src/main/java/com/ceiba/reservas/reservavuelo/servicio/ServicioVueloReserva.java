package com.ceiba.reservas.reservavuelo.servicio;

import com.ceiba.reservas.reservavuelo.modelo.entidad.ReservaVuelo;
import com.ceiba.reservas.reservavuelo.modelo.entidad.SolicitudReservar;
import com.ceiba.reservas.reservavuelo.puerto.repositorio.RepositorioReservaVuelo;
import com.ceiba.reservas.vuelo.modelo.entidad.Vuelo;
import com.ceiba.reservas.vuelo.puerto.repositorio.RepositorioVuelo;

public class ServicioVueloReserva {

    private final RepositorioVuelo repositorioVuelo;
    private final RepositorioReservaVuelo repositorioReservaVuelo;

    public ServicioVueloReserva(RepositorioVuelo repositorioVuelo, RepositorioReservaVuelo repositorioReservaVuelo){
        this.repositorioVuelo = repositorioVuelo;
        this.repositorioReservaVuelo = repositorioReservaVuelo;
    }

    public Long ejecutar(SolicitudReservar solicitudReservar) {
        Vuelo vuelo = solicitudReservar.getVuelo();
        ReservaVuelo reservaVuelo = ReservaVuelo.crear(solicitudReservar);
        vuelo.reservarAsientos(solicitudReservar.getPasajeros());
        repositorioVuelo.actualizar(vuelo);
        return repositorioReservaVuelo.guardar(reservaVuelo);
    }
}
