package com.ceiba.reservas.habitacion.modelo.entidad;

import com.ceiba.reservas.dominio.ValidadorArgumento;
import lombok.Getter;

@Getter
public final class Habitacion {
    private Long id;
    private int numeroHabitacion;
    private boolean disponibilidad;

    private Habitacion(Long id, int numeroHabitacion, boolean disponibilidad) {
        this.id = id;
        this.numeroHabitacion = numeroHabitacion;
        this.disponibilidad = disponibilidad;
    }

    public static Habitacion reconstruir(Long id, int numeroHabitacion, boolean disponibilidad){
        ValidadorArgumento.validarObligatorio(id, "El id es requerido para la habitacion");
        ValidadorArgumento.validarObligatorio(numeroHabitacion, "El numero de la habitacion es requerido");
        ValidadorArgumento.validarObligatorio(disponibilidad, "La disponibilidad de la habitacion es requerida");
        return new Habitacion(id, numeroHabitacion, disponibilidad);
    }

    public boolean estaDisponible(){
        return disponibilidad;
    }

    public void liberarHabitacion(){
        this.disponibilidad = true;
    }

    public void ocuparHabitacion(){
        this.disponibilidad = false;
    }
}
