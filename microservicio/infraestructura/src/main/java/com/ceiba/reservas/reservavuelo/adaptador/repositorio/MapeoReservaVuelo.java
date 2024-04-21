package com.ceiba.reservas.reservavuelo.adaptador.repositorio;

import com.ceiba.reservas.cliente.puerto.RepositorioCliente;
import com.ceiba.reservas.infraestructura.jdbc.MapperResult;
import com.ceiba.reservas.reservavuelo.modelo.entidad.EstadoReserva;
import com.ceiba.reservas.reservavuelo.modelo.entidad.ReservaVuelo;
import com.ceiba.reservas.vuelo.puerto.repositorio.RepositorioVuelo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class MapeoReservaVuelo implements RowMapper<ReservaVuelo>, MapperResult {

    private final RepositorioCliente repositorioCliente;
    private final RepositorioVuelo repositorioVuelo;

    public MapeoReservaVuelo(RepositorioCliente repositorioCliente, RepositorioVuelo repositorioVuelo) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioVuelo = repositorioVuelo;
    }

    @Override
    public ReservaVuelo mapRow(ResultSet resulSet, int rowNum) throws SQLException {
        long id = resulSet.getLong("id");
        long idCliente = resulSet.getLong("id_cliente");
        long idVuelo = resulSet.getLong("id_vuelo");
        LocalDateTime fechaHoraReserva = resulSet.getTimestamp("fecha_hora_reserva").toLocalDateTime();
        int pasajeros = resulSet.getInt("pasajeros");
        BigDecimal precioFinal = resulSet.getBigDecimal("precio_final");
        EstadoReserva estadoReserva = EstadoReserva.valueOf(resulSet.getString("estado_reserva"));

        return ReservaVuelo.reconstruir(id,repositorioVuelo.obtener(idVuelo),fechaHoraReserva,
                pasajeros,precioFinal, estadoReserva, repositorioCliente.obtener(idCliente));
    }

}
