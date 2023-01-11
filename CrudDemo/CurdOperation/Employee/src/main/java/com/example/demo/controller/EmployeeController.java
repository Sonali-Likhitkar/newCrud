package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;

import com.example.demo.service.EmployeeService;


@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService  employeeService;


	@GetMapping("/home")
	public String home() {
		return "this is home page";
	}
	
	@GetMapping("/employees")
     public List<Employee> getListOfEmployees()
	{
		return employeeService.getListOfEmployees();
    	 
     }
	
	@GetMapping("/employees/{employeeId}")
	public Optional<Employee> getEmployee(@PathVariable Long employeeId ){
		return employeeService.getEmployee(employeeId);
	}
	
	@PostMapping("/saveEmployees")
    public Employee saveEmployee(@RequestBody Employee employee) {
		
	   return employeeService.saveEmployee(employee);
  
	}
	@PutMapping("/updateEmployees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}
	
	@DeleteMapping("/deleteEmployees/{employeeId}")
	public String deleteEmployee(@PathVariable Long employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}
	
	@PostMapping("/multipleStudentSave")
	public String insertStudent(@RequestBody List<Employee> employee ) {
		employeeService.saveAll(employee);
		return "your record is saved sucessfully !!";
	
	}
	


}









































