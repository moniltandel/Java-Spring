package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Employeedto;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping("/employee")
	public ResponseEntity<List<Employeedto>> viewAllEmployees() {
		List<Employee> emps = employeeService.allEmployee();
		
		List<Employeedto> empdtos = emps.stream().map(ele->mapper.map(ele, Employeedto.class)).collect(Collectors.toList());
		
		
//		for(Employee e : emps) {
//			
//			Employeedto dto = mapper.map(e, Employeedto.class);
//			empdtos.add(dto);
//		}
		
		return new ResponseEntity<>(empdtos, HttpStatus.OK);
	}
	
	@PostMapping("/employee")
	public ResponseEntity<Employeedto> addEmployee(@RequestBody Employee e) {
		
		Employee employee = employeeService.addEmployee(e);
		
		Employeedto dto = mapper.map(employee, Employeedto.class);
		
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employeedto> updateEmployee(@RequestBody Employee e,@PathVariable("id") int id) {
		Employee emp = employeeService.updateEmployee(e, id);
		
		Employeedto dto = mapper.map(emp, Employeedto.class);
		
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id){
		 employeeService.deleteEmployee(id);
		
		
		
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employeedto> findEmployeeById(@PathVariable("id") int id) {
		Employee e = employeeService.employeeById(id);
		
		Employeedto dto = mapper.map(e, Employeedto.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
