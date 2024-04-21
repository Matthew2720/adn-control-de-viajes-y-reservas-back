package com.ceiba.reservas.reservahotel;

import com.ceiba.reservas.cliente.ClienteTestDataBuilder;
import com.ceiba.reservas.habitacion.HabitacionTestDataBuilder;
import com.ceiba.reservas.habitacion.puerto.RepositorioHabitacion;
import com.ceiba.reservas.hotel.HotelTestDataBuilder;
import com.ceiba.reservas.reservahotel.puerto.repositorio.RepositorioReservaHotel;
import com.ceiba.reservas.reservahotel.servicio.ServicioAnularHotelReserva;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

class ServicioAnularHotelReservaTest {

    @Test
    void deberiaAnularReservaHotel() {
        // Preparar datos de prueba
        var repositorioReservaHotel = Mockito.mock(RepositorioReservaHotel.class);
        var repositorioHabitacion = Mockito.mock(RepositorioHabitacion.class);
        var servicioAnularHotelReserva = new ServicioAnularHotelReserva(repositorioReservaHotel, repositorioHabitacion);

        var cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        var hotel = new HotelTestDataBuilder().conHotelPorDefecto().reconstruir();
        var habitacion = new HabitacionTestDataBuilder().conHabitacionPorDefecto().reconstruir();
        var reservaHotel = new ReservaHotelTestDataBuilder()
                .conId(1L)
                .conFechaEntrada(LocalDateTime.now().plusDays(2))
                .conFechaSalida(LocalDateTime.now().plusDays(8))
                .conHotel(hotel)
                .conCliente(cliente)
                .conHabitacion(habitacion)
                .conEstado("RESERVADO")
                .conCostoTotal(90000)
                .reconstruir();

        // Ejecutar método a probar
        servicioAnularHotelReserva.ejecutar(reservaHotel);

        // Verificar comportamiento esperado
        Mockito.verify(repositorioHabitacion).actualizar(habitacion);
        Mockito.verify(repositorioReservaHotel).actualizar(reservaHotel);
    }
}