package com.emirates.master.data.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emirates.master.data.service.model.AirportDTO;

public interface AirportRepository extends JpaRepository<AirportDTO, Long>{

	  List<AirportDTO> findByAirportCodeContaining(String airportCode);
}
