package com.ceiba.reservas.hotel.controlador;

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
import static org.hamcrest.Matchers.hasSize;


import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorHotel.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorHotelTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    void consultarVuelosDisponibles() throws Exception {
        mocMvc.perform(get("/hoteles")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nombre", is("Hotel La Maria")))
                .andExpect(jsonPath("$[0].ubicacion", is("MEDELLIN")))
                .andExpect(jsonPath("$[0].habitaciones", hasSize(2)))
                .andExpect(jsonPath("$[0].habitaciones[0].id", is(1)))
                .andExpect(jsonPath("$[0].habitaciones[0].numeroHabitacion", is(101)))
                .andExpect(jsonPath("$[0].habitaciones[0].disponibilidad", is(true)))
                .andExpect(jsonPath("$[0].costoPorNoche", is(100.0)));
    }
}
