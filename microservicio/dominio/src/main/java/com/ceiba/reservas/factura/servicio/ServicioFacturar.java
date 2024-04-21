package com.ceiba.reservas.factura.servicio;

import com.ceiba.reservas.factura.modelo.entidad.Factura;
import com.ceiba.reservas.factura.modelo.entidad.SolicitudFacturar;
import com.ceiba.reservas.factura.puerto.repositorio.RepositorioFactura;

public class ServicioFacturar {
    private final RepositorioFactura repositorioFactura;

    public ServicioFacturar(RepositorioFactura repositorioFactura) {
        this.repositorioFactura = repositorioFactura;
    }

    public Long ejecutar(SolicitudFacturar solicitudFacturar) {
        Factura factura = Factura.crear(solicitudFacturar);
        return repositorioFactura.guardar(factura);
    }
}
