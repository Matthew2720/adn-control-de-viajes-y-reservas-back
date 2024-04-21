package com.ceiba.reservas.hotel.adaptador.dao;

import com.ceiba.reservas.hotel.adaptador.repositorio.MapeoHotel;
import com.ceiba.reservas.hotel.modelo.entidad.Hotel;
import com.ceiba.reservas.hotel.puerto.dao.HotelDAO;
import com.ceiba.reservas.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.reservas.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoHotelMysql implements HotelDAO {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoHotel mapeoHotel;

    @SqlStatement(namespace = "hotel", value = "obtenerhoteles")
    private static String sqlObtenerHabitaciones;

    public DaoHotelMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoHotel mapeoHotel) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoHotel = mapeoHotel;
    }


    @Override
    public List<Hotel> obtenerHotelesDisponibles() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerHabitaciones, mapeoHotel);

    }
}
