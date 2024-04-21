package com.ceiba.reservas.dominio.excepcion;

public class ExcepcionVueloNoDisponible extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionVueloNoDisponible(String mensaje) {
        super(mensaje);
    }
}
