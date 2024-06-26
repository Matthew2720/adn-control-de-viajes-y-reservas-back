package com.ceiba.reservas.factura.comando.fabrica;

import com.ceiba.reservas.cliente.puerto.RepositorioCliente;
import com.ceiba.reservas.factura.comando.ComandoProductoFacturar;
import com.ceiba.reservas.factura.comando.ComandoSolicitudFacturar;
import com.ceiba.reservas.factura.modelo.entidad.ProductoFacturar;
import com.ceiba.reservas.factura.modelo.entidad.SolicitudFacturar;
import com.ceiba.reservas.producto.puerto.RepositorioProducto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FabricaSolicitudFacturar {

    private final RepositorioCliente repositorioCliente;
    private final RepositorioProducto repositorioProducto;

    public FabricaSolicitudFacturar(RepositorioCliente repositorioCliente, RepositorioProducto repositorioProducto) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioProducto = repositorioProducto;
    }

    public SolicitudFacturar crear(ComandoSolicitudFacturar comandoSolicitudFacturar) {
        return new SolicitudFacturar(repositorioCliente.obtener(comandoSolicitudFacturar.getIdCliente()),
                obtenerProductos(comandoSolicitudFacturar.getComandoProductosFacturar())
        );
    }

    private List<ProductoFacturar> obtenerProductos(List<ComandoProductoFacturar> comandoProductosFacturar) {
        return comandoProductosFacturar.stream().map(comandoProducto ->
                        ProductoFacturar.crear(
                                comandoProducto.getCantidad(),
                                repositorioProducto.obtener(comandoProducto.getIdProducto())))
                                .collect(Collectors.toList());
    }
}
