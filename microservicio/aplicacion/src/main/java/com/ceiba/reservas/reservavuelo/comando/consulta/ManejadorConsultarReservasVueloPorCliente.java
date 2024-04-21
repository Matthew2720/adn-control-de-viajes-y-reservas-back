package com.ceiba.reservas.reservavuelo.comando.consulta;

import com.ceiba.reservas.reservahotel.modelo.entidad.ReservaHotel;
import com.ceiba.reservas.reservavuelo.modelo.entidad.ReservaVuelo;
import com.ceiba.reservas.reservavuelo.puerto.dao.DaoReservaVuelo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarReservasVueloPorCliente {

    private final DaoReservaVuelo daoReservaVuelo;

    public ManejadorConsultarReservasVueloPorCliente(DaoReservaVuelo daoReservaVuelo) {
        this.daoReservaVuelo = daoReservaVuelo;
    }

    public List<ReservaVuelo> ejecutar(Long idCliente) {
        return daoReservaVuelo.obtenerPorIdCliente(idCliente);    }
}
