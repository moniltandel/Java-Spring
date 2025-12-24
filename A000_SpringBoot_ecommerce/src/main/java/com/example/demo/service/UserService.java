package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Role;
import com.example.demo.model.User;

public interface UserService {
	public void addOrUpdateUser(User u);
	public List<User> allUser();
	public User userById(int id);
	
	public User userByEmailAndPassword(String email, String pass);
	
	public User loginUser(User u);
}
