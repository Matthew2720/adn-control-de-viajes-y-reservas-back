package com.ceiba.reservas.producto.puerto;

import com.ceiba.reservas.producto.entidad.Producto;

public interface RepositorioProducto {

    Producto obtener(Long id);
}
