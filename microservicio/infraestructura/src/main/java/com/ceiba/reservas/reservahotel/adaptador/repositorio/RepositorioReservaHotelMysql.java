package com.ceiba.reservas.reservahotel.adaptador.repositorio;

import com.ceiba.reservas.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.reservas.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.reservas.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reservas.reservahotel.modelo.entidad.ReservaHotel;
import com.ceiba.reservas.reservahotel.puerto.repositorio.RepositorioReservaHotel;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioReservaHotelMysql implements RepositorioReservaHotel {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoReservaHotel mapeoReservaHotel;

    @SqlStatement(namespace = "reservahotel", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "reservahotel", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "reservahotel", value = "obtener")
    private static String sqlObtenerPorId;

    public RepositorioReservaHotelMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoReservaHotel mapeoReservaHotel) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoReservaHotel = mapeoReservaHotel;
    }

    @Override
    public Long guardar(ReservaHotel reservaHotel) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fecha_entrada", reservaHotel.getFechaEntrada());
        paramSource.addValue("fecha_salida", reservaHotel.getFechaSalida());
        paramSource.addValue("hotel_id", reservaHotel.getHotel().getId());
        paramSource.addValue("habitacion_id", reservaHotel.getHabitacion().getId());
        paramSource.addValue("cliente_id", reservaHotel.getCliente().getId());
        paramSource.addValue("costo_total", reservaHotel.getCostoTotal());
        paramSource.addValue("estado", reservaHotel.getEstado());
        return this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
    }

    @Override
    public ReservaHotel obtener(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(()->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlObtenerPorId, paramSource, mapeoReservaHotel));
    }

    @Override
    public void actualizar(ReservaHotel reservaHotel) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fecha_entrada", reservaHotel.getFechaEntrada());
        paramSource.addValue("fecha_salida", reservaHotel.getFechaSalida());
        paramSource.addValue("hotel_id", reservaHotel.getHotel().getId());
        paramSource.addValue("habitacion_id", reservaHotel.getHabitacion().getId());
        paramSource.addValue("cliente_id", reservaHotel.getCliente().getId());
        paramSource.addValue("costo_total", reservaHotel.getCostoTotal());
        paramSource.addValue("estado", reservaHotel.getEstado());
        paramSource.addValue("id", reservaHotel.getId());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizar, paramSource);
    }
}
