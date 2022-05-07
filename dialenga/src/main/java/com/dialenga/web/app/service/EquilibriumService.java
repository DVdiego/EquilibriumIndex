package com.dialenga.web.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialenga.web.app.models.EquilibriumBean;
import com.dialenga.web.app.repository.EquilibriumRepository;

@Service
public class EquilibriumService implements IEquilibriumService {

	private static final String NO_EQ_INDEX = "No tiene";
	
	@Autowired
	private EquilibriumRepository crudRepository;
	
	public EquilibriumService(EquilibriumRepository repository) {
		this.crudRepository = repository;
	}

	@Override
	public long save(EquilibriumBean eqb) {
		return crudRepository.save(eqb).getId();
	}
	
	@Override
	public List<EquilibriumBean> getAll() {
		return crudRepository.findAll();
	}
	
	
	@Override
	public List<EquilibriumBean> getAllequilibriumIndex() {
		// lo más rápido para la prueba es buscar todas y filtrar despues...
		return crudRepository.findAll().parallelStream()
				.filter(e -> !e.getEquilibriumIndices().contains(NO_EQ_INDEX)
						|| !e.getEquilibriumIndices().isBlank())
				.collect(Collectors.toList());
	}
	
	
}
