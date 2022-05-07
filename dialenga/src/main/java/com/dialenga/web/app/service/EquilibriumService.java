package com.dialenga.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialenga.web.app.models.EquilibriumBean;
import com.dialenga.web.app.repository.EquilibriumRepository;

@Service
public class EquilibriumService implements IEquilibriumService {

	@Autowired
	private EquilibriumRepository repository;
	
	@Override
	public long save(EquilibriumBean eqb) {
		return repository.save(eqb).getId();
	}
	
	@Override
	public List<EquilibriumBean> getAll() {
		return repository.findAll();
	}
}
