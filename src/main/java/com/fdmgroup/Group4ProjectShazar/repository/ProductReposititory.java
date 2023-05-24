package com.fdmgroup.Group4ProjectShazar.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.Group4ProjectShazar.model.Product;


@Repository
public interface ProductReposititory extends JpaRepository<Product, Integer> {
	
	Optional<Product> findByName(String name);
	List<Product> findByNameIgnoreCaseContaining(String name);
	List<Product> findByNameIgnoreCaseStartingWith(String name);
	List<Product> findByNameIgnoreCaseEndingWith(String name);
	List<Product> findBypriceForDayBetween(Double startPrice, Double endPrice);
	List<Product> findByColorIgnoreCase(String color);
	List<Product> findByCategoryIgnoreCase(String category);
	List<Product> findByTypeIgnoreCase(String type);
	List<Product> findByPriceForDayLessThanEqual(double parseDouble);
	List<Product> findByPriceForDayGreaterThanEqual(double parseDouble);
}

 