package com.ceiba.reservas.reservahotel.controlador;

import com.ceiba.reservas.ApplicationMock;
import com.ceiba.reservas.reservahotel.puerto.repositorio.RepositorioReservaHotel;
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

import java.time.temporal.ChronoUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorReservaHotel.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorReservaHotelTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private RepositorioReservaHotel repositorioReservaHotel;

    @Test
    void crearReservaHotelExitosa() throws Exception {

        var comandoReservarHotelTestDataBuilder = new ComandoReservarHotelTestDataBuilder().crearPorDefecto().build();

        var resultado = mocMvc.perform(post("/reservahotel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReservarHotelTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaReservarHotel.class);

        var reservaGuardada = repositorioReservaHotel.obtener(respuesta.getValor());

        Assertions.assertEquals("Cliente 1", reservaGuardada.getCliente().getNombre());
        Assertions.assertEquals("Hotel La Maria", reservaGuardada.getHotel().getNombre());
        Assertions.assertEquals(101, reservaGuardada.getHabitacion().getNumeroHabitacion());
        Assertions.assertEquals(comandoReservarHotelTestDataBuilder.getFechaEntrada().truncatedTo(ChronoUnit.SECONDS), reservaGuardada.getFechaEntrada());
        Assertions.assertEquals(comandoReservarHotelTestDataBuilder.getFechaSalida().truncatedTo(ChronoUnit.SECONDS), reservaGuardada.getFechaSalida());
    }

    @Test
    void anularReservaExistosa() throws Exception {
        mocMvc.perform(post("/reservahotel/cancelar/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        var reservaAnulada = repositorioReservaHotel.obtener(1L);

        Assertions.assertTrue(reservaAnulada.esCancelado());
    }
}
