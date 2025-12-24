package com.example.demo.controller;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	
	@PostMapping("/role/{id}")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userdto,@PathVariable("id") int roleid) {
		userdto.setPassword(encoder.encode(userdto.getPassword()));
		UserDto createdUser = userService.addUser(userdto,roleid);
		
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> allUsers() {
		List<UserDto> users = userService.allUsers();
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> userById(@PathVariable("id") int id) {
		UserDto userDto = userService.userById(id);
		
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@PutMapping("/{userid}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userdto,@PathVariable("userid") int userid,
						@RequestParam("roleid") int roleid) {
		UserDto updatedUser = userService.updateUser(userdto, userid, roleid);
		return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id")int id) {
		userService.deleteUser(id);
		return new ResponseEntity<>("deleted", HttpStatus.OK);
	}
	
}
