package com.emirates.master.data.service;

import java.util.Arrays;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.emirates.master.data.service.model.AirportDTO;
import com.emirates.master.data.service.model.FlightDTO;
import com.emirates.master.data.service.repository.AirportRepository;
import com.emirates.master.data.service.repository.FlightRepository;

@SpringBootApplication
public class MasterDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterDataServiceApplication.class, args);
	}

	@Bean
	public ApplicationRunner initializer(AirportRepository airportRepository,FlightRepository flightRepository) {
		airportRepository.saveAll(Arrays.asList(
				new AirportDTO("BOM",  "Chhatrapati Shivaji Maharaj International Airport", "Mumbai","19°05′19″N", "72°52′05″E"),
				new AirportDTO("DXB",  "Dubai Internation Airport", "Dubai","25°15′10″N", "055°21′52″E"),
				new AirportDTO("JFK",  "John F. Kennedy International Airport", "New York","40°38′23″N", "073°46′44″W"),
				new AirportDTO("IAH",  "George Bush Intercontinental Airport", "Houston","29°59′04″N", "095°20′29″W")
				));
		return args -> flightRepository.saveAll(Arrays.asList(
				new FlightDTO("501", "BOM", "DXB", "4:30", "6:00"),
				new FlightDTO("2137", "BOM", "DXB", "5:10", "6:50"),
				new FlightDTO("507", "BOM", "DXB", "15:35", "17:20"),
				new FlightDTO("503", "BOM", "DXB", "19:20", "21:00"),
				new FlightDTO("201", "DXB", "JFK", "8:30", "14:25"),
				new FlightDTO("203", "DXB", "JFK", "2:50", "8:50"),
				new FlightDTO("205", "DXB", "JFK", "9:45", "19:00"),
				new FlightDTO("211", "DXB", "IAH", "9:35", "16:50")
				));
	}
}


