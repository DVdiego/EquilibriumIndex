package com.dialenga.web.app.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dialenga.web.app.models.EquilibriumBean;
import com.dialenga.web.app.repository.EquilibriumRepository;

@DataJpaTest
class EquilibriumServiceTest {

	@Autowired
	private EquilibriumRepository repository;
	
	@Test
	public void testSave() {
		Date now = new Date();
		EquilibriumBean one = getNewEquilibriumBean(now);
		EquilibriumBean saved = repository.save(one);
		
		assertEquals(one, saved);
	}
	
	@Test
	public void testService_Save() {
		Date now = new Date();
		EquilibriumService service = new EquilibriumService(repository);
		EquilibriumBean one = getNewEquilibriumBean(now);
		long id = service.save(one);
		assertTrue(one.getId() == id);
	}

	@Test
	public void testGetAll() {
		EquilibriumService service = new EquilibriumService(repository);
		// se cargan datos al arrancar la aplicación...
		List<EquilibriumBean> list = service.getAll();
		assertTrue(!list.isEmpty());
	}

	private EquilibriumBean getNewEquilibriumBean(Date now) {
		return new EquilibriumBean(now,"array","index");
	}
}
