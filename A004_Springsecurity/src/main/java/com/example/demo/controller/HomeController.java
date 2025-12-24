package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String index() {
		return "index request";
	}
	
	@GetMapping("/public")
	public String Public() {
		return "public request";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user request";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin request";
	}
	
	@GetMapping("/demo")
	public String demo() {
		return "demo request";
	}
}
