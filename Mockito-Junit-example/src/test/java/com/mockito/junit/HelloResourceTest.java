package com.mockito.junit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mockito.junit.HelloResource;
import com.mockito.junit.service.ServiceClass;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
class HelloResourceTest {

	private MockMvc mockMvc;

	@InjectMocks
	private HelloResource helloResource;

	@Mock
	ServiceClass serviceClass;

	@BeforeEach
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(helloResource).build();
	}

	@Test
	public void getHelloTrueTest() throws Exception {
		when(serviceClass.boolMethod()).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/hi")).andExpect(status().isOk())
				.andExpect(content().string("Mani"));

	}
	
	@Test
	public void getHelloFalseTest() throws Exception {
		when(serviceClass.boolMethod()).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/hi")).andExpect(status().isOk())
				.andExpect(content().string("Gupta"));

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testHelloWorld3() throws Exception {
		when(serviceClass.boolMethod()).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/hi")).andExpect(status().isOk())
				.andExpect(content().string("Mani"));

	}
	
	
	@Test
	public void testHelloWorld4() throws Exception {
		when(serviceClass.boolMethod()).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/hi")).andExpect(status().isOk())
				.andExpect(content().string("Gupta"));

	}

}
