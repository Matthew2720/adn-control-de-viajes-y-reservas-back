package com.ceiba.reservas.reservavuelo.comando.fabrica;

import com.ceiba.reservas.cliente.puerto.RepositorioCliente;
import com.ceiba.reservas.reservavuelo.comando.ComandoSolicitudReservar;
import com.ceiba.reservas.reservavuelo.modelo.entidad.SolicitudReservar;
import com.ceiba.reservas.vuelo.puerto.repositorio.RepositorioVuelo;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudReservar {

    private final RepositorioCliente repositorioCliente;
    private final RepositorioVuelo repositorioVuelo;


    public FabricaSolicitudReservar(RepositorioCliente repositorioCliente, RepositorioVuelo repositorioVuelo) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioVuelo = repositorioVuelo;
    }

    public SolicitudReservar crear(ComandoSolicitudReservar comandoSolicitudReservar){
        return new SolicitudReservar(repositorioCliente.obtener(comandoSolicitudReservar.getIdCliente()),
                repositorioVuelo.obtener(comandoSolicitudReservar.getIdVuelo()), comandoSolicitudReservar.getPasajeros());
    }
}
