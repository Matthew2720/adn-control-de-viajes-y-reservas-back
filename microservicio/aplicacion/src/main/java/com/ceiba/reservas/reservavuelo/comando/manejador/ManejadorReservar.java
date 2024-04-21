package com.ceiba.reservas.reservavuelo.comando.manejador;

import com.ceiba.reservas.ComandoRespuesta;
import com.ceiba.reservas.manejador.ManejadorComandoRespuesta;
import com.ceiba.reservas.reservavuelo.comando.ComandoSolicitudReservar;
import com.ceiba.reservas.reservavuelo.comando.fabrica.FabricaSolicitudReservar;
import com.ceiba.reservas.reservavuelo.servicio.ServicioVueloReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorReservar implements ManejadorComandoRespuesta<ComandoSolicitudReservar, ComandoRespuesta<Long>> {

    private final FabricaSolicitudReservar fabricaSolicitudReservar;
    private final ServicioVueloReserva servicioVueloReserva;


    public ManejadorReservar(FabricaSolicitudReservar fabricaSolicitudReservar, ServicioVueloReserva servicioVueloReserva) {
        this.fabricaSolicitudReservar = fabricaSolicitudReservar;
        this.servicioVueloReserva = servicioVueloReserva;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitudReservar comandoSolicitudReservar) {
        return new ComandoRespuesta<>(servicioVueloReserva
                .ejecutar(fabricaSolicitudReservar.crear(comandoSolicitudReservar)));
    }
}
