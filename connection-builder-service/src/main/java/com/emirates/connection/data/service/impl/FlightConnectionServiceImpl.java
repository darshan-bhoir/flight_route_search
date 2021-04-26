package com.emirates.connection.data.service.impl;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.emirates.connection.data.service.exception.BadRequestException;
import com.emirates.connection.data.service.exception.FlightConnectionNotFoundException;
import com.emirates.connection.data.service.model.FlightConnectionDTO;
import com.emirates.connection.data.service.model.FlightConnectionRequestDTO;
import com.emirates.connection.data.service.model.FlightDTO;
import com.emirates.connection.data.service.repository.FlightConnectionRepository;

@Service
public class FlightConnectionServiceImpl {

	@Autowired
	FlightConnectionRepository flightRepository;

	private static final Integer MINIMUM_DEBOARD_TIME=120;
	private static final Integer MAXIMUM_WAIT_TIME=480;

	private Logger logger = LoggerFactory.getLogger(FlightConnectionServiceImpl.class);

	public List<FlightConnectionDTO> searchFlight(FlightConnectionRequestDTO flightConnectionRequestDTO) {
		List<FlightConnectionDTO> flightConnectionDTOList = new ArrayList<FlightConnectionDTO>();
		if(StringUtils.hasText(flightConnectionRequestDTO.getArrAirport())
				&& StringUtils.hasText(flightConnectionRequestDTO.getDepAirport())) {
			List<FlightDTO> flights = flightRepository.findByDepArptContaining(flightConnectionRequestDTO.getDepAirport());
			if(!flights.isEmpty()) {
				flights.forEach(onwardFlight -> {
					if(onwardFlight.getDepArpt().equalsIgnoreCase(flightConnectionRequestDTO.getDepAirport())
							&& onwardFlight.getArrArpt().equalsIgnoreCase(flightConnectionRequestDTO.getArrAirport())) {
						FlightConnectionDTO flightConnectionDTO = createFlightConnectionRequestDTO(onwardFlight,null);
						flightConnectionDTOList.add(flightConnectionDTO);
					}else {
						List<FlightDTO> connectingFlights = flightRepository.findByDepArptContaining(onwardFlight.getArrArpt());
						connectingFlights.forEach(connectingFlight -> {
							if(connectingFlight.getArrArpt().equalsIgnoreCase(flightConnectionRequestDTO.getArrAirport())
									&& isValidConnectionTime(onwardFlight.getArrTime(), connectingFlight.getDepTime())) {
								FlightConnectionDTO flightConnectionDTO = createFlightConnectionRequestDTO(onwardFlight,connectingFlight);
								flightConnectionDTOList.add(flightConnectionDTO);
							}
						});
					}
				});

				if(flightConnectionDTOList.isEmpty()) {
					String errorMessage = String.format("No Flights found from departure airport %s to arrival airport %s ",flightConnectionRequestDTO.getDepAirport(), flightConnectionRequestDTO.getArrAirport());
					logger.error(errorMessage);
					throw new FlightConnectionNotFoundException("S002", errorMessage);
				} 
				return flightConnectionDTOList;
			}else {
				String errorMessage = String.format("No Flights found from departure airport %s to arrival airport %s ",flightConnectionRequestDTO.getDepAirport(), flightConnectionRequestDTO.getArrAirport());
				logger.error(errorMessage);
				throw new FlightConnectionNotFoundException("S002", errorMessage);
			}
		}else {
			String errorMessage = String.format("Departure airport or Arrival airport cannot be null or empty");
			logger.error(errorMessage);
			throw new BadRequestException("S001",errorMessage);
		}
	}

	private FlightConnectionDTO createFlightConnectionRequestDTO(FlightDTO onwardFlight, FlightDTO connectingFlight) {
		FlightConnectionDTO flightConnectionDTO = new FlightConnectionDTO();
		flightConnectionDTO.setOnwardFltNo(onwardFlight.getFlightNo());
		flightConnectionDTO.setOnwardDepArpt(onwardFlight.getDepArpt());
		flightConnectionDTO.setOnwardArrArpt(onwardFlight.getArrArpt());
		flightConnectionDTO.setOnwardDepTime(onwardFlight.getDepTime());
		flightConnectionDTO.setOnwardArrTime(onwardFlight.getArrTime());
		if(Objects.nonNull(connectingFlight)) {
			flightConnectionDTO.setConnFltNo(connectingFlight.getFlightNo());
			flightConnectionDTO.setConnDepArpt(connectingFlight.getDepArpt());
			flightConnectionDTO.setConnArrArpt(connectingFlight.getArrArpt());
			flightConnectionDTO.setConnDepTime(connectingFlight.getDepTime());
			flightConnectionDTO.setConnArrTime(connectingFlight.getArrTime());
		}
		return flightConnectionDTO;
	}

	private boolean isValidConnectionTime(String arrivalTime, String departureTime) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime localTime1 = LocalTime.parse(arrivalTime,dtf);
		LocalTime localTime2 = LocalTime.parse(departureTime,dtf);		
		LocalDateTime localDateTime1 = LocalDate.now().plusDays(0).atTime(localTime1);
		LocalDateTime localDateTime2 = LocalDate.now().plusDays(0).atTime(localTime2);
		Long minutes = MINUTES.between(localDateTime1, localDateTime2);		
		if(minutes>120 && minutes<480) {
			return true;
		}else {
			if(minutes<0) {
				localDateTime2 = LocalDate.now().plusDays(1).atTime(localTime2);
				minutes = MINUTES.between(localDateTime1, localDateTime2);
				if(minutes>MINIMUM_DEBOARD_TIME && minutes<MAXIMUM_WAIT_TIME) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}
	}
}
