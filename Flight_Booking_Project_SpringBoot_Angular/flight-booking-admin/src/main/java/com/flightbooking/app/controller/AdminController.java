package com.flightbooking.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AdminController 
{
   @PostMapping("/login")
   public String adminLogin()
   {
	   String username="admin";
	   String password="admin";
	   return "";
   }
}
