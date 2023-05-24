package com.fdmgroup.Group4ProjectShazar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Group4ProjectShazar.model.Cart;
import com.fdmgroup.Group4ProjectShazar.model.CartItem;
import com.fdmgroup.Group4ProjectShazar.repository.CartItemRepository;

@Service
public class CartItemService {

	private CartItemRepository cartItemRepository;
	
	@Autowired
	public CartItemService(CartItemRepository cartItemRepository) {
		super();
		this.cartItemRepository = cartItemRepository;
	}
	
	public CartItem findCartItemById(Integer cartItemId) {
		return cartItemRepository.getById(cartItemId);
	}
	
	public void saveCartItem(CartItem cartItem) {
		cartItemRepository.save(cartItem);
	}
	
	public List<CartItem> findCartItems(Cart cart){
		List<CartItem> cartItems = cartItemRepository.findByCart(cart);
		return cartItems;
	}
	
	public void deleteCartItem(Integer cartItemId) {
		cartItemRepository.deleteById(cartItemId);
	}
	
	
	
}
