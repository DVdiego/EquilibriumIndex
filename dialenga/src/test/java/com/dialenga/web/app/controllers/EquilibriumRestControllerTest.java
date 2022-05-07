package com.dialenga.web.app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.dialenga.web.app.models.EquilibriumBean;
import com.dialenga.web.app.service.IEquilibriumService;

@WebMvcTest(controllers = EquilibriumRestController.class )
class EquilibriumRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	IEquilibriumService service;
	
	@Test
	void whenValidInput_thenReturns200() throws Exception {
		mockMvc.perform(get("/api/equilibriumindex/find/{param}", "-7,1,5,2,-4,3,0"))
		.andExpect(status().isOk());
	}
	
	@Test
	void whenInvalidInput_thenReturnsError() throws Exception {
		mockMvc.perform(get("/api/equilibriumindex/find/{param}", "-7,1,5,2,-4|3,0"))
				.andExpect(status().isBadRequest());

	}
	
	@Test
	void testGetEquilibriumParallel() throws Exception {
		Mockito.when(service.save(new EquilibriumBean())).thenReturn(1L);
		mockMvc.perform(get("/api/equilibriumindex/find/{param}", "-7,1,5,2,-4,3,0"))
					.andExpect(status().isOk());
	}

	@Test
	void whenListIsEmptyNoContent_testGetAll() throws Exception {
		mockMvc.perform(get("/api/get/all")).andExpect(status().isNoContent());
	}

	@Test
	void whenTableIsNotEmpty_testGetAllEquilibriumIndex() throws Exception {
		List<EquilibriumBean> list = new ArrayList<>();
		list.add(new EquilibriumBean());
		Mockito.when(service.getAllequilibriumIndex()).thenReturn(list);
		mockMvc.perform(get("/api/get/equilibriumindex")).andExpect(status().isOk());
	}
	
	@Test
	void whenGetAllEquilibriumIndex_throwException() throws Exception {
		Mockito.when(service.getAllequilibriumIndex()).thenThrow();
		mockMvc.perform(get("/api/get/equilibriumindex")).andExpect(status().isInternalServerError());
	}
}
