package com.mani.springboot.thymeleafdemo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mani.springboot.thymeleafdemo.entity.Employee;
import com.mani.springboot.thymeleafdemo.service.EmployeeService;

//@RestController
//@RequestMapping("/emp")
//@CrossOrigin(origins = "http://localhost:4200")
public class EmpRestController {

	private static final Logger LOGGER = LogManager.getLogger(EmpRestController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/list")	
	public List listEmployee() {
		LOGGER.info("Employee List");
		LOGGER.debug("Employee List");
		System.out.println("Mani List Employee-------------------------------------------------------");
		//get Employee from db
		List<Employee> theEmployees=employeeService.findAll();		
		return theEmployees;
	}
	
	@PostMapping("/save")
		public String saveEmployee(@RequestBody Employee theEmployee) {
		System.out.println("Save Employee-------------------------------------------------------");
		LOGGER.info("Save Employee");
		 employeeService.save(theEmployee);
		
		 return "Success";
	}

}
