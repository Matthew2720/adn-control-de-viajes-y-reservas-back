package com.ceiba.reservas.vuelo.controlador;

import com.ceiba.reservas.vuelo.consulta.ManejadorConsultarReservasDisponibles;
import com.ceiba.reservas.vuelo.modelo.dto.VueloDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vuelos")
@Tag(name = "Controlador consulta vuelo")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaControladorVuelo {

    private final ManejadorConsultarReservasDisponibles manejadorConsultarReservasDisponibles;

    public ConsultaControladorVuelo(ManejadorConsultarReservasDisponibles manejadorConsultarReservasDisponibles) {
        this.manejadorConsultarReservasDisponibles = manejadorConsultarReservasDisponibles;
    }

    @GetMapping("")
    @Operation(summary = "Activa" , description = "Metodo utilizado para consultar las reservas disponibles")
    public List<VueloDTO> obtenerVuelosDisponibles() {return manejadorConsultarReservasDisponibles.ejecutar();}
}
