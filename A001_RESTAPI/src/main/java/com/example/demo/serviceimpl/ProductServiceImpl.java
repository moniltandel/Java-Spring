package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepo productRepo;
	
	@Override
	public Product addProduct(Product p) {
		// TODO Auto-generated method stub
		return productRepo.save(p);
	}

	@Override
	public List<Product> allProducts() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public Product productById(int id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).orElseThrow();
	}

	@Override
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		productRepo.deleteById(id);
	}

	@Override
	public Product updateProduct(int id, Product p) {
		// TODO Auto-generated method stub
		Product existed = productRepo.findById(id).orElseThrow();
		existed.setName(p.getName());
		existed.setPrice(p.getPrice());
		existed.setQty(p.getQty());
		existed.setCategory(p.getCategory());
		
		return productRepo.save(existed);
	}
	
}
