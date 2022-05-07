package com.dialenga.web.app.core;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.dialenga.web.app.models.IndexDataBean;

public class FindIndexEquilibrium implements IFindIndexEquilibrium {


	public FindIndexEquilibrium() {
		super();
	}


	@Override
	public List<IndexDataBean> getEquilibriumIndex(List<Integer> array) {

		AtomicInteger position = new AtomicInteger();
		return array.parallelStream()
				// si recorro el array y genero el indice concurrentemente con AtomicInteger,
				// me ahorro generar una lista de indices para posteriormente recorrerlos paralelamente
				.map(n->mapToIndexData(array,position))
				.filter(i -> i.isEquilibrium())
				.collect(Collectors.toList());
	}


	public IndexDataBean mapToIndexData(List<Integer> array, AtomicInteger position) {

		int p = position.getAndIncrement();
		List<Integer> left = getSubList(array, 0, p);
		List<Integer> right = getSubList(array, p+1, array.size());

		IndexDataBean idb = new IndexDataBean(
				p,
				left.parallelStream().reduce(0, Integer::sum),
				right.parallelStream().reduce(0, Integer::sum),
				left,
				right);

		return idb;
	}


	/**
	 *  return sublista -> si el indice dado es 0 (primero) o array.size (último),
	 *  retorna lista vacia puesto que no tiene elementos a la izq o der.
	 */
	public List<Integer> getSubList(List<Integer> array, int from, int to) {
		try {
			return array.stream().skip(from).limit(to).collect(Collectors.toList());
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

}
