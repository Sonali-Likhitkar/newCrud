package com.example.demo.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
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
//		Employee employee =getEmployee();
//		employeeRepository.save(employee);
		 Employee  Result = employeeRepository.findByEmpId(146l);
		assertEquals(146l, Result.getEmpId());
	}
	
	@Test 
	public void findAllEmp() {
		 List<Employee> empList = (List<Employee>) employeeRepository.findAll();

	        Assertions.assertThat(empList.size()).isGreaterThan(0);

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
	      employeeRepository.deleteById(146l);
	      Optional<Employee> optionalemployee = Optional.ofNullable(employeeRepository.findByEmpId(146l));
	        Assertions.assertThat(optionalemployee).isEmpty();
	    
	}
	
	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmpName("Priyanshi");
		employee.setCity("Indore");
		
	return employee;
	}
	
	
}

