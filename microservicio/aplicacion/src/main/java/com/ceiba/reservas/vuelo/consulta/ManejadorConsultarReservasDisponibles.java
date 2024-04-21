package com.ceiba.reservas.vuelo.consulta;

import com.ceiba.reservas.vuelo.modelo.dto.VueloDTO;
import com.ceiba.reservas.vuelo.puerto.dao.DaoVuelo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarReservasDisponibles {

    private final DaoVuelo daoVuelo;


    public ManejadorConsultarReservasDisponibles(DaoVuelo daoVuelo) {
        this.daoVuelo = daoVuelo;
    }

    public List<VueloDTO> ejecutar() {
        return daoVuelo.obtenerVuelosDisponibles();
    }
}
