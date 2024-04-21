package com.ceiba.reservas.reservavuelo.puerto.repositorio;

import com.ceiba.reservas.reservavuelo.modelo.entidad.ReservaVuelo;

public interface RepositorioReservaVuelo {

    Long guardar(ReservaVuelo reservaVuelo);

    void actualizar(ReservaVuelo reservaVuelo);

    ReservaVuelo obtener(Long idVuelo);
}
