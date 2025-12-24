package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;
	@Override
	public void addorUpdateCategory(Category c) {
		categoryRepo.save(c);
		
	}
	@Override
	public List<Category> allCategories() {
		
		return categoryRepo.findAll();
	}
	@Override
	public Category findById(int id) {
		// TODO Auto-generated method stub
		return categoryRepo.getById(id);
	}
	@Override
	public void delete(int id) {
		categoryRepo.deleteById(id);
	}

}
