package com.fdmgroup.Group4ProjectShazar.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fdmgroup.Group4ProjectShazar.Group4ProjectShazarApplication;
import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.service.ProductService;
import com.fdmgroup.Group4ProjectShazar.util.Filters;

@SpringBootTest(classes = { ProductController.class })
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = Group4ProjectShazarApplication.class)
public class ProductControllerTest {

	@MockBean
	private ProductService service;

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	List<Product>mockList; 
	
	@Mock
	Filters filters;
	@Mock
	Product mockProduct1;
	@Mock
	Product mockProduct2;
	@Mock
	Product mockProduct3;
	@Mock
	Product mockProduct4;

	@Test
	@WithMockUser
	public void test_goToAddProduct_statusOk() throws Exception {

		mockMvc.perform(get("/goToAddProduct")).andExpect(status().isOk()).andExpect(view().name("addProduct"));
	}

	@Test
	@WithMockUser
	public void test_EditProductReturnsProductPage_statusOk() throws Exception {
		
		List<Product>expectedList = new ArrayList<>();
		//expectedList.add(mockProduct3);
	
		mockList.add(mockProduct1);
		mockList.add(mockProduct2);
		String city = "Rome";
	
		when(service.filterResults(mockList,filters,city)).thenReturn(expectedList);

		mockMvc.perform(post("/filtered").param("searchedPhrase", "Car").param("color", "blue").param("category", "Vehicles")
						.param("type", "For kids").param("minPrice", "50").param("maxPrice", "200")
						.param("city", "Krakow").param("startDate", "2023-02-01").param("endDate", "2023-02-07"))
				.andExpect(status().isOk()).andExpect(model().attribute("foundProducts", expectedList)).andExpect(view().name("searching-page"));
	}
	
	@Test
	@WithMockUser
	public void test_filterSearch_IfUserPassMaxAndMinPriceAndMinIsBiggerThanMax_ErrorMessage() throws Exception {
		

		mockMvc.perform(post("/filtered").param("searchedPhrase", "Car").param("color", "blue").param("category", "Vehicles")
						.param("type", "For kids").param("minPrice", "50").param("maxPrice", "10")
						.param("city", "Krakow").param("startDate", "2023-02-01").param("endDate", "2023-02-07"))
				.andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "Min value cannot be higher than max.")).andExpect(view().name("searching-page"));
	}

	@Test
	@WithMockUser
	public void test_filterSearch_1() throws Exception {
		

		
		mockMvc.perform(post("/filtered").param("searchedPhrase", "Car").param("color", "blue").param("category", "Vehicles")
						.param("type", "For kids").param("minPrice", "10").param("maxPrice", "50")
						.param("city", "Krakow").param("startDate", "2023-02-01").param("endDate", "2023-02-07"))
				.andExpect(status().isOk()).andExpect(view().name("searching-page"));
		
		verify(service,times(1)).findProductsByName("Car");
	}
	
	@Test
	@WithMockUser
	public void test_goToSearchingPage_ReturnsError_WhenUserDoesntTypeAnythingInSearchBar() throws Exception {
		
		mockMvc.perform(get("/goToSearchingPage").param("productName", "").param("startDate", "2023-02-01").param("endDate", "2023-02-07")
				.param("city", "Krakow")).andExpect(status().isOk()).andExpect(model().attribute("errorNothingToSearch", "Please type something to search")).andExpect(view().name("index"));
	}
	
	@Test
	@WithMockUser
	public void test_goToSearchingPage_ReturnsError_WhenUserDoesntGiveDatas() throws Exception {
		
		mockMvc.perform(get("/goToSearchingPage").param("productName", "dasda").param("startDate", "").param("endDate", "")
				.param("city", "Krakow")).andExpect(status().isOk()).andExpect(model().attribute("errorNoDate", "Please choose date")).andExpect(view().name("index"));
	}
	
	@Test
	@WithMockUser
	public void test_goToSearchingPage_ReturnsError_WhenStartingDateIsLaterThanEndDate() throws Exception {
		
		mockMvc.perform(get("/goToSearchingPage").param("productName", "dasda").param("startDate", "2023-02-07").param("endDate", "2023-02-01")
				.param("city", "Krakow")).andExpect(status().isOk()).andExpect(model().attribute("errorDate", "Invalid date")).andExpect(view().name("index"));
	}
	
	@Test
	@WithMockUser
	public void test_goToProductPage_statusOk_returnViewProduct_addAttributeProduct() throws Exception {

		when(service.findProductById(1)).thenReturn(mockProduct1);
		
		mockMvc.perform(get("/goToProductPage/1")).andExpect(status().isOk()).andExpect(model().attribute("product", mockProduct1)).andExpect(view().name("product"));
	}
	

	@Test
	@WithMockUser
	public void test_goToEditProduct_statusOk_returnViewEditProduct_addAttributeProduct() throws Exception {

		when(service.findProductById(1)).thenReturn(mockProduct1);
		
		mockMvc.perform(get("/goToEditProduct/1")).andExpect(status().isOk()).andExpect(model().attribute("product", mockProduct1)).andExpect(view().name("editProduct"));
	}

	
//	
//	@Test
//	@WithMockUser
//	public void test_2() throws Exception {
//		
//		User owner = new User();
//		owner.setUsername("testUsername1");
//		Product product = new Product("testName", "testDescription", "8-10", true, 50.5, 7, "testColor", "testType",
//				"testCategory", owner);
//	
//		
//		//when(service.save(product)).thenReturn()
//		
//		mockMvc.perform(post("/addProduct").param("name", "testName").param("description", "testDescription").param("pickupTime", "8-10")
//				.param("priceForDay", "50.5").param("maxNumberOfDays", "7").param("color", "testColor").param("type", "testType").param("category", "testCategory")
//				.param("MultipartFile", "testColor").param("color", "testColor").param("color", "testColor")).andExpect(status().isOk()).andExpect(model().attribute("errorDate", "Invalid date")).andExpect(view().name("index"));
//	}
//	
}
