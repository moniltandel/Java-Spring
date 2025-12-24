package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.ProductRepo;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public void addOrUpdateProduct(Product p) {
		// TODO Auto-generated method stub
		productRepo.save(p);
	}

	@Override
	public List<Product> viewAllProducts() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).orElseThrow();
	}

	@Override
	public List<Product> productsByCategory(int catid) {
		// TODO Auto-generated method stub
		return productRepo.findByCategory(categoryRepo.findById(catid).orElseThrow());
	}
	
	

}
