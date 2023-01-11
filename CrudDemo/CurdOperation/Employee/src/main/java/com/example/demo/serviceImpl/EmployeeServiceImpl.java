package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	List<Employee> list;

	@Override
	public Optional<Employee> getEmployee(Long employeeId) {
		return employeeRepository.findById(employeeId);
	}
	//
	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}
	
	@Override
	public Employee updateEmployee(Employee employeeId) {
		
		Employee i =  employeeRepository.findById(employeeId.getEmpId()).orElse(null);
		
		i.setEmpId(employeeId.getEmpId());
		i.setEmpName(employeeId.getEmpName());
		i.setCity(employeeId.getCity());
		
		return employeeRepository.save(i);
	}
	@Override
	public String deleteEmployee(Long employeeId) {
		
		  employeeRepository.deleteById(employeeId);
		  return "deleted successfully";
	
	}
	@Override
	public void saveAll(List<Employee> employee) {
		employeeRepository.saveAll(employee);
		
	}

	@Override
	public List<Employee> getListOfEmployees() {
		return employeeRepository.findAll();
	}
	

}
