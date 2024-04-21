package com.ceiba.reservas.factura.servicio;

import com.ceiba.reservas.dominio.ValidadorArgumento;
import com.ceiba.reservas.factura.modelo.entidad.Factura;
import com.ceiba.reservas.factura.puerto.repositorio.RepositorioFactura;

public class ServicioAnular {

    private final RepositorioFactura repositorioFactura;

    public ServicioAnular(RepositorioFactura repositorioFactura) {
        this.repositorioFactura = repositorioFactura;
    }

    public void ejecutar(Factura factura) {
        ValidadorArgumento.validarObligatorio(factura, "No existe una factura para anular");
        factura.anular();
        repositorioFactura.actualizarEstado(factura);
    }
}
