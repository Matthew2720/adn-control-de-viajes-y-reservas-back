package com.ceiba.reservas.reservahotel.controlador;

import com.ceiba.reservas.ComandoRespuesta;
import com.ceiba.reservas.reservahotel.ComandoAnularReservaHotel;
import com.ceiba.reservas.reservahotel.ComandoSolicitudReservaHotel;
import com.ceiba.reservas.reservahotel.manejador.ManejadorAnularReservaHotel;
import com.ceiba.reservas.reservahotel.manejador.ManejadorReservaHotel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservahotel")
@Tag(name = "Controlador comando reserva hotel")
@CrossOrigin(origins = "http://localhost:4200")
public class ComandoControladorReservaHotel {

    private final ManejadorAnularReservaHotel manejadorAnularReservaHotel;
    private final ManejadorReservaHotel manejadorReservaHotel;


    public ComandoControladorReservaHotel(ManejadorAnularReservaHotel manejadorAnularReservaHotel, ManejadorReservaHotel manejadorReservaHotel) {
        this.manejadorAnularReservaHotel = manejadorAnularReservaHotel;
        this.manejadorReservaHotel = manejadorReservaHotel;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Reservar hotel" , description = "Metodo utilizado para crear una nueva reserva de hotel ")
    public ComandoRespuesta<Long> reservar(@RequestBody ComandoSolicitudReservaHotel comandoSolicitudReservaHotel){
        return this.manejadorReservaHotel.ejecutar(comandoSolicitudReservaHotel);
    }

    @DeleteMapping("cancelar/{id-reserva}")
    @Operation(summary = "Cancelar reserva hotel", description = "Metodo utilizado para cancelar una reserva de hotel")
    public void cancelar(@PathVariable("id-reserva") Long idReserva){
        this.manejadorAnularReservaHotel.ejecutar(new ComandoAnularReservaHotel(idReserva));
    }

}
