package com.ceiba.reservas.reservahotel.controlador;

public class RespuestaReservarHotel {
    private Long valor;

    public RespuestaReservarHotel() {
    }

    public RespuestaReservarHotel(Long valor) {
        this.valor = valor;
    }

    public Long getValor() {
        return valor;
    }
}
