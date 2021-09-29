package com.mockito.junit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockito.junit.entity.UserDetails;
import com.mockito.junit.service.ServiceClass;

@RestController
@RequestMapping("/api")
public class HelloResource {
	
	@Autowired
	ServiceClass serviceClass;
	
	
	@GetMapping("/hi")
	public String getHello() {
		
		if(serviceClass.boolMethod())
		{
			return "Mani";
		}		
		else
		{
			return "Gupta";
		}
			
	}
	
	
	
	@PostMapping("/hi")	
	public String helloBharat(@ModelAttribute("userDetails") UserDetails userDetails, Model model) {		
		if(serviceClass.boolMethod()) {
			model.addAttribute("username", userDetails.getUsername());
			model.addAttribute("password", userDetails.getPassword());
			return "Mani";
		}
		else
			return "Gupta";
	}

}
