package com.example.demo.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.EmployeeApplication;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Transactional
@SpringBootTest(classes = EmployeeApplication.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeRepoTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	 public void testSave() {
	      Employee employee = getEmployee();
	      employeeRepository.save(employee);
	      Employee found = employeeRepository.findById(employee.getEmpId()).get();
	      assertEquals(employee.getEmpId(), found.getEmpId());	     
	     
	   }
	@Test
	public void findById() {
		Employee employee =getEmployee();
		employeeRepository.save(employee);
		Employee Result = employeeRepository.findById(employee.getEmpId()).get();
		assertEquals(employee.getEmpId(), Result.getEmpId());
	}
	
	@Test 
	public void findAllEmp() {
		List<Employee> employee = getEmployeeList();
		//employeeRepository.save(employee);
		List<Employee> result = new ArrayList<>();
	      employeeRepository.findAll().forEach(e -> result.add(e));
	      assertEquals(result.size(), employee.size());	
	}
	
	@Test
	public void updateEmp() {
		Employee employee = getEmployee();
		Employee Result = employeeRepository.save(employee);
		employeeRepository.findById(employee.getEmpId()).get();
		assertEquals(employee.getEmpId(), Result.getEmpId());
		
	}
	
	@Test
	   public void testDeleteById() {
	      Employee employee = getEmployee();
	      employeeRepository.save(employee);
	      employeeRepository.deleteById(employee.getEmpId());
	      List<Employee> result = new ArrayList<>();
	      employeeRepository.findAll().forEach(e -> result.add(e));
	      assertEquals(result.size(), 2);
	      
	}
	
	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmpName("Priyanshi");
		employee.setCity("Indore");
		
	return employee;
	}
	
	private List<Employee> getEmployeeList() {
		List<Employee> list=new ArrayList<>();
		Employee employee = new Employee();
		employee.setEmpName("Priyanshi");
		employee.setCity("Indore");
		Employee employee1 = new Employee();
		employee1.setEmpName("Sonali");
		employee1.setCity("Indore");
		list.add(employee);
		list.add(employee1);		
		
	return list;
	}
	
}

