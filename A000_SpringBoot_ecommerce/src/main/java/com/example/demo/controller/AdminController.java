package com.example.demo.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repo.CartRepo;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
	
	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
	
	@GetMapping("/dashboard1")
	public String dashboard1() {
		return "dashboard1";
	}
/*******************   Category Starts *************************/	
	@GetMapping("/category")
	public String category(Model model) {
		model.addAttribute("categories", categoryService.allCategories());
		return "category";
	}
	
	@GetMapping("/category1")
	public String category1() {
		return "category1";
	}
	
	@PostMapping("/addcategory")
	public String addcategory(@RequestParam("file") MultipartFile file,@RequestParam("catname")String catname,HttpServletRequest req) {
		
		String fileName = System.currentTimeMillis()+ "_" + file.getOriginalFilename();
		
		
		String path = req.getServletContext().getRealPath("/");
		String mypath = path + File.separator + "category_img";
		File createfile = new File(mypath);
		if(!createfile.exists()) {
			createfile.mkdir();
		}
		
		File savedFile = new File(createfile, fileName);
		
		try {
			file.transferTo(savedFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Category c = new Category();
		c.setName(catname);
		c.setImage(fileName);
		categoryService.addorUpdateCategory(c);
		return "redirect:/category";
	}
	
/************************* Category ends **********************/
	
	@GetMapping("/products")
	public String products(Model model) {
		model.addAttribute("categories", categoryService.allCategories());
		model.addAttribute("products", productService.viewAllProducts());
		
		return "product";
	}
	
//	@PostMapping("/addproduct")
//	public String addproduct(@RequestParam("file") MultipartFile file, 
//			@RequestParam("name") String name, @RequestParam("price")String price,
//			@RequestParam("description") String description,@RequestParam("stock") double stock) {
//		
//		return "";
//	}
	
	@PostMapping("/addproduct")
	public String addproduct(@RequestParam("file") MultipartFile file,HttpServletRequest req) {
		
		String fileName = System.currentTimeMillis()+ "_" + file.getOriginalFilename();
		
		
		String path = req.getServletContext().getRealPath("/");
		String mypath = path + File.separator + "product_img";
		File createfile = new File(mypath);
		if(!createfile.exists()) {
			createfile.mkdir();
		}
		
		File savedFile = new File(createfile, fileName);
		
		try {
			file.transferTo(savedFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Product p = new Product();
		p.setCategory(categoryService.findById(Integer.parseInt(req.getParameter("category"))));
		p.setName(req.getParameter("name"));
		p.setPrice(Double.parseDouble(req.getParameter("price")));
		p.setDescription(req.getParameter("description"));
		p.setStock(Integer.parseInt(req.getParameter("stock")));
		p.setImage(fileName);
		
		productService.addOrUpdateProduct(p);
		
		
		return "redirect:/products";
	}
}

