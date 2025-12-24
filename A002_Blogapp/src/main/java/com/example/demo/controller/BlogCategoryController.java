package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BlogCategoryDto;
import com.example.demo.service.BlogCategoryService;

@RestController
@RequestMapping("/blogcategories")
public class BlogCategoryController {
	
	@Autowired
	BlogCategoryService blogCategoryService;
	
	@PostMapping("/")
	public ResponseEntity<BlogCategoryDto> addBlogCategory(@RequestBody BlogCategoryDto blogcatdto) {
		BlogCategoryDto categoryDto = blogCategoryService.addBlogCategory(blogcatdto);
		return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<BlogCategoryDto>> viewAllCategories() {
		List<BlogCategoryDto> categoryDtos = blogCategoryService.AllBlogCategories();
		return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BlogCategoryDto> BlogById(@PathVariable("id") int id) {
		BlogCategoryDto categoryDto = blogCategoryService.BlogCategoryById(id);
		return new ResponseEntity<>(categoryDto, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BlogCategoryDto> updateBlogCategory(@RequestBody BlogCategoryDto blogCategoryDto,@PathVariable("id") int id) {
		BlogCategoryDto updaCategoryDto = blogCategoryService.updateBlogCategory(blogCategoryDto, id);
		
		return new ResponseEntity<>(updaCategoryDto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBlogCategory(@PathVariable("id") int id) {
		blogCategoryService.deleteBlogCategory(id);
		
		return new ResponseEntity<>("deleted", HttpStatus.OK);
	}
	
}
