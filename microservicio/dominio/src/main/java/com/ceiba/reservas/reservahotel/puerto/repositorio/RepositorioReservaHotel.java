package com.ceiba.reservas.reservahotel.puerto.repositorio;

import com.ceiba.reservas.reservahotel.modelo.entidad.ReservaHotel;

import java.util.List;

public interface RepositorioReservaHotel {
    Long guardar(ReservaHotel reservaHotel);
    ReservaHotel obtener(Long id);

    void actualizar(ReservaHotel reservaHotel);
}
