package com.ceiba.reservas.reservahotel.fabrica;

import com.ceiba.reservas.cliente.puerto.RepositorioCliente;
import com.ceiba.reservas.habitacion.puerto.RepositorioHabitacion;
import com.ceiba.reservas.hotel.puerto.repositorio.RepositorioHotel;
import com.ceiba.reservas.reservahotel.ComandoSolicitudReservaHotel;
import com.ceiba.reservas.reservahotel.modelo.entidad.SolicitudReservaHotel;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudReservaHotel {

    private final RepositorioCliente repositorioCliente;
    private final RepositorioHotel repositorioHotel;
    private final RepositorioHabitacion repositorioHabitacion;


    public FabricaSolicitudReservaHotel(RepositorioCliente repositorioCliente, RepositorioHotel repositorioHotel, RepositorioHabitacion repositorioHabitacion) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioHotel = repositorioHotel;
        this.repositorioHabitacion = repositorioHabitacion;
    }

    public SolicitudReservaHotel crear(ComandoSolicitudReservaHotel comandoSolicitudReservaHotel){
        return new SolicitudReservaHotel(repositorioCliente.obtener(comandoSolicitudReservaHotel.getIdCliente()),
                repositorioHotel.obtener(comandoSolicitudReservaHotel.getIdHotel()), repositorioHabitacion.obtener(comandoSolicitudReservaHotel.getIdHabitacion())
                ,comandoSolicitudReservaHotel.getFechaEntrada(), comandoSolicitudReservaHotel.getFechaSalida());
    }
}
