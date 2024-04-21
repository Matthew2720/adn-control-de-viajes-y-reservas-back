package com.ceiba.reservas.infraestructura.jdbc;

public interface EjecutarBD<T> {
    T ejecutar();
}