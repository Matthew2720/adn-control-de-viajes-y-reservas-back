package com.ceiba.reservas.vuelo.puerto.dao;

import com.ceiba.reservas.vuelo.modelo.dto.VueloDTO;

import java.util.List;

public interface DaoVuelo {
    List<VueloDTO> obtenerVuelosDisponibles();
}
