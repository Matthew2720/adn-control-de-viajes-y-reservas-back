package com.ceiba.reservas.hotel.puerto.repositorio;

import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;

import java.util.List;

public interface RepositorioHabitacionHotel {
    List<Habitacion> obtenerPorHotel(Long idHotel);
}
