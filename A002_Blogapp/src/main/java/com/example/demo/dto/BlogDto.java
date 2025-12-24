package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogDto {

	 int id;
	
	
	 String title;
	
	
	 String content;
	
	
	 String image;
	
	 BlogCategoryDto category;
	
	 UserDto user;
}
