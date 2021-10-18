package com.flightbooking.app.service;

import java.util.List;

import com.flightbooking.app.entity.AirlineFlightEntity;

public interface AirlineFlightService {

	public int addUpdateFlight(AirlineFlightEntity flightEntity);
	public List<AirlineFlightEntity> findFlights(String source, String destination, String date);
	public List<AirlineFlightEntity> getAllFlights();
	public void blockAirline(String number, String flag);
	public boolean deleteById(int id);
}
