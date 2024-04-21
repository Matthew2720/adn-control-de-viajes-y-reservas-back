package com.ceiba.reservas.reservahotel;

import com.ceiba.reservas.cliente.ClienteTestDataBuilder;
import com.ceiba.reservas.habitacion.HabitacionTestDataBuilder;
import com.ceiba.reservas.habitacion.puerto.RepositorioHabitacion;
import com.ceiba.reservas.hotel.HotelTestDataBuilder;
import com.ceiba.reservas.reservahotel.modelo.entidad.ReservaHotel;
import com.ceiba.reservas.reservahotel.modelo.entidad.SolicitudReservaHotel;
import com.ceiba.reservas.reservahotel.puerto.repositorio.RepositorioReservaHotel;
import com.ceiba.reservas.reservahotel.servicio.ServicioHotelReserva;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class ServicioHotelReservaTest {

    @Test
    void deberiaCrearReservaHotel() {
        var repositorioReservaHotel = Mockito.mock(RepositorioReservaHotel.class);
        var repositorioHabitacion = Mockito.mock(RepositorioHabitacion.class);
        var servicioHotelReserva = new ServicioHotelReserva(repositorioReservaHotel, repositorioHabitacion);

        var cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        var hotel = new HotelTestDataBuilder().conHotelPorDefecto().reconstruir();
        var habitacion = new HabitacionTestDataBuilder().conHabitacionPorDefecto().reconstruir();
        var solicitudReservaHotel = new SolicitudReservaHotel(cliente, hotel, habitacion, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(6));

        servicioHotelReserva.ejecutar(solicitudReservaHotel);

        Mockito.verify(repositorioHabitacion).actualizar(habitacion);
        Mockito.verify(repositorioReservaHotel).guardar(Mockito.any(ReservaHotel.class));
    }
}
