package com.fdmgroup.Group4ProjectShazar.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.Group4ProjectShazar.Group4ProjectShazarApplication;
import com.fdmgroup.Group4ProjectShazar.model.Booking;
import com.fdmgroup.Group4ProjectShazar.model.Cart;
import com.fdmgroup.Group4ProjectShazar.model.CartItem;
import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.BookingService;
import com.fdmgroup.Group4ProjectShazar.service.CartItemService;
import com.fdmgroup.Group4ProjectShazar.service.CartService;
import com.fdmgroup.Group4ProjectShazar.service.ProductService;

@SpringBootTest(classes = { CartItemContoller.class })
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = Group4ProjectShazarApplication.class)

public class CartItemControllerTest {

	@MockBean
	private CartItemService mockCartItemService;

	@MockBean
	private CartService mockCartService;

	@MockBean
	private MainController mockMainController;

	@MockBean
	private DefaultUserDetailService mockDefaultUserService;

	@MockBean
	private ProductService mockProductService;

	@MockBean
	private BookingService mockBookingService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser
	public void test_goToCart_statusOk() throws Exception {

		User borrower = new User();
		borrower.setUsername("testUsername");
		Cart cart = new Cart();
		List<Booking> mockListofPastBookings = new ArrayList<>();
		List<Booking> mockListofCurrentBookings = new ArrayList<>();
		List<CartItem> mockListofCartItems = new ArrayList<>();

		when(mockBookingService.findPastBookingByBorrower(borrower)).thenReturn(mockListofPastBookings);
		when(mockBookingService.findCurrentBookingByBorrower(borrower)).thenReturn(mockListofCurrentBookings);
		when(mockCartItemService.findCartItems(cart)).thenReturn(mockListofCartItems);

		mockMvc.perform(get("/goToCart")).andExpect(status().isOk()).andExpect(view().name("cart"))
				.andExpect(model().attribute("pastBookings", mockListofPastBookings))
				.andExpect(model().attribute("currentBookings", mockListofCurrentBookings))
				.andExpect(model().attribute("cartItems", mockListofCartItems));
	}

	@Test
	@WithMockUser
	public void test_addToCart_whenDateIsEmpty() throws Exception {

		mockMvc.perform(post("/addToCart").param("startDate", "").param("endDate", "").param("productId", "0")
				.param("user", "testUsername")).andExpect(status().isOk()).andExpect(view().name("product"))
				.andExpect(model().attribute("errorNoDate", "Please choose date"));
	}

	@Test
	@WithMockUser
	public void test_addToCart_whenDateIsBeforeToday_returnProduct() throws Exception {

		mockMvc.perform(post("/addToCart").param("startDate", "2023-02-10").param("endDate", "2022-02-15")
				.param("productId", "0").param("user", "testUsername")).andExpect(status().isOk())
				.andExpect(view().name("product")).andExpect(model().attribute("errorDate", "Invalid date"));
	}

	@Test
	@WithMockUser
	public void test_addToCartMethod_Returns_Cart() throws Exception {

		User owner = new User();
		owner.setUsername("testUsername1");
		User borrower = new User();
		borrower.setUsername("testUsername");
		Cart cart = new Cart();
		Product product = new Product("testName", "testDescription", "8-10", true, 50.5, 7, "testColor", "testType",
				"testCategory", owner);
		List<Booking> mockListofPastBookings = new ArrayList<>();
		List<Booking> mockListofCurrentBookings = new ArrayList<>();
		List<CartItem> mockListofCartItems = new ArrayList<>();
		
		when(mockBookingService.findPastBookingByBorrower(borrower)).thenReturn(mockListofPastBookings);
		when(mockBookingService.findCurrentBookingByBorrower(borrower)).thenReturn(mockListofCurrentBookings);
		when(mockCartItemService.findCartItems(cart)).thenReturn(mockListofCartItems);
		when(mockProductService.findProductById(0)).thenReturn(product);
		
		mockMvc.perform(post("/addToCart").param("startDate", "2023-02-10").param("endDate", "2023-02-15")
				.param("productId", "0").param("user", "testUsername")).andExpect(status().isOk())
				.andExpect(view().name("cart")).andExpect(model().attribute("pastBookings", mockListofPastBookings))
				.andExpect(model().attribute("currentBookings", mockListofCurrentBookings))
				.andExpect(model().attribute("cartItems", mockListofCartItems));

	}

	@Test
	@WithMockUser
	public void test_deleteFromCart_return_Cart() throws Exception {
		User borrower = new User();
		borrower.setUsername("testUsername");
		Cart cart = new Cart();
		List<Booking> mockListofPastBookings = new ArrayList<>();
		List<Booking> mockListofCurrentBookings = new ArrayList<>();
		List<CartItem> mockListofCartItems = new ArrayList<>();
		
		when(mockBookingService.findPastBookingByBorrower(borrower)).thenReturn(mockListofPastBookings);
		when(mockBookingService.findCurrentBookingByBorrower(borrower)).thenReturn(mockListofCurrentBookings);
		when(mockCartItemService.findCartItems(cart)).thenReturn(mockListofCartItems);
		
		mockMvc.perform(post("/deleteFromCart").param("cartItemId", "0")).andExpect(status().isOk())
				.andExpect(view().name("cart")).andExpect(model().attribute("pastBookings", mockListofPastBookings))
				.andExpect(model().attribute("currentBookings", mockListofCurrentBookings))
				.andExpect(model().attribute("cartItems", mockListofCartItems));
	}

}
