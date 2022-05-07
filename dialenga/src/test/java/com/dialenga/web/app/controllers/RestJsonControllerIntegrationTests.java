package com.dialenga.web.app.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(controllers = RestJsonControllerIntegration.class )
class RestJsonControllerIntegrationTests {

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
		assertTrue(response.contains(RestJsonControllerIntegration.ERROR));
	}


	// puede fallar cuando tiene más de un indice de equilibrio -> por el procesamiento en paralelo
	// el orden de procesamiento de los indices cambia... mirar whenValidInput_thenReturnsEquilibriumBeanListToStringHasOneEquilibriumIndex
	@Test
	void whenValidInput_thenReturnsEquilibriumBeanListToString() throws Exception {
		final String lista = "-7,1,5,2,-4,3,0";
		ObjectMapper objectMapper = new ObjectMapper();
		RestJsonControllerIntegration rest =  new RestJsonControllerIntegration();
		String response = mockMvc.perform(get("/json/equilibrium/{param}", "-7,1,5,2,-4,3,0")
				.contentType("application/json"))
				.andReturn().getResponse().getContentAsString();
		String expected = rest.getEquilibriumParallel(lista);
		JsonNode jsonNodeOne = objectMapper.readTree(response);
		JsonNode jsonNodeTwo = objectMapper.readTree(expected);
		assertEquals(jsonNodeOne.get(0).get("array_enteros"), jsonNodeTwo.get(0).get("array_enteros"));

		Logger.getGlobal().info(expected);
		Logger.getGlobal().info(response);
		assertEquals(jsonNodeOne.get(0).get("indices_equilibrio"), jsonNodeTwo.get(0).get("indices_equilibrio"));
	}
	
	
	@Test
	void whenValidInput_thenReturnsEquilibriumBeanListToStringHasOneEquilibriumIndex() throws Exception {
		final String lista = "2, 9, 3, 4, 0, 3, 3, 2, 9, 1";
		ObjectMapper objectMapper = new ObjectMapper();
		RestJsonControllerIntegration rest =  new RestJsonControllerIntegration();
		String response = mockMvc.perform(get("/json/equilibrium/{param}", "2, 9, 3, 4, 0, 3, 3, 2, 9, 1")
				.contentType("application/json"))
				.andReturn().getResponse().getContentAsString();
		String expected = rest.getEquilibriumParallel(lista);
		JsonNode jsonNodeOne = objectMapper.readTree(response);
		JsonNode jsonNodeTwo = objectMapper.readTree(expected);
		assertEquals(jsonNodeOne.get(0).get("array_enteros"), jsonNodeTwo.get(0).get("array_enteros"));
		assertEquals(jsonNodeOne.get(0).get("indices_equilibrio"), jsonNodeTwo.get(0).get("indices_equilibrio"));
	}
}
