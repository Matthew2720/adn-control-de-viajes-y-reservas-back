package com.ceiba.reservas.vuelo.adaptador.dao;

import com.ceiba.reservas.infraestructura.jdbc.MapperResult;
import com.ceiba.reservas.vuelo.modelo.dto.VueloDTO;
import com.ceiba.reservas.vuelo.modelo.entidad.Ciudad;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class MapeoVueloResumen implements RowMapper<VueloDTO>, MapperResult {
    @Override
    public VueloDTO mapRow(ResultSet resulSet, int rowNum) throws SQLException {

        long id = resulSet.getLong("id");
        Ciudad origen = Ciudad.valueOf(resulSet.getString("origen"));
        Ciudad destino = Ciudad.valueOf(resulSet.getString("destino"));
        LocalDateTime fechaSalida = resulSet.getTimestamp("fecha_salida").toLocalDateTime();
        LocalDateTime fechaLlegada = resulSet.getTimestamp("fecha_llegada").toLocalDateTime();
        int asientosDisponibles = resulSet.getInt("asientos_disponibles");
        BigDecimal precioBase = resulSet.getBigDecimal("precio_base");

        return new VueloDTO(id,origen,destino,fechaSalida,fechaLlegada,asientosDisponibles,precioBase);
    }
}
