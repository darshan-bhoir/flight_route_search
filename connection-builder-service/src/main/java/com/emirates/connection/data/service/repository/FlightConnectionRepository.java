package com.emirates.connection.data.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emirates.connection.data.service.model.FlightDTO;

public interface FlightConnectionRepository extends JpaRepository<FlightDTO, Long>{

	  List<FlightDTO> findByDepArptContaining(String depArpt);
}
