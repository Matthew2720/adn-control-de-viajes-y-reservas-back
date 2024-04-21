package com.ceiba.reservas.hotel.adaptador.repositorio;

import com.ceiba.reservas.hotel.modelo.entidad.Hotel;
import com.ceiba.reservas.hotel.puerto.repositorio.RepositorioHabitacionHotel;
import com.ceiba.reservas.infraestructura.jdbc.MapperResult;
import com.ceiba.reservas.vuelo.modelo.entidad.Ciudad;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoHotel implements RowMapper<Hotel>, MapperResult{

    private final RepositorioHabitacionHotel repositorioHabitacionHotel;

    public MapeoHotel(RepositorioHabitacionHotel repositorioHabitacionHotel){
        this.repositorioHabitacionHotel = repositorioHabitacionHotel;
    }

    @Override
    public Hotel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        Ciudad ciudad = Ciudad.valueOf(resultSet.getString("ubicacion"));
        double costoPorNoche = resultSet.getDouble("costo_por_noche");

        return Hotel.reconstruir(id,nombre,ciudad,repositorioHabitacionHotel.obtenerPorHotel(id), costoPorNoche);
    }
}
