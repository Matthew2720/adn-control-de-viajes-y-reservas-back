package com.ceiba.reservas.reservavuelo.controlador;

import com.ceiba.reservas.ApplicationMock;
import com.ceiba.reservas.reservavuelo.modelo.entidad.EstadoReserva;
import com.ceiba.reservas.reservavuelo.puerto.repositorio.RepositorioReservaVuelo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorReserva.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorReservaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private RepositorioReservaVuelo repositorioReservaVuelo;

    @Test
    void crearReservaExitosa() throws Exception {
        var comandoReservarTestDataBuilder = new ComandoReservarTestDataBuilder().crearPorDefecto().build();

        var resultado = mocMvc.perform(post("/reserva")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReservarTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaReservar.class);

        var reservaGuardada = repositorioReservaVuelo.obtener(respuesta.getValor());

        Assertions.assertEquals("Cliente 1", reservaGuardada.getCliente().getNombre());
        Assertions.assertEquals(new BigDecimal("150.00"), reservaGuardada.getVuelo().getPrecioBase());
        Assertions.assertEquals(EstadoReserva.ACTIVA.name(), reservaGuardada.getEstadoReserva().toString());
    }

}
