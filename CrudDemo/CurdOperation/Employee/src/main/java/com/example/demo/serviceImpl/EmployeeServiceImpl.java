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
	public Employee getEmployee(Long employeeId) {
		return employeeRepository.findByEmpId(employeeId);
	}
	//
	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}
	
	@Override
	public Employee updateEmployee(Employee employee) {
		
		Employee emp = new Employee();
		
		emp.setEmpId(employee.getEmpId());
		emp.setEmpName(employee.getEmpName());
		emp.setCity(employee.getCity());
		
		return employeeRepository.save(emp);
	}
	@Override
	public String deleteEmployee(long employeeId) {
		
		  employeeRepository.deleteById(employeeId);
		  return "deleted successfully";
	
	}
	@Override
	public List<Employee> saveMultipleRecords(List<Employee> employee) {
		return employeeRepository.saveAll(employee);
		
	}
	

	@Override
	public List<Employee> getListOfEmployees() {
		return employeeRepository.findAll();
	}

	

}
