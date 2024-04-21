package com.ceiba.reservas.reservahotel.servicio;

import com.ceiba.reservas.dominio.ValidadorArgumento;
import com.ceiba.reservas.habitacion.puerto.RepositorioHabitacion;
import com.ceiba.reservas.reservahotel.modelo.entidad.ReservaHotel;
import com.ceiba.reservas.reservahotel.puerto.repositorio.RepositorioReservaHotel;

public class ServicioAnularHotelReserva {

    private final RepositorioReservaHotel repositorioReservaHotel;
    private final RepositorioHabitacion repositorioHabitacion;

    public ServicioAnularHotelReserva(RepositorioReservaHotel repositorioReservaHotel, RepositorioHabitacion repositorioHabitacion) {
        this.repositorioReservaHotel = repositorioReservaHotel;
        this.repositorioHabitacion = repositorioHabitacion;
    }

    public void ejecutar(ReservaHotel reservaHotel){
        ValidadorArgumento.validarObligatorio(reservaHotel , "No existe una reserva para anular");
        reservaHotel.cancelarReserva();
        repositorioReservaHotel.actualizar(reservaHotel);
        repositorioHabitacion.actualizar(reservaHotel.liberarHabitacionReserva());
    }
}
