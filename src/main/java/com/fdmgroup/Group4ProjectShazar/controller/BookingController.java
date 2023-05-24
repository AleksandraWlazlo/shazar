package com.fdmgroup.Group4ProjectShazar.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Group4ProjectShazar.model.Booking;
import com.fdmgroup.Group4ProjectShazar.model.CartItem;
import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.BookingService;
import com.fdmgroup.Group4ProjectShazar.service.CartItemService;
import com.fdmgroup.Group4ProjectShazar.service.ProductService;

@Controller
public class BookingController {

	@Autowired
	private MainController mainController;

	@Autowired
	private DefaultUserDetailService defaultUserService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private RequestController requestController;
	

	@PostMapping("/addBooking")
	public String addBooking(ModelMap model, @RequestParam String startDate, @RequestParam String endDate,
			@RequestParam Integer productId, @RequestParam String user) {
		mainController.populateLoggedUserModel(model);
		
		User borrower = defaultUserService.findByUsername(user);
		Product product = productService.findProductById(productId);
		model.addAttribute("product",product);
		
		//Checking if user chose startDate and endDate
		if (startDate.isEmpty() || endDate.isEmpty()) {
			model.addAttribute("errorNoDate", "Please choose date");
			return "product";
		}
		
		//we change input from user which is String to LocalDate using DateTimeFormatter
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate startDateLD = LocalDate.parse(startDate, dateTimeFormatter);
		LocalDate endDateLD = LocalDate.parse(endDate, dateTimeFormatter);
		
		//assuring that the reservation cannot be made in the past and that the end date is not before start date.
		if (endDateLD.isBefore(LocalDate.now()) || startDateLD.isBefore(LocalDate.now()) || endDateLD.isBefore(startDateLD)) {
			model.addAttribute("errorDate", "Invalid date");
			return "product";
		}
		
		Booking newBooking = new Booking(product, borrower, startDateLD, endDateLD);
		
		model.addAttribute("booking", newBooking);

		List<Booking> bookingFromDatabase = bookingService.getBookingFromDatabase(newBooking);
		
		//Checking whether date is available. If no, displaying error message
		if(bookingFromDatabase.size() != 0) {
			model.addAttribute("errorMessage", "This date in not available");
			return "product";
		}
		
		bookingService.saveBooking(newBooking);
		
		requestController.sendRequestMessage(borrower, newBooking);
		
		return "booking-confirmation";

	}
	
	@PostMapping("/addBookingFromCart")
	public String addBookingFromCart(ModelMap model, @RequestParam String startDate, @RequestParam String endDate,
			@RequestParam Integer productId, @RequestParam String user, @RequestParam Integer cartItemId) {
		mainController.populateLoggedUserModel(model);
		
		User borrower = defaultUserService.findByUsername(user);
		Product product = productService.findProductById(productId);
		model.addAttribute("product",product);
		CartItem cartItem = cartItemService.findCartItemById(cartItemId);
		
		
		//we change input from user which is String to LocalDate using DateTimeFormatter
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate startDateLD = LocalDate.parse(startDate, dateTimeFormatter);
		LocalDate endDateLD = LocalDate.parse(endDate, dateTimeFormatter);
		
		Booking newBooking = new Booking(product, borrower, startDateLD, endDateLD);
		
		model.addAttribute("booking", newBooking);

		List<Booking> bookingFromDatabase = bookingService.getBookingFromDatabase(newBooking);
		
		//Checking whether date is available. If no, displaying error message
		if(bookingFromDatabase.size() != 0) {
			model.addAttribute("errorMessage", "This date in not available");
			cartItemService.deleteCartItem(cartItem.getCartItemId());
			
			return "product";
		}
		
		bookingService.saveBooking(newBooking);
		
		requestController.sendRequestMessage(borrower, newBooking);
		cartItemService.deleteCartItem(cartItem.getCartItemId());
		return "booking-confirmation";

	}
		

}