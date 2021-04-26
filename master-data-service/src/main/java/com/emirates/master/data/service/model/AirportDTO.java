package com.emirates.master.data.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "airports")
public class AirportDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "airportCode")
	@NotNull
	private String airportCode;
	
	@Column(name = "airportName")
	@NotNull
	private String airportName;
	
	@Column(name = "cityName")
	@NotNull
	private String cityName;
	
	@Column(name = "longitude")
	@NotNull
	private String longitude;
	
	@Column(name = "latitude")
	@NotNull
	private String latitude;
	
	public AirportDTO() {
		
	}

	public AirportDTO(String airportCode,  String airportName, String cityName,
			String longitude, String latitude) {
		super();
		this.airportCode = airportCode;
		this.airportName = airportName;
		this.cityName = cityName;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	

}
