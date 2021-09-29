package com.mani.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mani.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	//that's it there is no need to write any code ......NO need to DAO .....

	//add a method to sort by last Name
	public List<Employee> findAllByOrderByIdAsc();
	
}
