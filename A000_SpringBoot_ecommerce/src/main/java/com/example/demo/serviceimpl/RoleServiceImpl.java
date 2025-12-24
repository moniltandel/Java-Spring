package com.example.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.repo.RoleRepo;
import com.example.demo.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepo roleRepo;
	
	@Override
	public Role roleById(int id) {
		// TODO Auto-generated method stub
		return roleRepo.findById(id).orElseThrow();
	}

}
