package com.ceiba.reservas.configuracion;


import com.ceiba.reservas.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.reservas.factura.servicio.ServicioAnular;
import com.ceiba.reservas.factura.servicio.ServicioFacturar;
import com.ceiba.reservas.habitacion.puerto.RepositorioHabitacion;
import com.ceiba.reservas.reservahotel.puerto.repositorio.RepositorioReservaHotel;
import com.ceiba.reservas.reservahotel.servicio.ServicioAnularHotelReserva;
import com.ceiba.reservas.reservahotel.servicio.ServicioHotelReserva;
import com.ceiba.reservas.reservavuelo.puerto.repositorio.RepositorioReservaVuelo;
import com.ceiba.reservas.reservavuelo.servicio.ServicioAnularReserva;
import com.ceiba.reservas.reservavuelo.servicio.ServicioVueloReserva;
import com.ceiba.reservas.vuelo.puerto.repositorio.RepositorioVuelo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioFacturar servicioFacturar(RepositorioFactura repositorioFactura) {
        return new ServicioFacturar(repositorioFactura);
    }

    @Bean
    public ServicioAnular servicioAnular(RepositorioFactura repositorioFactura) {
        return new ServicioAnular(repositorioFactura);
    }

    @Bean
    public ServicioAnularReserva servicioAnularReserva(RepositorioReservaVuelo repositorioReservaVuelo, RepositorioVuelo repositorioVuelo){
        return new ServicioAnularReserva(repositorioReservaVuelo, repositorioVuelo);
    }

    @Bean
    public ServicioVueloReserva servicioVueloReserva(RepositorioVuelo repositorioVuelo, RepositorioReservaVuelo repositorioReservaVuelo){
        return new ServicioVueloReserva(repositorioVuelo, repositorioReservaVuelo);
    }

    @Bean
    public ServicioAnularHotelReserva servicioAnularHotelReserva(RepositorioReservaHotel repositorioReservaHotel, RepositorioHabitacion repositorioHabitacion){
        return new ServicioAnularHotelReserva(repositorioReservaHotel, repositorioHabitacion);
    }

    @Bean
    public ServicioHotelReserva servicioHotelReserva(RepositorioReservaHotel repositorioReservaHotel, RepositorioHabitacion repositorioHabitacion){
        return new ServicioHotelReserva(repositorioReservaHotel, repositorioHabitacion);
    }

	

} 
