package com.ceiba.reservas.habitacion.adaptador.repositorio;

import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;
import com.ceiba.reservas.habitacion.puerto.RepositorioHabitacion;
import com.ceiba.reservas.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.reservas.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.reservas.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioHabitacionMysql implements RepositorioHabitacion {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "habitacion", value = "obtenerporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "habitacion", value = "actualizar")
    private static String sqlActualizar;

    public RepositorioHabitacionMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public void actualizar(Habitacion habitacion) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("numero_habitacion", habitacion.getNumeroHabitacion());
        paramSource.addValue("disponibilidad", habitacion.estaDisponible());
        paramSource.addValue("id", habitacion.getId());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizar,
                paramSource);
    }

    @Override
    public Habitacion obtener(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPorId,
                        paramSource, new MapeoHabitacion())
                );
    }
}
