package com.ceiba.reservas.reservavuelo;

import com.ceiba.reservas.core.BasePrueba;
import com.ceiba.reservas.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reservas.reservavuelo.modelo.entidad.EstadoReserva;
import com.ceiba.reservas.reservavuelo.modelo.entidad.ReservaVuelo;
import com.ceiba.reservas.reservavuelo.puerto.repositorio.RepositorioReservaVuelo;
import com.ceiba.reservas.reservavuelo.servicio.ServicioAnularReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ServicioAnularReservaTest {

    @Mock
    private RepositorioReservaVuelo repositorioReservaVuelo;

    @InjectMocks
    private ServicioAnularReserva servicioAnularReserva;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void anularReservaNullDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> servicioAnularReserva.ejecutar(null),
                ExcepcionValorObligatorio.class, "No existe una reserva para anular");
    }
}
