package com.example.demo.serviceImpl;

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
	public Employee updateEmployee(Employee employee) {
		
		Employee i =  employeeRepository.findById(employee.getEmpId()).orElse(null);
		
		i.setEmpId(employee.getEmpId());
		i.setEmpName(employee.getEmpName());
		i.setCity(employee.getCity());
		
		return employeeRepository.save(i);
	}
	@Override
	public String deleteEmployee(Long employeeId) {
		
		  employeeRepository.deleteById(employeeId);
		  return "deleted successfully";
	
	}
	@Override
	public List<Employee> saveAll(List<Employee> employee) {
		return employeeRepository.saveAll(employee);
		
	}
	

	@Override
	public List<Employee> getListOfEmployees() {
		return employeeRepository.findAll();
	}
	

}
