package com.dialenga.web.app.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = RestJsonController.class )
class RestJsonControllerTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	void whenValidInput_thenReturns200() throws Exception {
		mockMvc.perform(get("/json/equilibrium/{param}", "-7,1,5,2,-4,3,0")
				.contentType("application/json"))
					.andExpect(status().isOk());
	}


	@Test
	void whenInvalidInput_thenReturnsError() throws Exception {
		String response = mockMvc.perform(get("/json/equilibrium/{param}", "-7,1,5,2,-4|3,0")
				.contentType("application/json"))
					.andReturn().getResponse().getContentAsString();
		assertTrue(response.contains(RestJsonController.ERROR));
	}


	@Test
	void whenValidInput_thenReturnsEquilibriumBeanListToString() throws Exception {
		final String lista = "-7,1,5,2,-4,3,0";
		RestJsonController rest =  new RestJsonController();
		String response = mockMvc.perform(get("/json/equilibrium/{param}", "-7,1,5,2,-4,3,0")
				.contentType("application/json"))
				.andReturn().getResponse().getContentAsString();
		String expected = rest.getEquilibriumParallel(lista);
		assertTrue(expected.contains(response));
	}
}
