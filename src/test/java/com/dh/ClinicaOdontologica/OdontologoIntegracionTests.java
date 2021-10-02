package com.dh.ClinicaOdontologica;

import com.dh.ClinicaOdontologica.model.Odontologo;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoIntegracionTests {

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
	public void registrarOdontologo() throws Exception {
		Odontologo od = new Odontologo();
		od.setNombre("Agustina");
		od.setMatricula(134);
		od.setApellido("Pasqualis");
		MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologos/registrar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(od)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
	}

	@Test
	@Order(2)
	@WithMockUser(roles = "ADMIN")
	public void listarOdontologos() throws Exception {
		MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());

	}

	@Test
	@Order(3)
	@WithMockUser(roles = "ADMIN")
	public void borrarOdontologo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/odontologos/1")).andExpect(MockMvcResultMatchers
				.status().is2xxSuccessful());
	}



}

