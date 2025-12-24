package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Studentdto;
import com.example.demo.service.StudentService;

@RestController

public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Value("${project.image}")
	String path;
	
	
	@PostMapping("/student")
	public ResponseEntity<Studentdto> addStudent(@RequestBody Studentdto dto) {
		Studentdto st = studentService.addStudent(dto);
		
		return new ResponseEntity<>(st, HttpStatus.OK);
	}
	
}
