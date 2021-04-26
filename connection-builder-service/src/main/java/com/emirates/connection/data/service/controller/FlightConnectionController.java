package com.emirates.connection.data.service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emirates.connection.data.service.impl.FlightConnectionServiceImpl;
import com.emirates.connection.data.service.model.FlightConnectionDTO;
import com.emirates.connection.data.service.model.FlightConnectionRequestDTO;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/search")
public class FlightConnectionController {

	@Autowired
	private FlightConnectionServiceImpl flightService;

	@PostMapping("/flights")
	public ResponseEntity<List<FlightConnectionDTO>> getAllFlights(@Valid @RequestBody FlightConnectionRequestDTO flightConnectionRequestDTO) {
		List<FlightConnectionDTO> flights = flightService.searchFlight(flightConnectionRequestDTO);
		if (flights.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}

}
