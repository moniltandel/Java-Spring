package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.repo.CartRepo;
import com.example.demo.service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	CartRepo cartrepo;
	
	@Override
	public void addOrUpdateCart(Cart c) {
		// TODO Auto-generated method stub
		cartrepo.save(c);
	}

	@Override
	public List<Cart> viewAllCart() {
		// TODO Auto-generated method stub
		return cartrepo.findAll();
	}

}
