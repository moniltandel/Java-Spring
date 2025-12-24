package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Product;

public interface ProductService {
	public void addOrUpdateProduct(Product p);
	
	public List<Product> viewAllProducts();
	
	public Product findById(int id);
	
	public List<Product> productsByCategory(int catid);
	
}
