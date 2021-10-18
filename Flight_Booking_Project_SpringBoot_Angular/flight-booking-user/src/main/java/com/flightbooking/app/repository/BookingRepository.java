package com.flightbooking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flightbooking.app.model.BookingEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer>
{
	@Query("from BookingEntity where pnrNo=:pnrNo")
    BookingEntity fetchTicketDetailsByPNR(@Param("pnrNo") String pnr);
	
	@Query("from BookingEntity where email=:email")
    List<BookingEntity> bookedTicketListByEmail(@Param("email") String email);    

}
