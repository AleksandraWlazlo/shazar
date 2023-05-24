package com.fdmgroup.Group4ProjectShazar.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Group4ProjectShazar.model.Booking;
import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.BookingService;
import com.fdmgroup.Group4ProjectShazar.service.ShowProductService;

@Controller
public class ShowProductController {

	@Autowired
	private MainController mainController;
	
	@Autowired
	private DefaultUserDetailService defaultUserDetailService;
	
	@Autowired
	private ShowProductService showProductService;
	
	@Autowired
	private BookingService bookingService;

	@GetMapping("/goShowProductsOfUser/{username}")
	public String goToShowProductOfUser(ModelMap model, @PathVariable String username) {
		
		mainController.populateLoggedUserModel(model);
		
		User user = defaultUserDetailService.findByUsername(username);
		
		List<Product> listOfProductsOfUser = showProductService.getListOfProductsOfUser(user);
		
		model.addAttribute("owner", user);
		model.addAttribute("productsOfUser", listOfProductsOfUser);
	
		return "showProductsOfUser";
	}
	
	@GetMapping("/goShowBookingsOfProductsOfUser/{username}")
	public String goToShowBookingsOfProductOfUser(ModelMap model, @PathVariable String username) {
		
		mainController.populateLoggedUserModel(model);
		
		populateBookingLists(model, username);	
		return "showBookingOfProductsOfUser";
	}
	
	@GetMapping("/showOtherUserProfile/{username}")
	public String goToShowUserProfile(ModelMap model, @PathVariable String username) {
		mainController.populateLoggedUserModel(model);
		
		User user = defaultUserDetailService.findByUsername(username);
		
		model.addAttribute("viewUser", user);
		
		return "showOtherUserProfile";
	}
	
	@GetMapping("/productReturned")
	public String productReturned(ModelMap model, @RequestParam int bookingId, @RequestParam String username) {
		mainController.populateLoggedUserModel(model);
		
		Booking booking = bookingService.findByBookingId(bookingId);
		
		booking.setEndDate(LocalDate.now());
				
		bookingService.editBooking(booking);
		
		populateBookingLists(model, username);
		
		model.addAttribute("message", "Return of product confirmed");
		
		return "showBookingOfProductsOfUser";
	}

	@GetMapping("/goShowBookingsOfUser/{username}")
	public String goToShowBookingsOfUser(ModelMap model, @PathVariable String username) {
		
		mainController.populateLoggedUserModel(model);
		
		User user = defaultUserDetailService.findByUsername(username);
		
		List<Booking> listOfCurrentBookings = bookingService.findCurrentBookingByBorrower(user);
		List<Booking> listOfPastBookings = bookingService.findPastBookingByBorrower(user);
		
		model.addAttribute("pastBookings", listOfPastBookings);
		model.addAttribute("currentBookings", listOfCurrentBookings);
		
		return "showBookingsOfUser";
	}
	
	public void populateBookingLists(ModelMap model, String username) {
		
		User user = defaultUserDetailService.findByUsername(username);
		
		List<Product> listOfProductsOfUser = showProductService.getListOfProductsOfUser(user);
		
		List<Booking> listOfBookings = new ArrayList<>();
		List<Booking> listOfCurrentBookings = new ArrayList<>();
			
		for(Product product : listOfProductsOfUser) {
			listOfBookings.addAll(showProductService.getBookingsOfProductsOfOwner(product));
		}
		
		for(Product product : listOfProductsOfUser) {
			listOfCurrentBookings.addAll(showProductService.getCurrentBookingsOfProductsOfOwner(product));
		}
		
		model.addAttribute("bookingsOfProductsOfOwner", listOfBookings);
		
		model.addAttribute("currentBookingsOfProductsOfOwner", listOfCurrentBookings);
		
	}
	
}
