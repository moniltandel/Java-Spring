package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Category;

public interface CategoryService {
	public Category addCategory(Category c);
	
	public List<Category> viewAllCategory();
	
	public Category categoryById(int id);
	
	public Category updateCategory(Category c, int id);
	
	public void deleteCategory(int id);
}
