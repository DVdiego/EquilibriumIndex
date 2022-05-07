package com.dialenga.web.app.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.dialenga.web.app.models.IndexDataBean;

@SpringBootTest
class FindIndexEquilibriumTests {

	FindIndexEquilibrium ce = new FindIndexEquilibrium();

	@Test
	public void getEquilibriumIndex() {
		String array = "2,9,3,4,0,3, 3, 2, 9, 1"; // Equilibrium Index es 4
		List<Integer> integers = getListIntegers(array);
		List<IndexDataBean> idb = ce.getEquilibriumIndex(integers);
		assertTrue(idb.get(0).getEquilibriumIndex() == 4);
	}


	@Test
	public void whenThereIsMoreThanOne_getEquilibriumIndex() {
		final String array = "-7,1,5,2,-4,3,0"; // Equilibrium Index es 3 y 6
		List<Integer> integers = getListIntegers(array);
		List<IndexDataBean> idb = ce.getEquilibriumIndex(integers);

		assertTrue(2 == idb.size());
	}


	@Test
	public void mapToIndexData() {
		String array = "0, -4, 7, -4, -2, 6, -3, 0";
		int p = 3;
		List<Integer> left = getListIntegers("0, -4, 7,");
		List<Integer> right = getListIntegers(" -2, 6, -3, 0");
		IndexDataBean idbUno = new IndexDataBean(
				p,
				left.parallelStream().reduce(0, Integer::sum),
				right.parallelStream().reduce(0, Integer::sum),
				left,
				right);
		
		IndexDataBean idbDos = ce.mapToIndexData(getListIntegers(array), new AtomicInteger(p));
		assertEquals(idbUno.getEquilibriumIndex(), idbDos.getEquilibriumIndex());
	}


	@Test
	public void getSubList_WhenIndexNegative() {
		String array = "0, -4, 7, -4, -2, 6, -3, 0";
		List<Integer> uno = ce.getSubList(getListIntegers(array), -1, 2);
		assertTrue(uno.isEmpty());
	}


	@Test
	public void getSubList_WhenIndexHigherThanArraySize() {
		String array = "0, -4, 7, -4, -2, 6, -3, 0";
		List<Integer> uno = ce.getSubList(
				getListIntegers(array),
				getListIntegers(array).size(),
				(getListIntegers(array).size()+10));
		assertTrue(uno.isEmpty());
	}


	private List<Integer> getListIntegers(String array) {
		return Arrays.asList(
				array.split(",")).stream()
    			.map(String::trim)
    			.map(Integer::valueOf)
    			.collect(Collectors.toList());
	}
}
