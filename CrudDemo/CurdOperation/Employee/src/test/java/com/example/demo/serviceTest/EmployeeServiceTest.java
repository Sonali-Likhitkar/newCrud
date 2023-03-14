package com.example.demo.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.serviceImpl.EmployeeServiceImpl;



@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class EmployeeServiceTest {
	

	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImpl  employeeServiceImpl;
	
	private Employee employee;
	
	
	@Test
	public void saveEmployee() {
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertThat(employeeServiceImpl.saveEmployee(employee)).isEqualTo(employee);
	}
	
	@Test
	public void findById() {
					
			Employee employee = getEmployee();
			lenient().when(employeeRepository.findByEmpId(Mockito.anyLong())).thenReturn(employee);
			Employee actualOutput =employeeServiceImpl.getEmployee(146l);
			assertEquals(actualOutput.toString(), employee.toString());

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
		when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
		Employee actualOutput= employeeServiceImpl.updateEmployee(employee);
		assertThat(actualOutput).isEqualTo(employee);
		//assertNotNull(employee);
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
		List<Employee> actualEmp = employeeServiceImpl.saveMultipleRecords(employee);
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
