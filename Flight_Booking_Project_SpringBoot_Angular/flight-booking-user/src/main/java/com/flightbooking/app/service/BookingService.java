package com.flightbooking.app.service;

import java.util.List;

import com.flightbooking.app.model.BookingEntity;

public interface BookingService 
{
  public BookingEntity bookFlightTicket(BookingEntity bookingEntity);
  public BookingEntity fetchTicketDetailsByPNR(String pnr);
  public List bookedTicketListByEmail(String email);
  public void deleteBookedTicket(int bookingId);
  public List<BookingEntity> bookedTicketListByAdmin();
	
}
