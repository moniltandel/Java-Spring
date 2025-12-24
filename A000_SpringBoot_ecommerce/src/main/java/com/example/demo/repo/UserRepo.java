package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Role;
import com.example.demo.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	public User findByEmailAndPassword(String email, String pass);
	
	@Query("select u from User u where u.email=:email and u.password=:password and u.role=:role")
	public User loginuser(@Param("email") String email,
						@Param("password") String password,
						@Param("role")Role role) ;
}
