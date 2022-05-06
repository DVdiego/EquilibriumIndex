package com.dialenga.web.app.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EquilibriumBean {

	@JsonIgnore
	private long id;
	
	@JsonProperty("fecha_solicitud")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date date;
	

	@JsonProperty("array_enteros")
	private List<Integer> arrayIntegers = new ArrayList<>();
	
	@JsonProperty("indices_equilibrio")
	private List<IndexDataBean> equilibriumIndices = new ArrayList<>();
	
	
	public EquilibriumBean(Date date, List<Integer> arrayIntegers, List<IndexDataBean> equilibriumIndices) {
		super();
		this.date = date;
		this.arrayIntegers.addAll(arrayIntegers);
		this.equilibriumIndices.addAll(equilibriumIndices);
	}


	public long getId() {
		return id;
	}


	public Date getDate() {
		return date;
	}


	public List<Integer> getArray() {
		return arrayIntegers;
	}


	public List<IndexDataBean> getEquilibriumIndices() {
		return equilibriumIndices;
	}


	@Override
	public String toString() {
		return "EquilibriumBean [date=" + date + ", arrayIntegers=" + arrayIntegers
				+ ", equilibriumIndices=" + equilibriumIndices + "]";
	}


	public void setEquilibriumIndices(List<IndexDataBean> equilibriumIndices) {
		this.equilibriumIndices = equilibriumIndices;
	}



	

	

	
	
	
}
