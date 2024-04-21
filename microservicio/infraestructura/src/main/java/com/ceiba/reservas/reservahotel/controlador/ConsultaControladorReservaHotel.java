package com.ceiba.reservas.reservahotel.controlador;

import com.ceiba.reservas.reservahotel.consulta.ManejadorConsultarReservasPorCliente;
import com.ceiba.reservas.reservahotel.modelo.entidad.ReservaHotel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@Tag(name="Controlador consulta reserva hotel")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaControladorReservaHotel {

    private final ManejadorConsultarReservasPorCliente manejadorConsultarReservasPorCliente;

    public ConsultaControladorReservaHotel(ManejadorConsultarReservasPorCliente manejadorConsultarReservasPorCliente) {
        this.manejadorConsultarReservasPorCliente = manejadorConsultarReservasPorCliente;
    }

    @GetMapping("hotel/{id-cliente}")
    @Operation(summary = "Busqueda", description = "Metodo utilizado para consultar las reservas por cliente")
    public List<ReservaHotel> obtenerReservasPorCliente(@PathVariable("id-cliente") Long idCliente){
        return manejadorConsultarReservasPorCliente.ejecutar(idCliente);
    }
}
