package com.ceiba.reservas.dominio.excepcion;

public class ExcepcionCancelacionNoDisponible extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionCancelacionNoDisponible(String mensaje) {
        super(mensaje);
    }
}
