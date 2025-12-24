package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController {
	@Autowired
	StudentService service;

	@GetMapping("/students")
	public ResponseEntity<List<Student>> viewAllStudents(@RequestParam(value = "pagenumber", defaultValue = "1")int pagenumber,
		@RequestParam(value = "pagesize", defaultValue = "1") int pagesize,@RequestParam(value = "sortby", defaultValue = "id") String sortby,
		@RequestParam(value = "sorttype", defaultValue = "asc") String sorttype) {
		List<Student> allstudents = service.allStudents(pagenumber,pagesize,sortby,sorttype);
		return new ResponseEntity<List<Student>>(allstudents, HttpStatus.OK);
	}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> studentById(@PathVariable("id") int id) {
		Student st = service.studentById(id);
		return new ResponseEntity<>(st, HttpStatus.OK);
	}

	@PostMapping("/students")
	public ResponseEntity<Student> addStudent(@Valid @RequestBody Student st) {

		Student s = service.addStudent(st);

		return new ResponseEntity<Student>(s, HttpStatus.CREATED);
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student st) {

		Student s = service.updateStudent(st, id);
		return new ResponseEntity<Student>(s, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("id") int id) {
		service.deleteStudent(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
