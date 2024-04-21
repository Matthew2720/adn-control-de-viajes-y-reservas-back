package com.ceiba.reservas.reservahotel.manejador;

import com.ceiba.reservas.manejador.ManejadorComando;
import com.ceiba.reservas.reservahotel.ComandoAnularReservaHotel;
import com.ceiba.reservas.reservahotel.puerto.repositorio.RepositorioReservaHotel;
import com.ceiba.reservas.reservahotel.servicio.ServicioAnularHotelReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAnularReservaHotel implements ManejadorComando<ComandoAnularReservaHotel> {

    private final ServicioAnularHotelReserva servicioAnularHotelReserva;
    private final RepositorioReservaHotel repositorioReservaHotel;


    public ManejadorAnularReservaHotel(ServicioAnularHotelReserva servicioAnularHotelReserva, RepositorioReservaHotel repositorioReservaHotel) {
        this.servicioAnularHotelReserva = servicioAnularHotelReserva;
        this.repositorioReservaHotel = repositorioReservaHotel;
    }


    @Override
    public void ejecutar(ComandoAnularReservaHotel comando) {
        servicioAnularHotelReserva.ejecutar(repositorioReservaHotel.obtener(comando.getIdReserva()));
    }
}
