package com.emirates.master.data.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "flights")
public class FlightDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "FlightNo")
	@NotNull
	private String flightNo;
	
	@Column(name = "depArpt")
	@NotNull
	private String depArpt;
	
	@Column(name = "arrArpt")
	@NotNull
	private String arrArpt;
	
	@Column(name = "depTime")
	@NotNull
	private String depTime;
	
	@Column(name = "arrTime")
	@NotNull
	private String arrTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getDepArpt() {
		return depArpt;
	}

	public void setDepArpt(String depArpt) {
		this.depArpt = depArpt;
	}

	public String getArrArpt() {
		return arrArpt;
	}

	public void setArrArpt(String arrArpt) {
		this.arrArpt = arrArpt;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	
	public FlightDTO() {
		
	}

	public FlightDTO(String fltNo, String depArpt, String arrArpt, String depTime, String arrTime) {
		super();
		this.flightNo = fltNo;
		this.depArpt = depArpt;
		this.arrArpt = arrArpt;
		this.depTime = depTime;
		this.arrTime = arrTime;
	}

	@Override
	public String toString() {
		return "FlightDTO [id=" + id + ", fltNo=" + flightNo + ", depArpt=" + depArpt + ", arrArpt=" + arrArpt
				+ ", depTime=" + depTime + ", arrTime=" + arrTime + "]";
	}

}
