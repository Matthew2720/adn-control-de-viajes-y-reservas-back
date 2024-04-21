package com.ceiba.reservas.factura.puerto.repositorio;

import com.ceiba.reservas.factura.modelo.entidad.Factura;
import com.ceiba.reservas.factura.modelo.entidad.ProductoFacturar;

import java.util.List;

public interface RepositorioProductoFacturar {

    void guardarPorFactura(Factura factura, Long idFactura);

    List<ProductoFacturar> obtenerPorFactura(Long idFactura);
}
