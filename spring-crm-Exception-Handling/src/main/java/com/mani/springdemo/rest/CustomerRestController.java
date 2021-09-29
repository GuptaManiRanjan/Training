package com.mani.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mani.springdemo.entity.Customer;
import com.mani.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	//autowire the customer service
	@Autowired
	private CustomerService customerService;
	
	//add mapping for get / customers
	@GetMapping("/customers")
	public List<Customer> getCustomer(){
		
		return customerService.getCustomers();
	}
	
	//add mapping for get / customers/{customersId}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId){
		
		Customer theCustomer = customerService.getCustomer(customerId);
		
		if(theCustomer == null) {
			throw new CustomerNotFoundException("Customer Id not found : " + customerId);
		}
		
		return theCustomer;
	}
	
	//Add Mapping for Save
	@PostMapping("/customers")
	public Customer saveCustomer(@RequestBody Customer theCustomer){
		
		//set id 0/null force to hibernate to save
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	//Add Mapping for Update
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer){
			
			customerService.saveCustomer(theCustomer);
			return theCustomer;
	}
	
	//Add Mapping for Delete
		@DeleteMapping("/customers/{customerId}")
		public String deleteCustomer(@PathVariable int customerId){
				
			Customer theCustomer=customerService.getCustomer(customerId);
			
			//Check customer is exist or not
			if(theCustomer==null) {
				throw new CustomerNotFoundException("Customer Id not Found : " + customerId);
			}
			
				customerService.deleteCustomer(customerId);
				return "Deleted Customer Id : " + customerId;
		}
}
