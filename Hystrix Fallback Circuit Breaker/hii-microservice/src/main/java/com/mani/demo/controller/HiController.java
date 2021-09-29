package com.mani.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/hi")
public class HiController {

	@GetMapping
	@HystrixCommand(fallbackMethod = "getDataFallBack")
	public String hi() {
				
		System.out.println("Inside Normal Method");		
	
			throw new RuntimeException();
				
		//return "Welcome to Hi Microservice";
	}
	
	
	public String getDataFallBack() {
		System.out.println("Inside Fallback Method");
		return "Inside Fallback Method";
	}
}
