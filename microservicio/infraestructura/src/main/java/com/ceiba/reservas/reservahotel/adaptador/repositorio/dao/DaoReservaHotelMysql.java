package com.ceiba.reservas.reservahotel.adaptador.repositorio.dao;

import com.ceiba.reservas.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.reservas.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reservas.reservahotel.adaptador.repositorio.MapeoReservaHotel;
import com.ceiba.reservas.reservahotel.modelo.entidad.ReservaHotel;
import com.ceiba.reservas.reservahotel.puerto.repositorio.dao.DaoReservaHotel;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoReservaHotelMysql implements DaoReservaHotel {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoReservaHotel mapeoReservaHotel;

    @SqlStatement(namespace = "reservahotel", value = "obtenerporidcliente")
    private static String sqlObtenerPorIdCliente;

    public DaoReservaHotelMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoReservaHotel mapeoReservaHotel) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoReservaHotel = mapeoReservaHotel;
    }


    @Override
    public List<ReservaHotel> obtenerReservasPorCliente(Long idCliente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("cliente_id", idCliente);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerPorIdCliente, paramSource, mapeoReservaHotel);
    }
}
