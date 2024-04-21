package com.ceiba.reservas.factura.modelo.entidad;

import com.ceiba.reservas.cliente.entidad.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SolicitudFacturar {

    private final Cliente cliente;
    private final List<ProductoFacturar> productosFacturar;

}
