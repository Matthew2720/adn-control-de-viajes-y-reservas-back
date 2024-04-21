package com.ceiba.reservas.factura.comando.manejador;

import com.ceiba.reservas.ComandoRespuesta;
import com.ceiba.reservas.factura.comando.ComandoSolicitudFacturar;
import com.ceiba.reservas.factura.comando.fabrica.FabricaSolicitudFacturar;
import com.ceiba.reservas.factura.servicio.ServicioFacturar;
import com.ceiba.reservas.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorFacturar implements ManejadorComandoRespuesta<ComandoSolicitudFacturar, ComandoRespuesta<Long>> {

    private final FabricaSolicitudFacturar fabricaSolicitudFacturar;
    private final ServicioFacturar servicioFacturar;

    public ManejadorFacturar(FabricaSolicitudFacturar fabricaSolicitudFacturar, ServicioFacturar servicioFacturar) {
        this.fabricaSolicitudFacturar = fabricaSolicitudFacturar;
        this.servicioFacturar = servicioFacturar;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitudFacturar comandoSolicitudFacturar) {
        return new ComandoRespuesta<>(servicioFacturar
                .ejecutar(fabricaSolicitudFacturar.crear(comandoSolicitudFacturar)));
    }
}
