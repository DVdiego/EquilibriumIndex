package com.dialenga.web.app.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IndexDataBean {
	
	private int index;
	private int sumLeft;
	private int sumRight;
	private List<Integer> left = Collections.synchronizedList(new  ArrayList<>());
	private List<Integer> right = Collections.synchronizedList(new  ArrayList<>());
	
	public IndexDataBean(int index, int sumLeft, int sumRight, List<Integer> left, List<Integer> right) {
		super();
		this.index = index;
		this.sumLeft = sumLeft;
		this.sumRight = sumRight;
		this.left.addAll(left);
		this.right.addAll(right);
	}

	public int getIndex() {
		return index;
	}

	public int getSumLeft() {
		return sumLeft;
	}

	public int getSumRight() {
		return sumRight;
	}

	public List<Integer> getLeft() {
		return List.copyOf(this.left);
	}

	public List<Integer> getRight() {
		return List.copyOf(this.right);
	}

	public boolean isEquilibrium() {
		return sumLeft == sumRight;
	}

	public int getEquilibriumIndex() {
		if (isEquilibrium()) {
			return this.index;
		}
		return -1;
	}

	@Override
	public String toString() {
		return "IndexData [ Index = " + getIndex() + ", SumLeft = " + getSumLeft() + ", SumRight = "
				+ getSumRight() + ", Left = " + getLeft() + ",Right = " + getRight() + ", isEquilibrium = "
				+ isEquilibrium() + ", EquilibriumIndex = " + getEquilibriumIndex() + "]";
	}
	
}
