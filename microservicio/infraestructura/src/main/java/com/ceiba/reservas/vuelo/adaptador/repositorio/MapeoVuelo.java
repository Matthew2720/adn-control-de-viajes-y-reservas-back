package com.ceiba.reservas.vuelo.adaptador.repositorio;

import com.ceiba.reservas.infraestructura.jdbc.MapperResult;
import com.ceiba.reservas.vuelo.modelo.entidad.Ciudad;
import com.ceiba.reservas.vuelo.modelo.entidad.Vuelo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class MapeoVuelo implements RowMapper<Vuelo>, MapperResult {

    @Override
    public Vuelo mapRow(ResultSet resulSet, int rowNum) throws SQLException {

        long id = resulSet.getLong("id");
        Ciudad origen = Ciudad.valueOf(resulSet.getString("origen"));
        Ciudad destino = Ciudad.valueOf(resulSet.getString("destino"));
        LocalDateTime fechaSalida = resulSet.getTimestamp("fecha_salida").toLocalDateTime();
        LocalDateTime fechaLlegada = resulSet.getTimestamp("fecha_llegada").toLocalDateTime();
        int asientosDisponibles = resulSet.getInt("asientos_disponibles");
        BigDecimal precioBase = resulSet.getBigDecimal("precio_base");

        return Vuelo.reconstruir(id,origen,destino,fechaSalida,fechaLlegada,asientosDisponibles,precioBase);
    }
}
