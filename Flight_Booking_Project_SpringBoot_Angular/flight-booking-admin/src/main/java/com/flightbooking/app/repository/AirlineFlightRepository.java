package com.flightbooking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flightbooking.app.entity.AirlineFlightEntity;

public interface AirlineFlightRepository extends JpaRepository<AirlineFlightEntity, Integer>
{
	@Query("from AirlineFlightEntity where source=:source and destination=:destination and status=:status")
    List<AirlineFlightEntity> findFlights(@Param("source") String source, @Param("destination") String destination, @Param("status") String status);
    
    @Modifying
    @Query(value="update flight set status=:status where airline_name=:airlineName", nativeQuery=true)
    void blockAirline(String status, String airlineName);
	
}
