package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	
	@GetMapping("/getListOfEmployees")
     public List<Employee> getListOfEmployees()
	{
		return employeeService.getListOfEmployees();
    	 
     }
	@PostMapping("/multipleStudentSave")
	public List<Employee> saveListOfEmployee(@RequestBody List<Employee> employee){
		return employeeService.saveMultipleRecords(employee);
	}
	
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable Long employeeId ){
		return employeeService.getEmployee(employeeId);
	}
	
	@PostMapping("/saveEmployees")
    public Employee saveEmployee(@RequestBody Employee employee) {
		
	   return employeeService.saveEmployee(employee);
  
	}
	@PutMapping("/updateEmployees/{employeeId}")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}
	
	@DeleteMapping("/deleteEmployees/{employeeId}")
	public String deleteEmployee(@PathVariable Long employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}
	
}










































