package com.dialenga.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialenga.web.app.models.EquilibriumBean;
import com.dialenga.web.app.repository.EquilibriumRepository;

@Service
public class EquilibriumService implements IEquilibriumService {

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
}
