package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hi")
public class HiController {

	@GetMapping
	public String hi() {
		return "Welcome to Hi Microservice";
	}
	
	@GetMapping("/hi")
	public String hii() {
		return "Welcome to Hi Microservice";
	}
}
