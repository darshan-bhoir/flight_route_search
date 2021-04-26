package com.emirates.master.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.emirates.master.data.service.exception.BadRequestException;
import com.emirates.master.data.service.exception.FlightNotFoundException;
import com.emirates.master.data.service.model.AirportDTO;
import com.emirates.master.data.service.repository.AirportRepository;


@Service
public class AirportServiceImpl {

	@Autowired
	AirportRepository airportRepository;
	
	private Logger logger = LoggerFactory.getLogger(AirportServiceImpl.class);

	public List<AirportDTO> getAllAirports(){
		List<AirportDTO> airports = new ArrayList<>();
		airportRepository.findAll().forEach(airport -> {
			airports.add(airport);
		});
		return airports;
	}

	public AirportDTO addAirport(AirportDTO airportDTO) {
		if(StringUtils.hasText(airportDTO.getAirportCode())) {
			List<AirportDTO> airports = airportRepository.findByAirportCodeContaining(airportDTO.getAirportCode());
			if(airports.isEmpty()) {
				AirportDTO airport = airportRepository
						.save(new AirportDTO(
								airportDTO.getAirportCode(),
								airportDTO.getAirportName(),
								airportDTO.getCityName(),
								airportDTO.getLongitude(),
								airportDTO.getLatitude()
								)
							);
				return airport;
			}else {
				String errorMessage = String.format("Data for Flight with Flight Number - %s already exist", airportDTO.getAirportCode());
				logger.error(errorMessage);
				throw new BadRequestException("S002", errorMessage);
			}
		}else {
			String errorMessage = String.format("Flight number cannot be null or empty");
			logger.error(errorMessage);
			throw new BadRequestException("S001",errorMessage);
		}

	}

	public AirportDTO updateAirport(AirportDTO airportDTO) {
		if(StringUtils.hasText(airportDTO.getAirportCode())) {
			List<AirportDTO> airports = airportRepository.findByAirportCodeContaining(airportDTO.getAirportCode());
			if(!airports.isEmpty()) {
				AirportDTO airport = airports.get(0);
				airport.setAirportCode(airportDTO.getAirportCode());
				airport.setAirportName(airportDTO.getAirportName());
				airport.setCityName(airportDTO.getCityName());
				airport.setLatitude(airportDTO.getLatitude());
				airport.setLongitude(airportDTO.getLongitude());
				return airportRepository.save(airport);
			}else {
				String errorMessage = String.format("Data for Flight with Flight Number - %s does not exist", airportDTO.getAirportCode());
				logger.error(errorMessage);
				throw new FlightNotFoundException("S002",errorMessage);
			} 

		}else {
			String errorMessage = String.format("Flight number cannot be null or empty");
			logger.error(errorMessage);
			throw new BadRequestException("S001",errorMessage);
		}
	}

	public void deleteAirport(String airportCode) {
		if(StringUtils.hasText(airportCode)) {
			List<AirportDTO> airports = airportRepository.findByAirportCodeContaining(airportCode);
			if(!airports.isEmpty()) {
				airportRepository.delete(airports.get(0));
			}else {
				String errorMessage = String.format("Data for Flight with Flight Number - %s does not exist", airportCode);
				logger.error(errorMessage);
				throw new FlightNotFoundException("S002",errorMessage);
			} 
		}else {
			String errorMessage = String.format("Flight number cannot be null or empty");
			logger.error(errorMessage);
			throw new BadRequestException("S001",errorMessage);
		}

	}
}
