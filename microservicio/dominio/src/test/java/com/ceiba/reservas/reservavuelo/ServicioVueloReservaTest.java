package com.ceiba.reservas.reservavuelo;

import com.ceiba.reservas.cliente.ClienteTestDataBuilder;
import com.ceiba.reservas.cliente.entidad.Cliente;
import com.ceiba.reservas.core.BasePrueba;
import com.ceiba.reservas.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.reservas.reservavuelo.modelo.entidad.ReservaVuelo;
import com.ceiba.reservas.reservavuelo.modelo.entidad.SolicitudReservar;
import com.ceiba.reservas.reservavuelo.puerto.repositorio.RepositorioReservaVuelo;
import com.ceiba.reservas.reservavuelo.servicio.ServicioVueloReserva;
import com.ceiba.reservas.vuelo.VueloTestDataBuilder;
import com.ceiba.reservas.vuelo.modelo.entidad.Vuelo;
import com.ceiba.reservas.vuelo.puerto.repositorio.RepositorioVuelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ServicioVueloReservaTest {

    @Mock
    private RepositorioReservaVuelo repositorioReservaVuelo;

    @Mock
    private RepositorioVuelo repositorioVuelo;
    @InjectMocks
    private ServicioVueloReserva servicioVueloReserva;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deberiaReservarCorrectamente(){
        Cliente cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        Vuelo vuelo = new VueloTestDataBuilder().conVueloEnDiezDiasDiezAsientosDisponibles().reconstruir();
        int pasajeros = 5;
        SolicitudReservar solicitudReservar = new SolicitudReservarTestDataBuilder()
                .conCliente(cliente)
                .conVuelo(vuelo)
                .conPasajeros(pasajeros).reconstruir();

        servicioVueloReserva.ejecutar(solicitudReservar);

        verify(repositorioVuelo, times(1)).actualizar(vuelo);

        verify(repositorioReservaVuelo, times(1)).guardar(any(ReservaVuelo.class));
    }

    @Test
    void noDeberiaReservarSinPasajeros(){
        Cliente cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        Vuelo vuelo = new VueloTestDataBuilder().conVueloEnDiezDiasDiezAsientosDisponibles().reconstruir();
        int pasajeros = 0;
        SolicitudReservar solicitudReservar = new SolicitudReservarTestDataBuilder()
                .conCliente(cliente)
                .conVuelo(vuelo)
                .conPasajeros(pasajeros).reconstruir();

        BasePrueba.assertThrows(() -> servicioVueloReserva.ejecutar(solicitudReservar),
                ExcepcionValorInvalido.class, "Pasajeros requeridos para la reserva"
                );
    }
}
