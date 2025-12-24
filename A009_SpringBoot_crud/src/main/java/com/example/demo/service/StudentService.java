package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Student;

public interface StudentService {
	
	public void addOrUpdateStudent(Student s);
	public List<Student> allStudents();
	public Student getStudentById(int id);
	public void deleteStudent(int id);
	
}
