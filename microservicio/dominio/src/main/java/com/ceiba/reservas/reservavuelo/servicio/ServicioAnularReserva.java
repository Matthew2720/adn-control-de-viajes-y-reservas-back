package com.ceiba.reservas.reservavuelo.servicio;

import com.ceiba.reservas.dominio.ValidadorArgumento;
import com.ceiba.reservas.reservavuelo.modelo.entidad.ReservaVuelo;
import com.ceiba.reservas.reservavuelo.puerto.repositorio.RepositorioReservaVuelo;
import com.ceiba.reservas.vuelo.modelo.entidad.Vuelo;
import com.ceiba.reservas.vuelo.puerto.repositorio.RepositorioVuelo;

public class ServicioAnularReserva {

    private final RepositorioReservaVuelo repositorioReservaVuelo;
    private final RepositorioVuelo repositorioVuelo;

    public ServicioAnularReserva(RepositorioReservaVuelo repositorioReservaVuelo, RepositorioVuelo repositorioVuelo) {
        this.repositorioReservaVuelo = repositorioReservaVuelo;
        this.repositorioVuelo = repositorioVuelo;
    }

    public void ejecutar(ReservaVuelo reservaVuelo){
        ValidadorArgumento.validarObligatorio(reservaVuelo, "No existe una reserva para anular");
        ReservaVuelo.anular(reservaVuelo);
        Vuelo vuelo = reservaVuelo.getVuelo();
        vuelo.actualizarAsientosLiberados(reservaVuelo.getPasajeros());
        repositorioVuelo.actualizar(vuelo);
        repositorioReservaVuelo.actualizar(reservaVuelo);
    }
}
