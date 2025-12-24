package com.example.demo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RoleDto;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.model.Role;
import com.example.demo.repo.RoleRepo;
import com.example.demo.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<RoleDto> allRoles() {
		List<Role> roles = roleRepo.findAll();
		return roles.stream().map(role->mapper.map(role, RoleDto.class)).collect(Collectors.toList());
	}

	@Override
	public RoleDto addRole(RoleDto roledto) {
		Role role =  roleRepo.save(mapper.map(roledto, Role.class));
		return mapper.map(role, RoleDto.class);
	}

	@Override
	public RoleDto roleById(int id) {
		// TODO Auto-generated method stub
		
		Role role = roleRepo.findById(id).orElseThrow(()-> new ResourceNotFound("Role", "Id", id));
		
		return mapper.map(role, RoleDto.class);
	}

	@Override
	public RoleDto updateRole(int id, RoleDto roledto) {
		// TODO Auto-generated method stub
		Role role = roleRepo.findById(id).orElseThrow(()-> new ResourceNotFound("Role", "Id", id));
		
		role.setRole(roledto.getRole());
		Role updatedrole = roleRepo.save(role);
		return mapper.map(updatedrole, RoleDto.class);
	}

	@Override
	public void deleteRole(int id) {
		// TODO Auto-generated method stub
		
		roleRepo.deleteById(id);
		
	}
	
}
