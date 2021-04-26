package com.emirates.connection.data.service;

import java.util.Arrays;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.emirates.connection.data.service.model.FlightDTO;
import com.emirates.connection.data.service.repository.FlightConnectionRepository;

@SpringBootApplication
public class ConnectionBuilderDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectionBuilderDataServiceApplication.class, args);
	}
	
	@Bean
	public ApplicationRunner initializer(FlightConnectionRepository flightRepository) {
		return args -> flightRepository.saveAll(Arrays.asList(
				new FlightDTO("501", "BOM", "DXB", "04:30", "06:00"),
				new FlightDTO("2137", "BOM", "DXB", "05:10", "06:50"),
				new FlightDTO("507", "BOM", "DXB", "15:35", "17:20"),
				new FlightDTO("503", "BOM", "DXB", "19:20", "21:00"),
				new FlightDTO("201", "DXB", "JFK", "08:30", "14:25"),
				new FlightDTO("203", "DXB", "JFK", "02:50", "08:50"),
				new FlightDTO("205", "DXB", "JFK", "09:45", "19:00"),
				new FlightDTO("211", "DXB", "IAH", "09:35", "16:50")
				));
	}

}
