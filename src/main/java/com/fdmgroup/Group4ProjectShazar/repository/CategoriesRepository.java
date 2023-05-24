package com.fdmgroup.Group4ProjectShazar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.Group4ProjectShazar.model.Product;

@Repository
public interface CategoriesRepository extends JpaRepository<Product, Integer>{

	List<Product> findByCategoryIgnoreCase(String category);	
}
