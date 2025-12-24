package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Cart;

public interface CartService {
	public void addOrUpdateCart(Cart c);
	
	public List<Cart> viewAllCart();
}
