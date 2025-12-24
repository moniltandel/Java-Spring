package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.A009SpringBootCrudApplication;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@Controller
public class StudentController {

    private final A009SpringBootCrudApplication a009SpringBootCrudApplication;
	
	@Autowired
	StudentService service;

    StudentController(A009SpringBootCrudApplication a009SpringBootCrudApplication) {
        this.a009SpringBootCrudApplication = a009SpringBootCrudApplication;
    }
	
	@GetMapping("/")
	public String hello(Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("students", service.allStudents());
		return "index";
	}
	
	@PostMapping("/addStudent")
	public String addStudent(@ModelAttribute("student") Student s) {
		service.addOrUpdateStudent(s);
		return "redirect:/";
	}
	
	@GetMapping("/edit")
	public String editStudent(@RequestParam("eid") int eid,Model model) {
		System.out.println(eid);
		Student st = service.getStudentById(eid);
		model.addAttribute("student", st);
		model.addAttribute("students", service.allStudents());
		return "index";
	}
	
	@GetMapping("/delete")
	public String deletStudent(@RequestParam("did") int did) {
		service.deleteStudent(did);
		return "redirect:/";
	}
}
