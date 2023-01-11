package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> getListOfEmployees();
	
	public Optional<Employee> getEmployee(Long employee);
	
	public Employee saveEmployee(Employee employee);
	
	public Employee updateEmployee(Employee employeeId);
	
	public String deleteEmployee(Long employeeId);

	public void saveAll(List<Employee> employee);
	


}
