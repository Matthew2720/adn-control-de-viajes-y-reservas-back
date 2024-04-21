package com.ceiba.reservas.vuelo.adaptador.repositorio;

import com.ceiba.reservas.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.reservas.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.reservas.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reservas.vuelo.modelo.entidad.Vuelo;
import com.ceiba.reservas.vuelo.puerto.repositorio.RepositorioVuelo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioVueloMysql implements RepositorioVuelo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "vuelo", value = "obtenerporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "vuelo", value = "actualizarasientosvuelo")
    private static String sqlActualizarAsientosDisponibles;

    public RepositorioVueloMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public void actualizar(Vuelo vuelo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", vuelo.getId());
        paramSource.addValue("asientos_disponibles", vuelo.getAsientosDisponibles());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .update(sqlActualizarAsientosDisponibles, paramSource);
    }

    @Override
    public Vuelo obtener(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id",id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlObtenerPorId,
                                paramSource, new MapeoVuelo()));
    }
}
