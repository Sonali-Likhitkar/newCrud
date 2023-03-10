package com.example.demo.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.serviceImpl.EmployeeServiceImpl;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	

	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImpl  employeeServiceImpl;
	
	@Autowired
	private EmployeeService employeeService;
	
	private Employee employee;
	
	
	@Test
	public void saveEmployee() {
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertThat(employeeServiceImpl.saveEmployee(employee)).isEqualTo(employee);
	}
	
	@Test
	public void findById() {
		
		
		when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getEmployee()));
		Employee actualOutput=employeeServiceImpl.getEmployee((long)1).get();
		assertThat(actualOutput).isNotNull();

	}
	

	@Test
	public void findAllEmployee() {
		List<Employee> employee = getEmployeeList();
		when(employeeRepository.findAll()).thenReturn(employee);
		List<Employee> actual=employeeServiceImpl.getListOfEmployees();
		assertThat(actual).isNotNull();
		
	
	}
	
	@Test
	public void updateEmp() {
		Employee employee = getEmployee();
		when(employeeRepository.findById(employee.getEmpId())).thenReturn(Optional.of(employee));
		Employee actualOutput= employeeServiceImpl.updateEmployee(employee);	
		assertNotNull(employee);
	}
	
	@Test
	public void deleteEmployeeById() {	
		String actual= employeeServiceImpl.deleteEmployee(1l);	        
        assertThat(actual).isNotNull();

	}
	@Test
	public void saveAllEmployee() {
		List<Employee> employee = getEmployeeList();
		when(employeeRepository.saveAll(employee)).thenReturn(employee);
		List<Employee> actualEmp = employeeServiceImpl.saveAll(employee);
		assertThat(actualEmp).isNotNull();
	}
	
	
	
	private List<Employee> getEmployeeList() {
		List<Employee> list=new ArrayList<>();
		Employee employee = new Employee();
		employee.getEmpId();
		employee.setEmpName("Priyanshi");
		employee.setCity("Indore");
		Employee employee1 = new Employee();
		employee1.getEmpId();
		employee1.setEmpName("Sonali");
		employee1.setCity("Indore");
		list.add(employee);
		list.add(employee1);		
		
	return list;
		
	}

	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmpId(1l);
		employee.setEmpName("Priyanshi");
		employee.setCity("Indore");
		
	return employee;
	}
	

}
