package com.ceiba.reservas.reservavuelo.adaptador.repositorio;

import com.ceiba.reservas.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.reservas.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.reservas.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reservas.reservavuelo.modelo.entidad.ReservaVuelo;
import com.ceiba.reservas.reservavuelo.puerto.repositorio.RepositorioReservaVuelo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioReservaVueloMysql implements RepositorioReservaVuelo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoReservaVuelo mapeoReservaVuelo;

    @SqlStatement(namespace = "reservavuelo", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "reservavuelo", value = "actualizarestado")
    private static String sqlActualizarEstado;

    @SqlStatement(namespace = "reservavuelo", value = "obtenerporid")
    private static String sqlObtenerPorId;

    public RepositorioReservaVueloMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoReservaVuelo mapeoReservaVuelo) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoReservaVuelo = mapeoReservaVuelo;
    }

    @Override
    public Long guardar(ReservaVuelo reservaVuelo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_cliente", reservaVuelo.getCliente().getId());
        paramSource.addValue("id_vuelo", reservaVuelo.getVuelo().getId());
        paramSource.addValue("fecha_hora_reserva", reservaVuelo.getFechaHoraReserva());
        paramSource.addValue("pasajeros", reservaVuelo.getPasajeros());
        paramSource.addValue("precio_final",reservaVuelo.getPrecioFinal());
        paramSource.addValue("estado_reserva",reservaVuelo.getEstadoReserva().name());
        return this.customNamedParameterJdbcTemplate.crear(paramSource,sqlCrear);
    }

    @Override
    public void actualizar(ReservaVuelo reservaVuelo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("estado_reserva", reservaVuelo.getEstadoReserva().toString());
        paramSource.addValue("id", reservaVuelo.getId());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizarEstado, paramSource);
    }

    @Override
    public ReservaVuelo obtener(Long idVuelo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idVuelo);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(()->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlObtenerPorId, paramSource, mapeoReservaVuelo));
    }
}
