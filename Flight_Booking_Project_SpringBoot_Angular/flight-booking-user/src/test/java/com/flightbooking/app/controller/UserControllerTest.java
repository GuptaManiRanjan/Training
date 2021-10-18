package com.flightbooking.app.controller;

import static org.mockito.Mockito.when;
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
import com.flightbooking.app.model.UserEntity;
import com.flightbooking.app.serviceImpl.UserServiceImpl;

class UserControllerTest {

	MockMvc mockMvc;	
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	UserServiceImpl userService;
	
	List al=new ArrayList<>();	
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
		this.mockMvc=MockMvcBuilders.standaloneSetup(userController).build();		
	}
	
	@Test
	public void userRegisterTest() throws Exception {
		when(userService.userRegister(null)).thenReturn(1);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(null );
		mockMvc.perform(MockMvcRequestBuilders.post("/flight/user/register").content(requestJson)).andExpect(status().is4xxClientError());
	}

	@Test
	public void userLoginTrueTest() throws Exception {
	    UserEntity obj=new UserEntity();
	    obj.setEmail("mani@gupta.com");
	    obj.setPassword("password");
		when(userService.userLogin(obj)).thenReturn(true);
		ObjectMapper mapper =  new ObjectMapper();
		String requestJson = mapper.writeValueAsString(obj);
		mockMvc.perform(MockMvcRequestBuilders.post("/flight/user/login").content(requestJson)).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void userLoginFalseTest() throws Exception {
	    UserEntity obj=new UserEntity();
	    obj.setEmail("mani@gupta.com");
	    obj.setPassword("password");
		when(userService.userLogin(obj)).thenReturn(false);
		ObjectMapper mapper =  new ObjectMapper();
		String requestJson = mapper.writeValueAsString(obj);
		mockMvc.perform(MockMvcRequestBuilders.post("/flight/user/login").content(requestJson)).andExpect(status().is4xxClientError());
	}
}
