package com.fdmgroup.Group4ProjectShazar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.repository.BookingRepository;
import com.fdmgroup.Group4ProjectShazar.repository.ProductReposititory;
import com.fdmgroup.Group4ProjectShazar.util.Filters;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

	@InjectMocks
	ProductService productService;
	
	@MockBean
	ProductReposititory mockRepo;
	
	@MockBean
	BookingRepository mockBookingRepository;

	@Mock
	Product product;

	@Mock
	List<Product> mockList;

	@Test
	public void test_findAllProducts() {
		productService.findALlProducts();
		verify(mockRepo, times(1)).findAll();
	}

	@Test
	public void test_createNewProduct_callsSaveMethodOfRepo() {
		productService.createNewProduct(product);
		verify(mockRepo, times(1)).save(product);
	}

	@Test
	public void test_findProductById_callsFindByIdMethodOfRepository() {

		productService.findProductById(1);
		verify(mockRepo).findById(1);
	}

	@Test
	public void test_findProductByPrice_calls_findBypriceForDayBetweenMethodFromRepositoryWhenCalledWithTwoNonEmptyParameters() {

		String minPrice = "10";
		String maxPrice = "20";

		productService.findProductByPrice(mockList, minPrice, maxPrice);
		verify(mockRepo, times(1)).findBypriceForDayBetween(Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
	}

	@Test
	public void test() {
		
		String minPrice = "";
		String maxPrice = "20";

		productService.findProductByPrice(mockList, minPrice, maxPrice);
		verify(mockRepo, times(1)).findByPriceForDayLessThanEqual (Double.parseDouble(maxPrice));
	}
	
	@Test
	public void test1() {
		
		String minPrice = "20";
		String maxPrice = "";

		productService.findProductByPrice(mockList, minPrice, maxPrice);
		verify(mockRepo, times(1)).findByPriceForDayGreaterThanEqual(Double.parseDouble(minPrice));
	}
	@Test
	public void test3() {
		
		String color = "red";

		productService.findProductByColor(mockList, color);
		verify(mockRepo, times(1)).findByColorIgnoreCase(color);
	}
	@Test
	public void test2() {
		
		String category = "category";

		productService.findProductByCategory(mockList, category);
		verify(mockRepo, times(1)).findByCategoryIgnoreCase(category);
	}
	
	@Test
	public void test4() {
		String type = "type";

		productService.findProductByType(mockList, type);
		verify(mockRepo, times(1)).findByTypeIgnoreCase(type);
	}
	
	@Test
	public void test_findProductsAlreadyBooked_callsSearchForBookingForSearchFilterFromRepositoryOnce() {
		
		String name="name";
		String date1 = "1991-10-10";
		String date2 = "1992-10-10";
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate startDateLD = LocalDate.parse(date1, dateTimeFormatter);
		LocalDate endDateLD = LocalDate.parse(date2, dateTimeFormatter);
		productService.findProductsAlreadyBooked(name, date1, date2);
	
		verify(mockBookingRepository, times(1)).searchForBookingForSearchFilter(name, startDateLD, endDateLD);
	}
	
	@Test
	public void test_findProductsByName_callsFindNameIgnoreCaseStartingWith_findByNameIgnoreCaseEndingWith_findByNameIgnoreCaseContainingMethods_ofProductRepository() {
		String name="name";
		
		productService.findProductsByName(name);		
		
		verify(mockRepo, times(1)).findByNameIgnoreCaseStartingWith(name);
		verify(mockRepo, times(1)).findByNameIgnoreCaseEndingWith(name);
		verify(mockRepo, times(1)).findByNameIgnoreCaseContaining(" " + name+ " ");
	}	

}
