package com.ceiba.reservas.vuelo.controlador;

import com.ceiba.reservas.ApplicationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorVuelo.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorVueloTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    void consultarVuelosDisponibles() throws Exception {
        mocMvc.perform(get("/vuelos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].origen", is("MEDELLIN")))
                .andExpect(jsonPath("$[0].destino", is("BOGOTA")))
                .andExpect(jsonPath("$[0].fechaSalida", is("2023-08-15 09:00:00")))
                .andExpect(jsonPath("$[0].fechaLlegada", is("2023-08-20 12:00:00")))
                .andExpect(jsonPath("$[0].asientosDisponibles", is(100)))
                .andExpect(jsonPath("$[0].precioBase", is(150.00)));
    }
}
