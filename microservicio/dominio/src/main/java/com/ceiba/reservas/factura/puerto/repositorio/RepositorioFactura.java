package com.ceiba.reservas.factura.puerto.repositorio;

import com.ceiba.reservas.factura.modelo.entidad.Factura;

public interface RepositorioFactura {
    Long guardar(Factura factura);
    Factura obtener(Long id);
    void actualizarEstado(Factura factura);
}
