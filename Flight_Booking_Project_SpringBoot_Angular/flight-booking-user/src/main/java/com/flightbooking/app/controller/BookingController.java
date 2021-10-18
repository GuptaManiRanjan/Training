package com.flightbooking.app.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.app.model.BookingEntity;
import com.flightbooking.app.serviceImpl.BookingServiceImpl;
import com.flightbooking.app.utils.NotificationByEmail;

@RestController
@RequestMapping("/flight")
@CrossOrigin(origins = "*")
public class BookingController 
{
	private static Logger log = Logger.getLogger(BookingController.class);

	@Autowired
	BookingServiceImpl bookingService;
	@Autowired
	NotificationByEmail notificationByEmail;
	
	@PostMapping("/booking/ticket")
	public BookingEntity bookFlightTicket(@RequestBody BookingEntity bookingEntity)
	{
		log.info(">>>>>booking flight ticket>>>>");
		BookingEntity entity = bookingService.bookFlightTicket(bookingEntity);		
		if(entity!=null) {
			String email = bookingEntity.getEmail();
			String pnr = bookingEntity.getPnrNo();
			String msg ="Flight Ticket Book successfully. Your Flight Ticket Pnr is : "+pnr;
			notification(email, msg);
			return entity;		
		}
		else {
			return null;
		}
	}
	
	@GetMapping("/ticket/view/{pnr}")
	public BookingEntity fetchTicketDetailsByPNR(@PathVariable String pnr)
	{
		log.info(">>>>>>>>PNR>>>>>>>>>>>>"+pnr);
		BookingEntity entity=bookingService.fetchTicketDetailsByPNR(pnr);
		log.info(">>>>PNR>>>>>>:"+entity);
		return entity;
	}
	
	@GetMapping("/ticket/list/{email}")
	public List<BookingEntity> bookedTicketList(@PathVariable String email)
	{
		log.info(">>>>>>>>Email>>>>>>>>>>>>"+email);
		return bookingService.bookedTicketListByEmail(email);

	}
	
	@DeleteMapping("/ticket/delete/{bookingId}")
	public void deleteBookedTicket(@PathVariable int bookingId)
	{
		log.info(">>>>>>>>bookingId>>>>>>>>>>>>"+bookingId);
		bookingService.deleteBookedTicket(bookingId);		
		
	}
	
	@GetMapping("/ticket/lists")
	public List<BookingEntity> bookedTicketListByAdmin()
	{
		log.info(">>>>>>>>bookedTicketListByAdmin>>>>>>>>>>>>");
		return bookingService.bookedTicketListByAdmin();

	}
	
	public void notification(String email, String msg){
		try {
			notificationByEmail.mailOTP(email, msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
			log.info("Mail SMTP Server is Busy......");
		}
	}
}
