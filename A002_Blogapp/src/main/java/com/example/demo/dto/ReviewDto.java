package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
	int id;
	String title;
	BlogDto blog;
	UserDto user;
}
