package com.fdmgroup.Group4ProjectShazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.Group4ProjectShazar.model.Cart;
import com.fdmgroup.Group4ProjectShazar.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	Cart findByUser(User user);
	
}
