package com.flightbooking.app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.flightbooking.app.model.BookingEntity;
import com.flightbooking.app.serviceImpl.BookingServiceImpl;


class BookingControllerTest {

	MockMvc mockMvc;	
	
	@InjectMocks
	private BookingController bookingController;
	
	@Mock
	BookingServiceImpl bookingService;
	List al=new ArrayList<>();	
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
		this.mockMvc=MockMvcBuilders.standaloneSetup(bookingController).build();		
	}
	
	@Test
	public void fetchTicketDetailsByPNRTest() throws Exception {
		String pnr="PNRTest";
		when(bookingService.fetchTicketDetailsByPNR(pnr)).thenReturn(new BookingEntity());
		mockMvc.perform(MockMvcRequestBuilders.get("/flight/ticket/view/"+pnr)).andExpect(status().isOk());
	}
	
	@Test
	public void bookedTicketListTest() throws Exception {
		al.add("flight1");
		al.add("flight2");
		String email="manigupta@gmail.com";
		when(bookingService.bookedTicketListByEmail(email)).thenReturn(al);
		mockMvc.perform(MockMvcRequestBuilders.get("/flight/ticket/list/"+email)).andExpect(status().isOk());
	}
	
	@Test
	public void deleteBookedTicketTest() throws Exception {
		int bookingId=1;
		mockMvc.perform(MockMvcRequestBuilders.get("/flight/ticket/delete/"+bookingId)).andExpect(status().is4xxClientError());
	}

	@Test
	public void bookFlightTicketTest() throws Exception {
		when(bookingService.bookFlightTicket(null)).thenReturn(new BookingEntity());
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(null );
		mockMvc.perform(MockMvcRequestBuilders.post("/flight/booking/ticket").content(requestJson)).andExpect(status().is4xxClientError());
	}
}
