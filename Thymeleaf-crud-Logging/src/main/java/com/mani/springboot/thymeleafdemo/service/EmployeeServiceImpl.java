package com.mani.springboot.thymeleafdemo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.mani.springboot.thymeleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//No need @Transactional Annotation because Jpa Repository provides the functionality

	@Override      
	public List<Employee> findAll() {
		
		return employeeRepository.findAllByOrderByIdAsc();
	}

	@Override
	public Employee findById(int theId) {
		
		Optional<Employee> result = employeeRepository.findById(theId);
		
		Employee theEmployee=null;
		if(result.isPresent()) {
			
			theEmployee=result.get();
		}
		else {
			throw new RuntimeException("Did not find Employee Id : "+theId);
		}
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		employeeRepository.save(theEmployee);
		
	}

	@Override
	public void deleteById(int theId) {
		
		employeeRepository.deleteById(theId);
		
	}

}
