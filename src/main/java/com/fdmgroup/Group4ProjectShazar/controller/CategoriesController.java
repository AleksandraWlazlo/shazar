package com.fdmgroup.Group4ProjectShazar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fdmgroup.Group4ProjectShazar.model.Product;

import com.fdmgroup.Group4ProjectShazar.service.CategoriesService;

@Controller
public class CategoriesController {

	
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private MainController mainController;
	
	
	@GetMapping("/goToCategory/{category}")
	public String goToCategory(ModelMap model, @PathVariable String category) {
		mainController.populateLoggedUserModel(model);
		
		category = category.replace("_", " ");
		
		List<Product> listOfProducts = categoriesService.listProductsOfCategory(category); 
		
		model.addAttribute("products", listOfProducts);
		model.addAttribute("category", category.toUpperCase());
		
		return "showProductsOfCategory";
	}
	
	
	
}
