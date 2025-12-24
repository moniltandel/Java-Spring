package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Override
	public Employee addEmployee(Employee e) {
		// TODO Auto-generated method stub
		return employeeRepo.save(e);
	}

	@Override
	public List<Employee> allEmployee() {
		// TODO Auto-generated method stub
		return employeeRepo.findAll();
	}

	@Override
	public Employee employeeById(int id) {
		// TODO Auto-generated method stub
		return employeeRepo.findById(id).orElseThrow();
	}

	@Override
	public Employee updateEmployee(Employee e, int id) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepo.findById(id).orElseThrow();
		
		emp.setName(e.getName());
		emp.setEmail(e.getEmail());
		emp.setAge(e.getAge());
		emp.setPhone(e.getPhone());
		
		employeeRepo.save(emp);
		
		return emp;
	}

	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		employeeRepo.deleteById(id);
		
	}
	
	
	
}
