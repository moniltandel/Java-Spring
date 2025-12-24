package com.example.demo.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {

    
	
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> viewAllProducts() {
		List<Product> products = productService.allProducts();
		
		List<ProductDto> pdtos = new ArrayList<>();
		
		for(Product p : products) {
			ProductDto dto = new ProductDto();
			dto.setId(p.getId());
			dto.setName(p.getName());
			dto.setPrice(p.getPrice());
			dto.setQty(p.getQty());
			
			CategoryDto cdto = new CategoryDto();
			cdto.setId(p.getCategory().getId());
			cdto.setName(p.getCategory().getName());
			
			dto.setCategoryDto(cdto);
			pdtos.add(dto);
		}
		
		return new ResponseEntity<>(pdtos, HttpStatus.OK);
	}
	
	@PostMapping("/products/category/{catid}")
	public ResponseEntity<ProductDto> addProduct(@RequestBody Product p, @PathVariable("catid") int cid) {
		Category c = categoryService.categoryById(cid);
		p.setCategory(c);
		
		Product product = productService.addProduct(p);
		
		CategoryDto cdto = new CategoryDto();
		cdto.setId(product.getCategory().getId());
		cdto.setName(product.getCategory().getName());
		
		ProductDto dto = new ProductDto();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		dto.setQty(product.getQty());
		dto.setCategoryDto(cdto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@PutMapping("/products/category/{catid}/{pid}")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody Product p, @PathVariable("catid") int cid,
					@PathVariable("pid") int pid) {
		Category c = categoryService.categoryById(cid);
		p.setCategory(c);
		
		Product product = productService.updateProduct(pid,p);
		
		CategoryDto cdto = new CategoryDto();
		cdto.setId(product.getCategory().getId());
		cdto.setName(product.getCategory().getName());
		
		ProductDto pdto = new ProductDto();
		pdto.setId(product.getId());
		pdto.setName(product.getName());
		pdto.setPrice(product.getPrice());
		pdto.setQty(product.getQty());
		pdto.setCategoryDto(cdto);
		
		return new ResponseEntity<>(pdto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("products/category/{pid}")
	public String deleteProduct(@PathVariable("pid") int pid) {
		productService.deleteProduct(pid);
		return "product deleted";
	}
}
