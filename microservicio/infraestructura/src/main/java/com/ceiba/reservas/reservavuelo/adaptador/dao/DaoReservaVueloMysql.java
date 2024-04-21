package com.ceiba.reservas.reservavuelo.adaptador.dao;

import com.ceiba.reservas.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.reservas.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reservas.reservavuelo.adaptador.repositorio.MapeoReservaVuelo;
import com.ceiba.reservas.reservavuelo.modelo.entidad.ReservaVuelo;
import com.ceiba.reservas.reservavuelo.puerto.dao.DaoReservaVuelo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoReservaVueloMysql implements DaoReservaVuelo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoReservaVuelo mapeoReservaVuelo;

    @SqlStatement(namespace = "reservavuelo" , value = "obteneractivas")
    private static String sqlObtenerActivas;

    @SqlStatement(namespace = "reservavuelo" , value = "obtenerporidcliente")
    private static String sqlObtenerPorIdCliente;

    public DaoReservaVueloMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoReservaVuelo mapeoReservaVuelo) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoReservaVuelo = mapeoReservaVuelo;
    }


    @Override
    public List<ReservaVuelo> obtenerResumenDeReservas() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerActivas, mapeoReservaVuelo);
    }

    @Override
    public List<ReservaVuelo> obtenerPorIdCliente(Long idCliente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_cliente", idCliente);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerPorIdCliente, paramSource, mapeoReservaVuelo);
    }


}
