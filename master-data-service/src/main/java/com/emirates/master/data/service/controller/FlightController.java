package com.emirates.master.data.service.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emirates.master.data.service.impl.FlightServiceImpl;
import com.emirates.master.data.service.model.FlightDTO;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/master")
public class FlightController {

	@Autowired
	private FlightServiceImpl flightService;

	@GetMapping("/flights")
	public ResponseEntity<List<FlightDTO>> getAllFlights() {
		try {
			List<FlightDTO> flights = flightService.getAllFlights();
			if (flights.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(flights, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/flights")
	public ResponseEntity<FlightDTO> addFlight(@RequestBody FlightDTO flightDTO) {
		//try {
			FlightDTO flight = flightService.addFlight(flightDTO);
			if (Objects.isNull(flight)) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<FlightDTO>(flight, HttpStatus.OK);
		/*} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
	}

	@DeleteMapping("/flights/{flightNo}")
	public ResponseEntity<HttpStatus> deleteFlight(@PathVariable("flightNo") @NotNull String flightNo) {
		try {
			flightService.deleteFlight(flightNo);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/flights")
	public ResponseEntity<FlightDTO> updateFlight(@RequestBody @Valid FlightDTO flightDTO) {
		try {
			FlightDTO flight = flightService.updateFlight(flightDTO);
			return new ResponseEntity<>(flight, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
