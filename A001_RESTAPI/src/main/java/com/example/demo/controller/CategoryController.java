package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryDto;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;

@RestController

public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDto>> viewAllCategory() {
		List<Category> ls = categoryService.viewAllCategory();
		
		List<CategoryDto> dtos = new ArrayList<>();
		
		for(Category c : ls) {
			CategoryDto dto = new CategoryDto();
			dto.setId(c.getId());
			dto.setName(c.getName());
			
			dtos.add(dto);
		}
		
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@DeleteMapping("/categories/{cid}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("cid") int cid) {
		categoryService.deleteCategory(cid);
		Category c = categoryService.categoryById(cid);
		
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
}
