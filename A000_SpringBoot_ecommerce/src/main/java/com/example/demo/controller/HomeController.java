package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.CartService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@Controller
public class HomeController {

    

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;
	
	@Autowired
	CartService cartService;

	@Autowired
	RoleService roleService;
	

	@GetMapping("/")
	public String index(Model model, @RequestParam(value = "catid", required = false, defaultValue = "0") int cid) {

		if (cid == 0) {
			model.addAttribute("products", productService.viewAllProducts());
		} else {
			model.addAttribute("products", productService.productsByCategory(cid));
		}

		model.addAttribute("categories", categoryService.allCategories());
		return "index";
	}

	@GetMapping("accounts")
	public String accounts() {
		return "accounts";
	}

// ***************** cart start *****************
	@GetMapping("cart")
	public String cart(Model model) {
		model.addAttribute("carts", cartService.viewAllCart());
		return "cart";
	}

	@GetMapping("addtocart")
	public void addtocart(@RequestParam("id") int id,HttpServletResponse res,HttpServletRequest req) throws IOException {
		PrintWriter pw = res.getWriter();
		
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("userdata");
		
		Product p = productService.findById(id);
		User u1 = userService.userById(u.getId());
		Cart c = new Cart();
		c.setProduct(p);
		c.setUser(u1);
		c.setQty(1);
		
		cartService.addOrUpdateCart(c);
		pw.append("product added to cart");
		
	}
	
	@GetMapping("changeqty")
	public void changeqty(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter pw = res.getWriter();
		int id = Integer.parseInt(req.getParameter("cid"));
		int qty = Integer.parseInt(req.getParameter("qty"));
		pw.append(id + " " );
		
	}
	
	@GetMapping("payment")
	public void makepayment(@RequestParam("total") int total, HttpServletResponse resp) throws RazorpayException, IOException {
		PrintWriter pw = resp.getWriter();
		
		RazorpayClient razorpay = new RazorpayClient("rzp_test_RJMGYVu70H6omy", "li0GJDJYzOXvFtZdJaF8dLaX");

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount",total*100);
		orderRequest.put("currency","INR");
		orderRequest.put("receipt", "receipt#1");
		JSONObject notes = new JSONObject();
		notes.put("notes_key_1","Tea, Earl Grey, Hot");
		notes.put("notes_key_1","Tea, Earl Grey, Hot");
		orderRequest.put("notes",notes);

		Order order = razorpay.orders.create(orderRequest);
		pw.append(order.toString());
	}

// ***************** cart end  *****************

	@GetMapping("checkout")
	public String checkout() {
		return "checkout";
	}

	@GetMapping("compare")
	public String compare() {
		return "compare";
	}

	@GetMapping("details")
	public String details(@RequestParam("id") int id, Model model) {
		model.addAttribute("product", productService.findById(id));
		return "details";
	}

	@GetMapping("shop")
	public String shop() {
		return "shop";
	}

	@GetMapping("wishlist")
	public String wishlist() {
		return "wishlist";
	}

//  ****************** User begin **********************

	@GetMapping("loginregister")
	public String loginregister(Model model) {

		model.addAttribute("user", new User());
		return "login-register";
	}

	@PostMapping("/adduser")
	public String adduser(@ModelAttribute("user") User u, Model model) {
		u.setRole(roleService.roleById(2));
		userService.addOrUpdateUser(u);
		model.addAttribute("msg", "Registration Success!!!");
		model.addAttribute("user", new User());
		return "login-register";
	}

	@PostMapping("/userlogin")
	public String userlogin(@RequestParam("email") String email, 
			@RequestParam("password") String password,HttpServletRequest req,
			Model model,User u) {
		u.setRole(roleService.roleById(2));
		u = userService.loginUser(u);
		
		
		if(u != null) {
			HttpSession session = req.getSession();
			session.setAttribute("userdata", u);
			
			return "redirect:";
		}else {
			
			return "login-register";
		}

	}
	
	@GetMapping("logout")
	public String logout(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
	
	

//  ****************** User end **************************	
}
