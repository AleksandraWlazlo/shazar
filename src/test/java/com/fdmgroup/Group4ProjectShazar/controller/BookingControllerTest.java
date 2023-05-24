package com.fdmgroup.Group4ProjectShazar.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

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
import com.fdmgroup.Group4ProjectShazar.model.CartItem;
import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.BookingService;
import com.fdmgroup.Group4ProjectShazar.service.CartItemService;
import com.fdmgroup.Group4ProjectShazar.service.ProductService;

@SpringBootTest(classes = { BookingController.class })
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = Group4ProjectShazarApplication.class)

public class BookingControllerTest {

	@MockBean
	private BookingService mockBookingService;

	@MockBean
	private ProductService mockProductService;

	@MockBean
	private CartItemService mockCartItemService;

	@MockBean
	private DefaultUserDetailService mockDefaultUserService;

	@MockBean
	private MainController mockMainController;

	@MockBean
	private RequestController mockRequestController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser
	public void test_addBooking_whenBookingDateIsEmpty_returnProduct() throws Exception {

		mockMvc.perform(post("/addBooking").param("startDate", "").param("endDate", "").param("productId", "0")
				.param("user", "testUsername")).andExpect(status().isOk()).andExpect(view().name("product"))
				.andExpect(model().attribute("errorNoDate", "Please choose date"));
	}

	@Test
	@WithMockUser
	public void test_addBooking_whenBookingDateIsBeforeToday_returnProduct() throws Exception {

		mockMvc.perform(post("/addBooking").param("startDate", "2022-02-10").param("endDate", "2022-02-15")
				.param("productId", "0").param("user", "testUsername")).andExpect(status().isOk())
				.andExpect(view().name("product")).andExpect(model().attribute("errorDate", "Invalid date"));
	}

	@Test
	@WithMockUser
	public void test_addBooking_whenBookingNotInDatabase_returnBookingConfirmation_saveBookingInDatabase()
			throws Exception {
		
		User owner = new User();
		User borrower = new User();
		borrower.setUsername("testUsername");
		owner.setUsername("testUsername1");
		Product product = new Product("testName", "testDescription", "8-10", true, 50.5, 7, "testColor", "testType",
				"testCategory", owner);
		Booking booking = new Booking(product, borrower, LocalDate.of(2023, 2, 10), LocalDate.of(2023, 2, 15));

		when(mockBookingService.getBookingFromDatabase(booking)).thenReturn(null);

		mockMvc.perform(post("/addBooking").param("startDate", "2023-02-10").param("endDate", "2023-02-15")
				.param("productId", "0").param("user", "testUsername")).andExpect(status().isOk())
				.andExpect(view().name("booking-confirmation"));
	}

//	@Test
//	@WithMockUser
//	public void test_addBooking_whenBookingIsInDatabase_returnProduct_error() throws Exception {
//
//		User owner = new User();
//		User borrower = new User();
//		borrower.setUsername("testUsername");
//
//		Product product = new Product("testName", "testDescription", "8-10", true, 50.5, 7, "testColor", "testType",
//				"testCategory", owner);
//		
//		Booking booking = new Booking(product, borrower, LocalDate.of(2023, 2, 10), LocalDate.of(2023, 2, 15));
//		Booking bookingFromDatabase = new Booking(product, borrower, LocalDate.of(2023, 2, 10), LocalDate.of(2023, 2, 15));
//		
//		when(mockProductService.findProductById(0)).thenReturn(product);
//		when(mockBookingService.getBookingFromDatabase(booking)).thenReturn(bookingFromDatabase);
//
//		mockMvc.perform(post("/addBooking").param("startDate", "2023-02-10").param("endDate", "2023-02-15")
//				.param("productId", "0").param("user", "testUsername")).andExpect(status().isOk())
//				.andExpect(view().name("product"))
//				.andExpect(model().attribute("errorMessage", "This date in not available"));
//	}

	@Test
	@WithMockUser
	public void test_addBookingFromCart() throws Exception {
		
		User owner = new User();
		User borrower = new User();
		borrower.setUsername("testUsername");
		owner.setUsername("testUsername1");
		CartItem cartItem = new CartItem();
		Product product = new Product("testName", "testDescription", "8-10", true, 50.5, 7, "testColor", "testType",
				"testCategory", owner);
		Booking booking = new Booking(product, borrower, LocalDate.of(2023, 2, 10), LocalDate.of(2023, 2, 15));

		when(mockBookingService.getBookingFromDatabase(booking)).thenReturn(null);
		when(mockCartItemService.findCartItemById(0)).thenReturn(cartItem);
		
		mockMvc.perform(post("/addBookingFromCart").param("startDate", "2023-02-10").param("endDate", "2023-02-15")
				.param("productId", "0").param("user", "testUsername").param("cartItemId", "0"))
				.andExpect(status().isOk()).andExpect(view().name("booking-confirmation"));

	}
}
