package com.ceiba.reservas.factura;

import com.ceiba.reservas.factura.modelo.entidad.ProductoFacturar;
import com.ceiba.reservas.producto.entidad.Producto;

public class ProductoFacturarTestDataBuilder {
    private Integer cantidad;
    private Producto producto;
    private Long id;

    public ProductoFacturarTestDataBuilder conCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public ProductoFacturarTestDataBuilder conProducto(Producto producto) {
        this.producto = producto;
        return this;
    }

    public ProductoFacturarTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ProductoFacturar build() {
        return ProductoFacturar.crear(cantidad, producto);
    }

    public ProductoFacturar reconstruir() {
        return ProductoFacturar.reconstruir(id, cantidad, producto);
    }
}
