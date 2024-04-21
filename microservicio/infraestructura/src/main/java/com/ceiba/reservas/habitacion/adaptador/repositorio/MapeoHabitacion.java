package com.ceiba.reservas.habitacion.adaptador.repositorio;

import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;
import com.ceiba.reservas.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoHabitacion implements RowMapper<Habitacion>, MapperResult{

    @Override
    public Habitacion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        long id = resultSet.getLong("id");
        int numeroHabitacion = resultSet.getInt("numero_habitacion");
        boolean disponibilidad = resultSet.getBoolean("disponibilidad");

        return Habitacion.reconstruir(id, numeroHabitacion, disponibilidad);
    }
}
