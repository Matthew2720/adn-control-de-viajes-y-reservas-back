package com.ceiba.reservas.cliente.adaptador.repositorio;

import com.ceiba.reservas.cliente.entidad.Cliente;
import com.ceiba.reservas.cliente.puerto.RepositorioCliente;
import com.ceiba.reservas.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.reservas.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.reservas.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioClienteMysql implements RepositorioCliente {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "cliente", value = "obtenerporid")
    private static String sqlObtenerPorId;

    public RepositorioClienteMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Cliente obtener(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPorId,
                        paramSource, new MapeoCliente()));
    }

}
