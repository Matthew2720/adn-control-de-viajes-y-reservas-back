package com.ceiba.reservas.reservavuelo.controlador;

import com.ceiba.reservas.reservavuelo.comando.consulta.ManejadorConsultarReservasVueloPorCliente;
import com.ceiba.reservas.reservavuelo.modelo.entidad.ReservaVuelo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@Tag(name="Controlador consulta reserva vuelo")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaControladorReservaVuelo {

    private final ManejadorConsultarReservasVueloPorCliente manejadorConsultarReservasVueloPorCliente;

    public ConsultaControladorReservaVuelo(ManejadorConsultarReservasVueloPorCliente manejadorConsultarReservasVueloPorCliente) {
        this.manejadorConsultarReservasVueloPorCliente = manejadorConsultarReservasVueloPorCliente;
    }

    @GetMapping("vuelo/{id-cliente}")
    @Operation(summary = "Busqueda", description = "Metodo utilizado para consultar las reservas por cliente")
    public List<ReservaVuelo> obtenerReservasPorCliente(@PathVariable("id-cliente") Long idCliente){
        return manejadorConsultarReservasVueloPorCliente.ejecutar(idCliente);
    }
}
