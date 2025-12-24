package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;

public interface UserService {
	public UserDto addUser(UserDto u,int roleid);
	public List<UserDto> allUsers();
	public UserDto userById(int id);
	public UserDto updateUser(UserDto u, int userid, int roleid);
	public void deleteUser(int id);
}
