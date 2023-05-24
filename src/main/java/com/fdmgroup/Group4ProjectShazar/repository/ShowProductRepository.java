package com.fdmgroup.Group4ProjectShazar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;

@Repository
public interface ShowProductRepository extends JpaRepository<Product, Integer>{

	
	List<Product> findByOwner(User user);
	
	@Query("SELECT p FROM Product p ORDER BY p.rating DESC")
	List<Product> findTopProducts();
	
}
