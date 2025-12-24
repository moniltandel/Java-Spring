package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public void addOrUpdateUser(User u) {
		// TODO Auto-generated method stub
		userRepo.save(u);
	}

	@Override
	public List<User> allUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User userById(int id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).orElseThrow();
	}

	@Override
	public User userByEmailAndPassword(String email, String pass) {
		// TODO Auto-generated method stub
		return userRepo.findByEmailAndPassword(email, pass);
	}

	@Override
	public User loginUser(User u) {
		// TODO Auto-generated method stub
		return userRepo.loginuser(u.getEmail(),u.getPassword(),u.getRole());
	}

}
