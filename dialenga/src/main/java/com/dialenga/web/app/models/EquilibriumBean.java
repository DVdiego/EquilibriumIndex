package com.dialenga.web.app.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder(toBuilder = true)
@Data
@Entity()
@Table(name = "EQUILIBRIUMS")
public class EquilibriumBean {

	@JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@JsonProperty("fecha_solicitud")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	@Column(name = "fecha_solicitud", nullable = false)
	private Date date;


	@JsonProperty("array_enteros")
	@Column(name = "array_enteros", nullable = false)
	private String arrayIntegers;


	@JsonProperty("indices_equilibrio")
	@JsonFormat
	@Column(name = "indices_equilibrio", nullable = false)
	private String equilibriumIndices;


	public EquilibriumBean(Date date, String arrayIntegers, String equilibriumIndices) {
		this.date = date;
		this.arrayIntegers = arrayIntegers;
		this.equilibriumIndices = equilibriumIndices;
	}

	@Tolerate
	public EquilibriumBean() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getArrayIntegers() {
		return arrayIntegers;
	}


	public void setArrayIntegers(String arrayIntegers) {
		this.arrayIntegers = arrayIntegers;
	}


	public String getEquilibriumIndices() {
		return equilibriumIndices;
	}


	public void setEquilibriumIndices(String equilibriumIndices) {
		this.equilibriumIndices = equilibriumIndices;
	}


	@Override
	public int hashCode() {
		return Objects.hash(arrayIntegers, date, equilibriumIndices, id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquilibriumBean other = (EquilibriumBean) obj;
		return Objects.equals(arrayIntegers, other.arrayIntegers) && Objects.equals(date, other.date)
				&& Objects.equals(equilibriumIndices, other.equilibriumIndices) && Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "EquilibriumBean [date=" + date + ", arrayIntegers=" + arrayIntegers + ", equilibriumIndices="
				+ equilibriumIndices + "]";
	}

}
