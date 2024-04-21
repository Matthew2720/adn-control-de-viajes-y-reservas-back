package com.ceiba.reservas.hotel.controlador;

import com.ceiba.reservas.hotel.consulta.ManejadorConsultarHotelesDisponibles;
import com.ceiba.reservas.hotel.modelo.entidad.Hotel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hoteles")
@Tag(name = "Controlador consulta hoteles")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaControladorHotel {

    private final ManejadorConsultarHotelesDisponibles manejadorConsultarHotelesDisponibles;

    public ConsultaControladorHotel(ManejadorConsultarHotelesDisponibles manejadorConsultarHotelesDisponibles) {
        this.manejadorConsultarHotelesDisponibles = manejadorConsultarHotelesDisponibles;
    }

    @GetMapping("")
    @Operation(summary = "Activas" , description = "Metodo utilizado para consultar los hoteles disponibles")
    public List<Hotel>  obtenerHotelesDisponibles(){
        return manejadorConsultarHotelesDisponibles.ejecutar();
    }

}
