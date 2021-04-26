package com.emirates.master.data.service.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

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

import com.emirates.master.data.service.impl.AirportServiceImpl;
import com.emirates.master.data.service.model.AirportDTO;
import com.sun.istack.NotNull;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/master")
public class AirportController {

	@Autowired
	private AirportServiceImpl airportService;

	@GetMapping("/airports")
	public ResponseEntity<List<AirportDTO>> getAllAirports() {
		try {
			List<AirportDTO> airports = airportService.getAllAirports();
					if (airports.isEmpty()) {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
			return new ResponseEntity<>(airports, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/airports")
	public ResponseEntity<AirportDTO> addAirport(@RequestBody AirportDTO airportDTO) {
		AirportDTO airport = airportService.addAirport(airportDTO);
		if (Objects.isNull(airport)) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<AirportDTO>(airport, HttpStatus.OK);
	}

	@DeleteMapping("/airports/{airportCode}")
	public ResponseEntity<HttpStatus> deleteAirport(@PathVariable("airportCode") @NotNull String airportCode) {
		try {
			airportService.deleteAirport(airportCode);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/airports")
	public ResponseEntity<AirportDTO> updateAirportData(@RequestBody @Valid AirportDTO airportDTO) {
		try {
			AirportDTO airport = airportService.updateAirport(airportDTO);
			return new ResponseEntity<>(airport, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
