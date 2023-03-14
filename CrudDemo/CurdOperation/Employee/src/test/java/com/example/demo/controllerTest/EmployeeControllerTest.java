package com.example.demo.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.demo.controller.EmployeeController;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;


public class EmployeeControllerTest {

	@Mock
	EmployeeService employeeService;

	@InjectMocks
	EmployeeController employeeController;
	
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	
	
	Employee employee;
	List<Employee> list;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		employee = getEmployee();
		list=getEmployeeList();
	
	}
	
	
	
	@Test
	public void saveEmpTest() throws Exception {
		String jsonRequest = objectMapper.writeValueAsString(employee);
		when(employeeService.saveEmployee(Mockito.any(Employee.class))).thenReturn(employee);
		MvcResult mvcResult = mockMvc
				.perform(post("/saveEmployees").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		Employee expectedOutputUser = objectMapper.readValue(expectedOutput, Employee.class);
		assertEquals(expectedOutputUser.getEmpId(), employee.getEmpId());
	}
	
	
	@Test
	public void updateEmpTest() throws Exception {
		String jsonRequest = objectMapper.writeValueAsString(employee);
		when(employeeService.updateEmployee(Mockito.any(Employee.class))).thenReturn(employee);
		MvcResult mvcResult = mockMvc
				.perform(put("/updateEmployees/{employeeId}",11).content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		Employee expectedOutputUser = objectMapper.readValue(expectedOutput, Employee.class);
		assertEquals(expectedOutputUser.getEmpId(), employee.getEmpId());
	}
	
	
	@Test
	public void deleteEmpTest() throws Exception {
		String jsonRequest = String.valueOf(employee.getEmpId());
		when(employeeService.deleteEmployee(Mockito.anyLong())).thenReturn(String.valueOf("Deleted Successfully"));
		MvcResult mvcResult = mockMvc
			.perform(delete("/deleteEmployees/{employeeId}",1).content(jsonRequest))
				.andExpect(status().isOk()).andReturn();
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		String actualOutput="Deleted Successfully";
		assertEquals(expectedOutput,actualOutput);
	}
	
	
	@Test
	public void getEmpByIdTest() throws Exception {
		String jsonRequest = String.valueOf(employee.getEmpId());
		when(employeeService.getEmployee(Mockito.any())).thenReturn(employee);
		MvcResult mvcResult = mockMvc
			.perform(get("/employees/{employeeId}",11).content(jsonRequest))
				.andExpect(status().isOk()).andReturn();
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		Employee expectedOutputUser = objectMapper.readValue(expectedOutput, Employee.class);
		assertEquals(expectedOutputUser.getEmpId(), employee.getEmpId());
	}
	
	
	@Test
	public void getAllEmpTest() throws Exception {
	List<Employee> getListOfEmployees = new ArrayList<>();
		when(employeeService.getListOfEmployees()).thenReturn(getListOfEmployees);
		MvcResult mvcResult = mockMvc
				.perform(get("/getListOfEmployees"))
				.andExpect(status().isOk()).andReturn();
		int status=mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void saveAllEmpTest()throws Exception{
		String jsonRequest = objectMapper.writeValueAsString(getEmployeeList());
		when(employeeService.saveMultipleRecords(getEmployeeList())).thenReturn(getEmployeeList());	
		MvcResult mvcResult = mockMvc
				.perform(post("/multipleStudentSave").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int status=mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
	}
	
	public Employee getEmployee()
	{
		Employee emp=new Employee();
		emp.setEmpId(55l);
		emp.setEmpName("hhh");
		emp.setCity("jjj");
		
		return emp;
	}
	public List<Employee> getEmployeeList()
	{
		List<Employee> empList = new ArrayList<>();
		
		Employee emp1=new Employee();
		emp1.setEmpId(11l);
		emp1.setEmpName("cccc");
		emp1.setCity("ddd");
		
		
		Employee emp2=new Employee();
		emp2.setEmpId(12l);
		emp2.setEmpName("fff");
		emp2.setCity("eeee");
		
		
		empList.add(emp1);
		empList.add(emp2);
		return empList;
		
	}
}
