package com.fdmgroup.Group4ProjectShazar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.Group4ProjectShazar.model.Cart;
import com.fdmgroup.Group4ProjectShazar.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository <CartItem, Integer> {
	List<CartItem> findByCart(Cart cart);
}
