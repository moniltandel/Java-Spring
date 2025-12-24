package com.example.demo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RoleDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	RoleService roleService;
	
	@Override
	public UserDto addUser(UserDto udto,int roleid) {
		// TODO Auto-generated method stub
		udto.setRole(mapper.map(roleService.roleById(roleid), RoleDto.class));
		
		return mapper.map(userRepo.save(mapper.map(udto, User.class)),UserDto.class);
	}

	@Override
	public List<UserDto> allUsers() {
		// TODO Auto-generated method stub
		
		List<User> users = userRepo.findAll();
		return users.stream().map(user->mapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto userById(int id) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFound("User", "Id", id));
		return mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto u, int userid, int roleid) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userid).orElseThrow(()-> new ResourceNotFound("User", "Id", userid));
		
		user.setName(u.getName());
		user.setEmail(u.getEmail());
		user.setPassword(u.getPassword());
		user.setPhone(u.getPhone());
		user.setRole(mapper.map(roleService.roleById(roleid), Role.class));
		
		User updatedUser = userRepo.save(user);
		
		return mapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
		
	}
	
	
	
}
