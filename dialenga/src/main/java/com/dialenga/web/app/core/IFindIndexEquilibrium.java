package com.dialenga.web.app.core;

import java.util.List;

import com.dialenga.web.app.models.IndexDataBean;

public interface IFindIndexEquilibrium {

	List<IndexDataBean> getEquilibriumIndex(List<Integer> array);

}