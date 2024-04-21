package com.ceiba.reservas.reservavuelo.puerto.dao;


import com.ceiba.reservas.reservavuelo.modelo.entidad.ReservaVuelo;

import java.util.List;

public interface DaoReservaVuelo {
    List<ReservaVuelo> obtenerResumenDeReservas();
    List<ReservaVuelo> obtenerPorIdCliente(Long idCliente);
}
