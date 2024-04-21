package com.ceiba.reservas.dominio.excepcion;

public class ExcepcionAsientosNoDisponibles extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionAsientosNoDisponibles(String mensaje) {
        super(mensaje);
    }
}
