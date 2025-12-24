package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Category;
import com.example.demo.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	public List<Product> findByCategory(Category c);
}
