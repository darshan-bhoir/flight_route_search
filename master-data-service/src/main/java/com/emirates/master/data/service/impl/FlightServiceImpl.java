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
import com.emirates.master.data.service.model.FlightDTO;
import com.emirates.master.data.service.repository.FlightRepository;


@Service
public class FlightServiceImpl {

	@Autowired
	FlightRepository flightRepository;
	
	private Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

	public List<FlightDTO> getAllFlights(){
		List<FlightDTO> flights = new ArrayList<>();
		flightRepository.findAll().forEach(flight -> {
			flights.add(flight);
		});
		return flights;
	}

	public FlightDTO addFlight(FlightDTO flightDTO) {
		if(StringUtils.hasText(flightDTO.getFlightNo())) {
			List<FlightDTO> flights = flightRepository.findByFlightNoContaining(flightDTO.getFlightNo());
			if(flights.isEmpty()) {
				FlightDTO flight = flightRepository
						.save(new FlightDTO(
								flightDTO.getFlightNo(),
								flightDTO.getDepArpt(),
								flightDTO.getArrArpt(),
								flightDTO.getDepTime(),
								flightDTO.getArrTime()));
				return flight;
			}else {
				String errorMessage = String.format("Data for Flight with Flight Number - %s already exist", flightDTO.getFlightNo());
				logger.error(errorMessage);
				throw new BadRequestException("S002", errorMessage);
			}
		}else {
			String errorMessage = String.format("Flight number cannot be null or empty");
			logger.error(errorMessage);
			throw new BadRequestException("S001",errorMessage);
		}

	}

	public FlightDTO updateFlight(FlightDTO flightDTO) {
		if(StringUtils.hasText(flightDTO.getFlightNo())) {
			List<FlightDTO> flights = flightRepository.findByFlightNoContaining(flightDTO.getFlightNo());
			if(!flights.isEmpty()) {
				FlightDTO flight = flights.get(0);
				flight.setArrArpt(flightDTO.getArrArpt());
				flight.setArrTime(flightDTO.getArrTime());
				flight.setDepArpt(flightDTO.getDepArpt());
				flight.setDepTime(flightDTO.getDepTime());
				return flightRepository.save(flight);
			}else {
				String errorMessage = String.format("Data for Flight with Flight Number - %s does not exist", flightDTO.getFlightNo());
				logger.error(errorMessage);
				throw new FlightNotFoundException("S002",errorMessage);
			} 

		}else {
			String errorMessage = String.format("Flight number cannot be null or empty");
			logger.error(errorMessage);
			throw new BadRequestException("S001",errorMessage);
		}
	}

	public void deleteFlight(String flightNo) {
		if(StringUtils.hasText(flightNo)) {
			List<FlightDTO> flights = flightRepository.findByFlightNoContaining(flightNo);
			if(!flights.isEmpty()) {
				flightRepository.delete(flights.get(0));
			}else {
				String errorMessage = String.format("Data for Flight with Flight Number - %s does not exist", flightNo);
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
