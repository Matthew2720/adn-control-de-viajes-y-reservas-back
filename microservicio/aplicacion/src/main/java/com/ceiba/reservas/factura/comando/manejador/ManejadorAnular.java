package com.ceiba.reservas.factura.comando.manejador;

import com.ceiba.reservas.factura.comando.ComandoAnular;
import com.ceiba.reservas.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.reservas.factura.servicio.ServicioAnular;
import com.ceiba.reservas.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAnular implements ManejadorComando<ComandoAnular> {

    private final ServicioAnular servicioAnular;
    private final RepositorioFactura repositorioFactura;

    public ManejadorAnular(ServicioAnular servicioAnular, RepositorioFactura repositorioFactura) {
        this.servicioAnular = servicioAnular;
        this.repositorioFactura = repositorioFactura;
    }

    @Override
    public void ejecutar(ComandoAnular comandoAnular) {
        servicioAnular.ejecutar(repositorioFactura.obtener(comandoAnular.getIdFactura()));
    }
}
