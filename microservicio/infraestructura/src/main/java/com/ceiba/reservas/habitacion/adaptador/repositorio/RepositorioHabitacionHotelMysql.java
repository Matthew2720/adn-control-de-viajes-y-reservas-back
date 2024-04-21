package com.ceiba.reservas.habitacion.adaptador.repositorio;

import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;
import com.ceiba.reservas.hotel.puerto.repositorio.RepositorioHabitacionHotel;
import com.ceiba.reservas.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.reservas.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioHabitacionHotelMysql implements RepositorioHabitacionHotel {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoHabitacion mapeoHabitacion;

    @SqlStatement(namespace = "habitacionhotel", value = "obtenertodasporhotel")
    private static String sqlObtenerPorHotel;

    public RepositorioHabitacionHotelMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoHabitacion mapeoHabitacion) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoHabitacion = mapeoHabitacion;
    }


    @Override
    public List<Habitacion> obtenerPorHotel(Long idHotel) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_hotel", idHotel);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerPorHotel, paramSource, mapeoHabitacion);
    }
}
