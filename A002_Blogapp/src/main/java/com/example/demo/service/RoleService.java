package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.RoleDto;
import com.example.demo.model.Role;

public interface RoleService {
	public List<RoleDto> allRoles();
	public RoleDto addRole(RoleDto roledto);
	public RoleDto roleById(int id);
	public RoleDto updateRole(int id,RoleDto roledto);
	public void deleteRole(int id);
}
