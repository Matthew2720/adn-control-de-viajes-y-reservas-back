package com.ceiba.reservas.reservahotel.manejador;

import com.ceiba.reservas.ComandoRespuesta;
import com.ceiba.reservas.manejador.ManejadorComandoRespuesta;
import com.ceiba.reservas.reservahotel.ComandoSolicitudReservaHotel;
import com.ceiba.reservas.reservahotel.fabrica.FabricaSolicitudReservaHotel;
import com.ceiba.reservas.reservahotel.servicio.ServicioHotelReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorReservaHotel implements ManejadorComandoRespuesta<ComandoSolicitudReservaHotel, ComandoRespuesta<Long>> {

    private final FabricaSolicitudReservaHotel fabricaSolicitudReservaHotel;
    private final ServicioHotelReserva servicioHotelReserva;

    public ManejadorReservaHotel(FabricaSolicitudReservaHotel fabricaSolicitudReservaHotel, ServicioHotelReserva servicioHotelReserva) {
        this.fabricaSolicitudReservaHotel = fabricaSolicitudReservaHotel;
        this.servicioHotelReserva = servicioHotelReserva;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitudReservaHotel comandoSolicitudReservaHotel) {
        return new ComandoRespuesta<>(
                servicioHotelReserva.ejecutar(fabricaSolicitudReservaHotel.crear(comandoSolicitudReservaHotel))
        );
    }
}
