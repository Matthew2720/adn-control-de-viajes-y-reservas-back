package com.ceiba.reservas.vuelo.modelo.dto;

import com.ceiba.reservas.vuelo.modelo.entidad.Ciudad;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class VueloDTO {
    private Long id;
    private Ciudad origen;
    private Ciudad destino;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private int asientosDisponibles;
    private BigDecimal precioBase;
}
