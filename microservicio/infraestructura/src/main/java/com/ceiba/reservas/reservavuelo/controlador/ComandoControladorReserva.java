package com.ceiba.reservas.reservavuelo.controlador;

import com.ceiba.reservas.ComandoRespuesta;
import com.ceiba.reservas.reservavuelo.comando.ComandoAnular;
import com.ceiba.reservas.reservavuelo.comando.ComandoSolicitudReservar;
import com.ceiba.reservas.reservavuelo.comando.manejador.ManejadorAnularReserva;
import com.ceiba.reservas.reservavuelo.comando.manejador.ManejadorReservar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")
@Tag(name = "Controlador comando reserva")
@CrossOrigin(origins = "http://localhost:4200")
public class ComandoControladorReserva {

    private final ManejadorAnularReserva manejadorAnularReserva;
    private final ManejadorReservar manejadorReservar;


    public ComandoControladorReserva(ManejadorAnularReserva manejadorAnularReserva, ManejadorReservar manejadorReservar) {
        this.manejadorAnularReserva = manejadorAnularReserva;
        this.manejadorReservar = manejadorReservar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Reservar" , description = "Metodo utilizado para crear una nueva reserva ")
    public ComandoRespuesta<Long> reservar(@RequestBody ComandoSolicitudReservar comandoSolicitudReservar){
        return this.manejadorReservar.ejecutar(comandoSolicitudReservar);
    }

    @DeleteMapping("cancelar/{id-reserva}")
    @Operation(summary = "Cancelar", description = "Metodo utilizado para cancelar una reserva")
    public void cancelar(@PathVariable("id-reserva") Long idReserva){
        this.manejadorAnularReserva.ejecutar(new ComandoAnular(idReserva));
    }
}
