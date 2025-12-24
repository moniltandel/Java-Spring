package com.example.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	int id;
	String name;
	String email;
	String password;
	String phone;
   	RoleDto role;
   
}
