package com.ceiba.reservas.factura.adaptador.repositorio;

import com.ceiba.reservas.factura.modelo.entidad.ProductoFacturar;
import com.ceiba.reservas.infraestructura.jdbc.MapperResult;
import com.ceiba.reservas.producto.puerto.RepositorioProducto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoProductoFacturar implements RowMapper<ProductoFacturar>, MapperResult {

    private final RepositorioProducto repositorioProducto;

    public MapeoProductoFacturar(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    @Override
    public ProductoFacturar mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        long id = resultSet.getLong("id");
        long idProducto = resultSet.getLong("id_producto");
        int cantidad = resultSet.getInt("cantidad");

        return ProductoFacturar.reconstruir(id, cantidad, repositorioProducto.obtener(idProducto));
    }

}
