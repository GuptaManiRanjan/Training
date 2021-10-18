package com.flightbooking.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightbooking.app.model.BookingEntity;
import com.flightbooking.app.repository.BookingRepository;
import com.flightbooking.app.repository.UserRepository;
import com.flightbooking.app.service.BookingService;
@Service
public class BookingServiceImpl implements BookingService 
{

	@Autowired
	BookingRepository bookingRepositiry;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public BookingEntity bookFlightTicket(BookingEntity bookingEntity) {
		
		bookingRepositiry.save(bookingEntity);
		return bookingRepositiry.save(bookingEntity);
	}

	@Override
	public BookingEntity fetchTicketDetailsByPNR(String pnr) {
		BookingEntity bookingEntiy=bookingRepositiry.fetchTicketDetailsByPNR(pnr);
		
		return bookingEntiy;
	}
	
	@Override
	public List<BookingEntity> bookedTicketListByEmail(String email) {
		return bookingRepositiry.bookedTicketListByEmail(email);
		
	}
	
	@Override
	public void deleteBookedTicket(int bookingId){
		
		 bookingRepositiry.deleteById(bookingId);

	}

	@Override
	public List<BookingEntity> bookedTicketListByAdmin() {
		
		return bookingRepositiry.findAll();
	}

}
