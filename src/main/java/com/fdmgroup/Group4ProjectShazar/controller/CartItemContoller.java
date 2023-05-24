package com.fdmgroup.Group4ProjectShazar.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Group4ProjectShazar.model.Cart;
import com.fdmgroup.Group4ProjectShazar.model.CartItem;
import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.BookingService;
import com.fdmgroup.Group4ProjectShazar.service.CartItemService;
import com.fdmgroup.Group4ProjectShazar.service.CartService;
import com.fdmgroup.Group4ProjectShazar.service.ProductService;

@Controller
public class CartItemContoller {

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private CartService cartService;

	@Autowired
	private MainController mainController;

	@Autowired
	private DefaultUserDetailService defaultUserService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/goToCart")
	public String goToCart(ModelMap model) {
		mainController.populateLoggedUserModel(model);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User borrower = defaultUserService.findByUsername(username);
		Cart cart = cartService.findCartByUser(borrower);
		populateListsForCart(model, borrower, cart);
		return "cart";
	}
	
	
	@PostMapping("/addToCart")
	public String addToCart(ModelMap model, @RequestParam Integer productId, @RequestParam String user,
			@RequestParam String startDate, @RequestParam String endDate) {
		
		User borrower = defaultUserService.findByUsername(user);
		Product product = productService.findProductById(productId);
		model.addAttribute("product", product);
		Cart cart = cartService.findCartByUser(borrower);

		// Checking if user chose startDate and endDate
		if (startDate.isEmpty() || endDate.isEmpty()) {
			model.addAttribute("errorNoDate", "Please choose date");
			return "product";
		}
		
		//change input from user which is String to LocalDate using DateTimeFormatter
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate startDateLD = LocalDate.parse(startDate, dateTimeFormatter);
		LocalDate endDateLD = LocalDate.parse(endDate, dateTimeFormatter);
		
		//assuring that the reservation cannot be made in the past and that the end date is not before start date.
		if (endDateLD.isBefore(LocalDate.now()) || startDateLD.isBefore(LocalDate.now()) || endDateLD.isBefore(startDateLD)) {
			model.addAttribute("errorDate", "Invalid date");
			return "product";
		}
		
		//calculate number of days based on start and end date
		int numberOfDays = (int) ChronoUnit.DAYS.between(startDateLD, endDateLD);
		
		double totalPrice = product.getPriceForDay() * (numberOfDays+1);
		
		CartItem cartItem = new CartItem(cart, product, startDateLD, endDateLD, totalPrice, 0);
		cartItemService.saveCartItem(cartItem);
		model.addAttribute("cartItem", cartItem);
		
		populateListsForCart(model, borrower, cart);
		mainController.populateLoggedUserModel(model);
		return "cart";
	}
	
	@PostMapping("/deleteFromCart")
	public String deleteFromCart(ModelMap model, @RequestParam Integer cartItemId) {
		
		mainController.populateLoggedUserModel(model);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User borrower = defaultUserService.findByUsername(username);
		Cart cart = cartService.findCartByUser(borrower);
		
		cartItemService.deleteCartItem(cartItemId);
		
		populateListsForCart(model, borrower, cart);
		
		return "cart";
	}
	
	public void populateListsForCart(ModelMap model, User borrower, Cart cart) {
		
		
		model.addAttribute("cartItems", cartItemService.findCartItems(cart));
		model.addAttribute("pastBookings", bookingService.findPastBookingByBorrower(borrower));
		model.addAttribute("currentBookings", bookingService.findCurrentBookingByBorrower(borrower));
		
	}
	
	
}
