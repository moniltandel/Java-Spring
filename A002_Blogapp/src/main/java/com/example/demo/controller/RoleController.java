package com.example.demo.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RoleDto;
import com.example.demo.model.Role;
import com.example.demo.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping("/")
	public ResponseEntity<List<RoleDto>> viewAllRoles() {
		
		
//		List<RoleDto> dtos = new ArrayList<>();
//		for(Role role : ls) {
//			RoleDto dto = new RoleDto();
//			dto.setId(role.getId());
//			dto.setRole(role.getRole());
//			
//			dtos.add(dto);
//		}
		
		List<RoleDto> roles = roleService.allRoles();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RoleDto> RoleById(@PathVariable("id") int id) {
		RoleDto roledto = roleService.roleById(id);
		
//		RoleDto dto = new RoleDto();
//		dto.setId(role.getId());
//		dto.setRole(role.getRole());
		
		return new ResponseEntity<>(roledto, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roledto) {
		RoleDto createdRole = roleService.addRole(roledto);
		
//		RoleDto dto = new RoleDto();
//		dto.setId(createdRole.getId());
//		dto.setRole(createdRole.getRole());
		
//		RoleDto dto = mapper.map(createdRole, RoleDto.class);
		
		return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}") 
	public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto roledto,@PathVariable("id") int id) {
		RoleDto updatedRole = roleService.updateRole(id, roledto);
		
		return new ResponseEntity<>(updatedRole, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRole(@PathVariable("id") int id) {
		roleService.deleteRole(id);
		return new ResponseEntity<>("deleted", HttpStatus.OK);
	}
	
	
}
