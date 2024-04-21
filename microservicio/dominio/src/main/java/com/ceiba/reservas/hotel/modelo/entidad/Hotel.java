package com.ceiba.reservas.hotel.modelo.entidad;

import com.ceiba.reservas.dominio.ValidadorArgumento;
import com.ceiba.reservas.habitacion.modelo.entidad.Habitacion;
import com.ceiba.reservas.vuelo.modelo.entidad.Ciudad;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class Hotel {
    private Long id;
    private String nombre;
    private Ciudad ubicacion;
    private List<Habitacion> habitaciones;
    private double costoPorNoche;

    private Hotel(Long id, String nombre, Ciudad ubicacion, List<Habitacion> habitaciones, double costoPorNoche) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.habitaciones = new ArrayList<>(habitaciones);
        this.costoPorNoche = costoPorNoche;
    }

    public static Hotel reconstruir(Long id, String nombre, Ciudad ubicacion, List<Habitacion> habitaciones, double costoPorNoche){
        ValidadorArgumento.validarObligatorio(id, "el id es requerido");
        ValidadorArgumento.validarObligatorio(nombre, "el nombre es requerido");
        ValidadorArgumento.validarObligatorio(ubicacion, "La ubicacion es requerida");
        ValidadorArgumento.validarObligatorio(habitaciones, "Las habitaciones son requeridas");
        ValidadorArgumento.validarObligatorio(costoPorNoche, "El costo por noche es requerido");
        ValidadorArgumento.validarPositivo(costoPorNoche, "El valor debe ser positivo");
        return new Hotel(id,nombre, ubicacion, habitaciones, costoPorNoche);
    }
}
