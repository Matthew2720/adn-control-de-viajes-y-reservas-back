package com.ceiba.reservas.hotel.adaptador.repositorio;

import com.ceiba.reservas.hotel.modelo.entidad.Hotel;
import com.ceiba.reservas.hotel.puerto.repositorio.RepositorioHotel;
import com.ceiba.reservas.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.reservas.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.reservas.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioHotelMysql implements RepositorioHotel {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoHotel mapeoHotel;

    @SqlStatement(namespace = "hotel", value = "obtenerporid")
    private static String sqlObtenerPorId;

    public RepositorioHotelMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoHotel mapeoHotel) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoHotel = mapeoHotel;
    }

    @Override
    public Hotel obtener(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() -> this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerPorId, paramSource, mapeoHotel));
    }
}
