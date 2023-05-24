package com.fdmgroup.Group4ProjectShazar.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.Group4ProjectShazar.Group4ProjectShazarApplication;
import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.service.CategoriesService;

@SpringBootTest(classes = { CategoriesController.class })
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = Group4ProjectShazarApplication.class)
public class CategoriesControllerTest {

	@MockBean
	MainController mainController;
	
	@MockBean
	CategoriesService mockCategoriesService;
	
	@Mock
	Product mockProduct;
	
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	@WithMockUser
	public void test_goToCategoryMethodWithParam_returnShowProductsOfCategory_AddAttributeProducts_andCategory() throws Exception {

		List<Product> mockListOfProducts = new ArrayList<>();
		mockListOfProducts.add(mockProduct);
		
		
		when(mockCategoriesService.listProductsOfCategory("vehicles")).thenReturn(mockListOfProducts);
		
		mockMvc.perform(get("/goToCategory/vehicles")).andExpect(status().isOk()).andExpect(model().attribute("products", mockListOfProducts)).andExpect(model().attribute("category", "VEHICLES")).andExpect(view().name("showProductsOfCategory"));
		
	}
	
	
	@Test
	@WithMockUser
	public void test_goToCategoryMethodWithParamWithUnderscore_returnShowProductsOfCategory_AddAttributeProducts_andCategoryWithoutUnderscore() throws Exception {

		List<Product> mockListOfProducts = new ArrayList<>();
		mockListOfProducts.add(mockProduct);
		
		
		when(mockCategoriesService.listProductsOfCategory("sport and hobbies")).thenReturn(mockListOfProducts);
		
		mockMvc.perform(get("/goToCategory/sport_and_hobbies")).andExpect(status().isOk()).andExpect(model().attribute("products", mockListOfProducts)).andExpect(model().attribute("category", "SPORT AND HOBBIES")).andExpect(view().name("showProductsOfCategory"));
		
	}
	
}
