package com.ceiba.reservas.habitacion.puerto;

import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;

public interface RepositorioHabitacion {
    void actualizar(Habitacion habitacion);

    Habitacion obtener(Long id);
}
