package com.ceiba.reservas.reservahotel.servicio;

import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;
import com.ceiba.reservas.habitacion.puerto.RepositorioHabitacion;
import com.ceiba.reservas.reservahotel.modelo.entidad.ReservaHotel;
import com.ceiba.reservas.reservahotel.modelo.entidad.SolicitudReservaHotel;
import com.ceiba.reservas.reservahotel.puerto.repositorio.RepositorioReservaHotel;

public class ServicioHotelReserva {

    private final RepositorioReservaHotel repositorioReservaHotel;
    private final RepositorioHabitacion repositorioHabitacion;


    public ServicioHotelReserva(RepositorioReservaHotel repositorioReservaHotel, RepositorioHabitacion repositorioHabitacion) {
        this.repositorioReservaHotel = repositorioReservaHotel;
        this.repositorioHabitacion = repositorioHabitacion;
    }

    public Long ejecutar(SolicitudReservaHotel solicitudReservaHotel){
        Habitacion habitacion = solicitudReservaHotel.getHabitacion();
        ReservaHotel reservaHotel = ReservaHotel.crear(solicitudReservaHotel);
        habitacion.ocuparHabitacion();
        repositorioHabitacion.actualizar(habitacion);
        return repositorioReservaHotel.guardar(reservaHotel);
    }
}
