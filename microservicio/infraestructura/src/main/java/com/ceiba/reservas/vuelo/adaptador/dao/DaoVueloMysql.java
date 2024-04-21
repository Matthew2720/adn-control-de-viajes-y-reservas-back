package com.ceiba.reservas.vuelo.adaptador.dao;

import com.ceiba.reservas.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.reservas.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reservas.vuelo.modelo.dto.VueloDTO;
import com.ceiba.reservas.vuelo.puerto.dao.DaoVuelo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoVueloMysql implements DaoVuelo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoVueloResumen mapeoVuelo;

    @SqlStatement(namespace = "vuelo", value = "obtenerdisponibles")
    private static String sqlObtenerVuelosDisponibles;

    public DaoVueloMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoVueloResumen mapeoVuelo) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoVuelo = mapeoVuelo;
    }


    @Override
    public List<VueloDTO> obtenerVuelosDisponibles() {
       return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
               .query(sqlObtenerVuelosDisponibles, mapeoVuelo);
    }
}
