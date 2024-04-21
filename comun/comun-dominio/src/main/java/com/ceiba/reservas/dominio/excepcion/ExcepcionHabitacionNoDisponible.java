package com.ceiba.reservas.dominio.excepcion;

public class ExcepcionHabitacionNoDisponible extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionHabitacionNoDisponible(String mensaje) {
        super(mensaje);
    }
}
