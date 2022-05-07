package com.dialenga.web.app.service;

import java.util.List;

import com.dialenga.web.app.models.EquilibriumBean;


public interface IEquilibriumService {

	long save(EquilibriumBean eqb);

	List<EquilibriumBean> getAll();

	List<EquilibriumBean> getAllequilibriumIndex();

}
