package com.ceiba.reservas.reservahotel.modelo.entidad;

import com.ceiba.reservas.cliente.entidad.Cliente;
import com.ceiba.reservas.dominio.ValidadorArgumento;
import com.ceiba.reservas.dominio.excepcion.ExcepcionCancelacionNoDisponible;
import com.ceiba.reservas.dominio.excepcion.ExcepcionHabitacionNoDisponible;
import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;
import com.ceiba.reservas.hotel.modelo.entidad.Hotel;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
public class ReservaHotel {

    public static final double DESCUENTO_ANTICIPACION_SIETE_DIAS = 0.1;

    private Long id;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Hotel hotel;
    private Habitacion habitacion;
    private Cliente cliente;
    private double costoTotal;
    private String estado;

    private ReservaHotel(Cliente cliente, Hotel hotel, Habitacion habitacion, LocalDateTime fechaEntrada, LocalDateTime fechaSalida){
        this.cliente = cliente;
        this.hotel = hotel;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.estado = "RESERVADO";
        this.costoTotal = calcularCostoTotal();
    }

    ReservaHotel(Long id, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, Hotel hotel, Habitacion habitacion, Cliente cliente, double costoTotal, String estado) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.hotel = hotel;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.costoTotal = costoTotal;
        this.estado = estado;
    }

    public static ReservaHotel reconstruir(Long id, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, Hotel hotel, Habitacion habitacion, Cliente cliente, double costoTotal, String estado){
        ValidadorArgumento.validarObligatorio(id, "El id es requerido para la reserva");
        ValidadorArgumento.validarObligatorio(fechaEntrada, "La fecha de entrada es requerida para la reserva");
        ValidadorArgumento.validarObligatorio(fechaSalida, "La fecha de salida es requerida para la reserva");
        ValidadorArgumento.validarObligatorio(hotel, "El hotel es requerido para la reserva");
        ValidadorArgumento.validarObligatorio(habitacion, "La habitacion es requerida para la reserva");
        ValidadorArgumento.validarObligatorio(cliente, "El cliente es requerido para la reserva");
        ValidadorArgumento.validarObligatorio(costoTotal, "El costo total es requerido");
        ValidadorArgumento.validarObligatorio(estado, "El estado es requerido para la reserva");
        return new ReservaHotelBuilder()
                .conId(id)
                .conFechaEntrada(fechaEntrada)
                .conFechaSalida(fechaSalida)
                .conHotel(hotel)
                .conHabitacion(habitacion)
                .conCliente(cliente)
                .conCostoTotal(costoTotal)
                .conEstado(estado).build();
    }

    public static ReservaHotel crear(SolicitudReservaHotel solicitudReservaHotel){
        ValidadorArgumento.validarObligatorio(solicitudReservaHotel.getCliente(), "El cliente es requerido para la reserva");
        ValidadorArgumento.validarObligatorio(solicitudReservaHotel.getHotel(), "El hotel es requerido para la reserva");
        ValidadorArgumento.validarObligatorio(solicitudReservaHotel.getHabitacion(), "La habitacion es requerida para la reserva");
        ValidadorArgumento.validarObligatorio(solicitudReservaHotel.getFechaEntrada(), "La fecha de entrada es requerida para la reserva");
        ValidadorArgumento.validarFechaNoMenorActual(solicitudReservaHotel.getFechaEntrada(), "La fecha no debe ser anterior a la fecha actual");
        ValidadorArgumento.validarObligatorio(solicitudReservaHotel.getFechaSalida(), "La fecha de salida es requerida para la reserva");
        validarDisponibilidadHabitacion(solicitudReservaHotel.getHabitacion());

        return new ReservaHotel(solicitudReservaHotel.getCliente(), solicitudReservaHotel.getHotel(),solicitudReservaHotel.getHabitacion()
                ,solicitudReservaHotel.getFechaEntrada(),solicitudReservaHotel.getFechaSalida());
    }

    private boolean sePuedeCancelar() {
        LocalDateTime fechaLimiteCancelacion = getFechaEntrada().minusDays(1);
        return LocalDateTime.now().isBefore(fechaLimiteCancelacion);
    }

    public void cancelarReserva(){
        if (sePuedeCancelar()){
            this.estado = "CANCELADO";
        } else {
            throw new ExcepcionCancelacionNoDisponible("Ha excedido el tiempo permitido para cancelar la reserva, recuerde que es un maximo de 24 horas");
        }
    }

    private long calcularDiasReserva(){
        return ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
    }

    private double calcularCostoTotal(){
        long diasReserva = calcularDiasReserva();
        costoTotal = diasReserva * hotel.getCostoPorNoche();

        aplicarDescuentoPorDuracion();
        return costoTotal;
    }

    private void aplicarDescuentoPorDuracion(){
        long diasReserva = calcularDiasReserva();

        if (diasReserva > 7){
            double descuento = costoTotal * DESCUENTO_ANTICIPACION_SIETE_DIAS;
            costoTotal -= descuento;
        }
    }

    public Habitacion liberarHabitacionReserva() {
        this.habitacion.liberarHabitacion();
        return habitacion;
    }

    public static void validarDisponibilidadHabitacion(Habitacion habitacion) {
        if (!habitacion.estaDisponible()) {
            throw new ExcepcionHabitacionNoDisponible("La habitacion no esta disponible");
        }
    }

    public boolean esCancelado(){
        return "CANCELADO".equals(this.estado);
    }

}


