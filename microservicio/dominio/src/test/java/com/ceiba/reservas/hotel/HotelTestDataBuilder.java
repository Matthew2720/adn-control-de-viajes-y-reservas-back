package com.ceiba.reservas.hotel;

import com.ceiba.reservas.habitacion.HabitacionTestDataBuilder;
import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;
import com.ceiba.reservas.hotel.modelo.entidad.Hotel;
import com.ceiba.reservas.vuelo.modelo.entidad.Ciudad;

import java.util.ArrayList;
import java.util.List;

public class HotelTestDataBuilder {
    private Long id;
    private String nombre;
    private Ciudad ubicacion;
    private List<Habitacion> habitaciones;
    private double costoPorNoche;

    public HotelTestDataBuilder(){
        this.habitaciones = new ArrayList<>();
    }

    public HotelTestDataBuilder conHotelPorDefecto(){
        this.id = 1l;
        this.nombre = "Camelot";
        this.ubicacion = Ciudad.BOGOTA;
        var habitacion = new HabitacionTestDataBuilder().conHabitacionPorDefecto().reconstruir();
        this.conHabitacion(habitacion);
        this.costoPorNoche = 15000;
        return this;
    }

    public HotelTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public HotelTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public HotelTestDataBuilder conUbicacion(Ciudad ubicacion) {
        this.ubicacion = ubicacion;
        return this;
    }

    public HotelTestDataBuilder conHabitacion(Habitacion habitacion){
        this.habitaciones.add(habitacion);
        return this;
    }

    public HotelTestDataBuilder conCostoPorNoche(double costoPorNoche) {
        this.costoPorNoche = costoPorNoche;
        return this;
    }

    public Hotel reconstruir(){
        return Hotel.reconstruir(id,nombre,ubicacion,habitaciones,costoPorNoche);
    }
}
