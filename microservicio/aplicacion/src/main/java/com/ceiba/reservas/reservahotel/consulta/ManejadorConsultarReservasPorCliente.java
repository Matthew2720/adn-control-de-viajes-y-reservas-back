package com.ceiba.reservas.reservahotel.consulta;

import com.ceiba.reservas.reservahotel.modelo.entidad.ReservaHotel;
import com.ceiba.reservas.reservahotel.puerto.repositorio.dao.DaoReservaHotel;
import com.ceiba.reservas.vuelo.modelo.dto.VueloDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarReservasPorCliente {

    private final DaoReservaHotel daoReservaHotel;

    public ManejadorConsultarReservasPorCliente(DaoReservaHotel daoReservaHotel) {
        this.daoReservaHotel = daoReservaHotel;
    }

    public List<ReservaHotel> ejecutar(Long idCliente) {
        return daoReservaHotel.obtenerReservasPorCliente(idCliente);    }
}
