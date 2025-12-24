package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Student;

public interface StudentService {
	
	public Student addStudent(Student st);
	public List<Student> allStudents(int pagenumber,int pagesize,String sortby,String sorttype);
	public Student studentById(int id);
	public void deleteStudent(int id);
	public Student updateStudent(Student st,int id);
	
}
