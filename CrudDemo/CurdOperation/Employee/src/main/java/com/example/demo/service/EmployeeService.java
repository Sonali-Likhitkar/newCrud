package com.example.demo.service;

import java.util.List;


import com.example.demo.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> getListOfEmployees();
	
	public Employee getEmployee(Long employee);
	
	public Employee saveEmployee(Employee employee);
	
	public Employee updateEmployee(Employee employeeId);
	
	public String deleteEmployee(long l);

	public List<Employee> saveMultipleRecords(List<Employee> employee);
	


}
