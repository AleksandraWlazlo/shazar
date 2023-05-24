package com.fdmgroup.Group4ProjectShazar.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.repository.BookingRepository;
import com.fdmgroup.Group4ProjectShazar.repository.ProductReposititory;
import com.fdmgroup.Group4ProjectShazar.util.Filters;


@Service
public class ProductService {
	
	@Autowired
	private ProductReposititory repo;
	
	@Autowired 
	private BookingRepository bookingRepo;
	
	public List<Product> findALlProducts(){
		return repo.findAll();
	}
	
	public void createNewProduct(Product product) {
		repo.save(product);
	}
	
	public void save(Product product) {
		repo.save(product);
	}

	public Product findProductById(int productId) {
		
		Optional<Product> productOpt = repo.findById(productId);
		return productOpt.orElse(null);
	}
	
	public List<Product> findProductsByName(String name) {
	
	List<Product> foundProducts = repo.findByNameIgnoreCaseStartingWith(name);
	List<Product> foundProducts2 = repo.findByNameIgnoreCaseEndingWith(name);
	List<Product> foundProducts3 = repo.findByNameIgnoreCaseContaining(" " + name+ " ");
	foundProducts.addAll(foundProducts2);
	foundProducts.addAll(foundProducts3);
	List<Product>toReturn = foundProducts.stream().distinct().collect(Collectors.toList());

	return foundProducts.stream().distinct().collect(Collectors.toList());
	}
	
	public List<Product> findProductByPrice(List<Product> products, String minPrice, String maxPrice){
		
		List<Product>filteredByprice = null; 
		
		if(minPrice.isEmpty() && maxPrice.isEmpty()) return products;
			
		//there is min and max
		else if(!minPrice.isEmpty() && !maxPrice.isEmpty()) {
			
			filteredByprice= repo.findBypriceForDayBetween(Double.parseDouble(minPrice), Double.parseDouble(maxPrice));	
		}
		
		//there is only min
		else if(!minPrice.isEmpty() && maxPrice.isEmpty()) {
			
			filteredByprice = repo.findByPriceForDayGreaterThanEqual(Double.parseDouble(minPrice));	
		}
		
		//there is only max
		else if(minPrice.isEmpty() && !maxPrice.isEmpty()) {
			filteredByprice = repo.findByPriceForDayLessThanEqual(Double.parseDouble(maxPrice));		
		}
		return joinTwoLists(products, filteredByprice);
	}
	
	public List<Product> findProductByColor(List<Product> products, String color) {
		if (!color.equals("No filter")) {
			List<Product> findByColor = repo.findByColorIgnoreCase(color);
			List<Product> result =  joinTwoLists(products, findByColor);
			return result;
		}
		return products;
	}
	
	public List<Product> findProductByCategory(List<Product> products, String category) {
		if (!category.equals("No filter")) {
			List<Product> findByCategory = repo.findByCategoryIgnoreCase(category);
			List<Product> result =  joinTwoLists(products, findByCategory);
			return result;
		}
		return products;
	}
	
	public List<Product> findProductByType(List<Product> products, String type) {
		
		if (!type.equals("No filter")) {
			List<Product> findByType = repo.findByTypeIgnoreCase(type);
			List<Product> result =  joinTwoLists(products, findByType);
			return result;
		}
		return products;
	}
	
	public List<Product> filterResults(List<Product> products, Filters filters, String city) {
		
		List<Product>filteredProducts = products;
		filterByLocation(filteredProducts, city);
		filteredProducts = findProductByPrice(filteredProducts, filters.getMinPrice(),filters.getMaxPrice());
		filteredProducts = findProductByType(filteredProducts, filters.getType());
		filteredProducts = findProductByColor(filteredProducts, filters.getColor());
		filteredProducts = findProductByCategory(filteredProducts, filters.getCategory());
		
		return filteredProducts;
	}
	
	public List<Product> joinTwoLists(List<Product> l1, List<Product> l2){

		Set<Product> result = l1.stream()
				  .distinct()
				  .filter(l2::contains)
				  .collect(Collectors.toSet());
		return result.stream().collect(Collectors.toList());

	}
	
	public List<Product> subtractList(List<Product> l1, List<Product> l2){
		
		l1.removeAll(l2);
		return l1;
	}

	public List<Product> findProductsAlreadyBooked(String searchedPhrase, String startDate, String endDate) {
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate startDateLD = LocalDate.parse(startDate, dateTimeFormatter);
		LocalDate endDateLD = LocalDate.parse(endDate, dateTimeFormatter);
			
		return bookingRepo.searchForBookingForSearchFilter(searchedPhrase, startDateLD, endDateLD);
		
	}

	public void filterByLocation(List<Product> foundProducts, String city) {
		if (city != "") 
		 foundProducts.removeIf(product -> !product.getPickupAdress().getCity().equalsIgnoreCase(city));			
	}






}
