package com.ceiba.reservas.reservahotel.adaptador.repositorio;

import com.ceiba.reservas.cliente.puerto.RepositorioCliente;
import com.ceiba.reservas.habitacion.puerto.RepositorioHabitacion;
import com.ceiba.reservas.hotel.puerto.repositorio.RepositorioHotel;
import com.ceiba.reservas.reservahotel.modelo.entidad.ReservaHotel;
import org.springframework.jdbc.core.RowMapper;
import com.ceiba.reservas.infraestructura.jdbc.MapperResult;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class MapeoReservaHotel implements RowMapper<ReservaHotel>, MapperResult{

    private final RepositorioCliente repositorioCliente;
    private final RepositorioHabitacion repositorioHabitacion;
    private final RepositorioHotel repositorioHotel;


    public MapeoReservaHotel(RepositorioCliente repositorioCliente, RepositorioHabitacion repositorioHabitacion, RepositorioHotel repositorioHotel) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioHabitacion = repositorioHabitacion;
        this.repositorioHotel = repositorioHotel;
    }

    @Override
    public ReservaHotel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        long id = resultSet.getLong("id");
        LocalDateTime fechaEntrada = resultSet.getTimestamp("fecha_entrada").toLocalDateTime();
        LocalDateTime fechaSalida = resultSet.getTimestamp("fecha_salida").toLocalDateTime();
        long idHotel = resultSet.getLong("hotel_id");
        long idHabitacion = resultSet.getLong("habitacion_id");
        long idCliente = resultSet.getLong("cliente_id");
        double costoTotal = resultSet.getDouble("costo_total");
        String estado = resultSet.getString("estado");

        return ReservaHotel.reconstruir(id, fechaEntrada, fechaSalida, repositorioHotel.obtener(idHotel), repositorioHabitacion.obtener(idHabitacion),
                repositorioCliente.obtener(idCliente), costoTotal, estado);
    }
}
