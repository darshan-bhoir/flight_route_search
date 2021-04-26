package com.emirates.master.data.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emirates.master.data.service.model.FlightDTO;

public interface FlightRepository extends JpaRepository<FlightDTO, Long>{

	  List<FlightDTO> findByFlightNoContaining(String fltNo);
}
