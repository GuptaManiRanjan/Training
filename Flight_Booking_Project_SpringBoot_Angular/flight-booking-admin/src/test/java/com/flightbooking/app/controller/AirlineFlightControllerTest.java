package com.flightbooking.app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.flightbooking.app.serviceImpl.AirlineFlightServiceImpl;

public class AirlineFlightControllerTest {

	List al=new ArrayList<>();		
	
	MockMvc mockMvc;	
	
	@InjectMocks
	private AirlineFlightController airlineFlightController;
	
	@Mock
	AirlineFlightServiceImpl flightService;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
		this.mockMvc=MockMvcBuilders.standaloneSetup(airlineFlightController).build();		
	}
	
	@Test
	public void getAllFlightsTest() throws Exception {
		al.add("flight1");
		al.add("flight2");
		when(flightService.getAllFlights()).thenReturn(al);
		mockMvc.perform(MockMvcRequestBuilders.get("/flight/airline/flights")).andExpect(status().isOk());
	}	
	
	@Test
	public void deleteFlightTrueTest() throws Exception {
		when(flightService.deleteById(5)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.get("/flight/airline/deleteFlight").param("id", "5")).andExpect(status().isOk())
		.andExpect(content().string("Record deleted successfully.."));
	}
	
	@Test
	public void deleteFlightFalseTest() throws Exception {
		when(flightService.deleteById(5)).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.get("/flight/airline/deleteFlight").param("id", "5")).andExpect(status().isOk())
		.andExpect(content().string("Something went wrong!"));
	}
	
	@Test
	public void getFlightsDetailTest() throws Exception {
		al.add("flight1");
		al.add("flight2");
		when(flightService.findFlights("Delhi","Mumbai","2021-11-11")).thenReturn(al);
		mockMvc.perform(MockMvcRequestBuilders.get("/flight/airline/search").param("source", "Delhi").param("destination", "Mumbai").param("date", "2021-11-11")).andExpect(status().isOk());
	}
	
	@Test
	public void addFlightTest() throws Exception{
		when(flightService.addUpdateFlight(null)).thenReturn(1);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(null );
		mockMvc.perform(MockMvcRequestBuilders.post("/flight/airline/addFlight").content(requestJson)).andExpect(status().is4xxClientError());
	}
}
