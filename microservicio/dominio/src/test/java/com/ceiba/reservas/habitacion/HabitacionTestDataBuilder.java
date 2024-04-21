package com.ceiba.reservas.habitacion;

import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;

public class HabitacionTestDataBuilder {
    private Long id;
    private int numeroHabitacion;
    private boolean disponibilidad;

    public HabitacionTestDataBuilder conHabitacionPorDefecto() {
        this.id = 1L;
        this.numeroHabitacion = 101;
        this.disponibilidad = true;
        return this;
    }

    public HabitacionTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public HabitacionTestDataBuilder conNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
        return this;
    }

    public HabitacionTestDataBuilder conDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
        return this;
    }

    public Habitacion reconstruir() {
        return Habitacion.reconstruir(id, numeroHabitacion, disponibilidad);
    }
}

