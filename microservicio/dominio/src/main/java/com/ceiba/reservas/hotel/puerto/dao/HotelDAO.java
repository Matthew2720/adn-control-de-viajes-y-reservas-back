package com.ceiba.reservas.hotel.puerto.dao;

import com.ceiba.reservas.hotel.modelo.entidad.Hotel;

import java.util.List;

public interface HotelDAO {
    List<Hotel> obtenerHotelesDisponibles();
}
