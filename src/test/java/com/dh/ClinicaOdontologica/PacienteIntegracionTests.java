package com.dh.ClinicaOdontologica;

import com.dh.ClinicaOdontologica.model.Domicilio;
import com.dh.ClinicaOdontologica.model.Paciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteIntegracionTests {

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(1)
    @WithMockUser(roles = "ADMIN")
    public void registrarPaciente() throws Exception {
        Paciente paciente = new Paciente();
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Buenos Aires");
        domicilio.setNumero("1255");
        domicilio.setLocalidad("Cördoba");
        domicilio.setProvincia("Cördoba");
        paciente.setNombre("Marcelo");
        paciente.setApellido("Lopez");
        paciente.setDni("321552265");
        paciente.setDomicilio(domicilio);

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/pacientes/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(paciente)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(2)
    @WithMockUser(roles = "ADMIN")
    public void listarPacientes() throws Exception {
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());

    }

    @Test
    @Order(3)
    @WithMockUser(roles = "ADMIN")
    public void buscarPacientes() throws Exception {
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/buscar/Lopez")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());

    }

    @Test
    @Order(4)
    @WithMockUser(roles = "ADMIN")
    public void borrarPaciente() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/1")).andExpect(MockMvcResultMatchers
                .status().is2xxSuccessful());
    }


}

