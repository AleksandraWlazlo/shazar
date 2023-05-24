package com.fdmgroup.Group4ProjectShazar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.repository.CategoriesRepository;

@Service
public class CategoriesService {

	private CategoriesRepository categoriesRepository;
	
	@Autowired
	public CategoriesService(CategoriesRepository categoriesRepository) {
		
		this.categoriesRepository = categoriesRepository;
	}
	
	public List<Product> listProductsOfCategory(String category) {
			
		return categoriesRepository.findByCategoryIgnoreCase(category);
	}
	
}
