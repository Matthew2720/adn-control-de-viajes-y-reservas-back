package com.ceiba.reservas.reservavuelo.comando.manejador;

import com.ceiba.reservas.manejador.ManejadorComando;
import com.ceiba.reservas.reservavuelo.comando.ComandoAnular;
import com.ceiba.reservas.reservavuelo.puerto.repositorio.RepositorioReservaVuelo;
import com.ceiba.reservas.reservavuelo.servicio.ServicioAnularReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAnularReserva implements ManejadorComando<ComandoAnular> {

    private final ServicioAnularReserva servicioAnularReserva;
    private final RepositorioReservaVuelo repositorioReservaVuelo;

    public ManejadorAnularReserva(ServicioAnularReserva servicioAnularReserva, RepositorioReservaVuelo repositorioReservaVuelo) {
        this.servicioAnularReserva = servicioAnularReserva;
        this.repositorioReservaVuelo = repositorioReservaVuelo;
    }

    @Override
    public void ejecutar(ComandoAnular comandoAnular) {
        servicioAnularReserva.ejecutar(repositorioReservaVuelo.obtener(comandoAnular.getIdReserva()));

    }
}
