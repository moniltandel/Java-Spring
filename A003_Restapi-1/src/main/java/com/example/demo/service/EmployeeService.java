package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
	public Employee addEmployee(Employee e);
	public List<Employee> allEmployee();
	public Employee employeeById(int id);
	public Employee updateEmployee(Employee e, int id);
	public void deleteEmployee(int id);
}
