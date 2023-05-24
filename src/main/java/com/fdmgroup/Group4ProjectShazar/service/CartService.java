package com.fdmgroup.Group4ProjectShazar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Group4ProjectShazar.model.Cart;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.repository.CartRepository;

@Service
public class CartService {

	private CartRepository cartRepository;
	
	@Autowired
	public CartService(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}
	
	public void saveCart(Cart cart) {
		cartRepository.save(cart);
	}

	public Cart findCartByUser(User user) {
		Cart cart = cartRepository.findByUser(user);
		return cart;
	}
	


}
