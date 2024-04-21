package com.ceiba.reservas.reservahotel.puerto.repositorio.dao;

import com.ceiba.reservas.reservahotel.modelo.entidad.ReservaHotel;

import java.util.List;

public interface DaoReservaHotel {
    List<ReservaHotel> obtenerReservasPorCliente(Long idCliente);
}
