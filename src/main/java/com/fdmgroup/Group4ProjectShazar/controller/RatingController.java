package com.fdmgroup.Group4ProjectShazar.controller;

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
import com.fdmgroup.Group4ProjectShazar.service.ProductService;

@Controller
public class RatingController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MainController mainController;
	
	@Autowired
	private DefaultUserDetailService defaultUserDetailService;
	
	@Autowired
	private BookingService bookingService;

	  
	  @GetMapping("/rating/{productId}")
	  public String submitRating(@PathVariable int productId, @RequestParam double rating, ModelMap modelMap, @RequestParam String userName) {
	    Product product = productService.findProductById(productId);
	    double currentRating = product.getRating();
	    int numberOfRatings = product.getNumberOfRatings();
	    double updatedRating = (currentRating * numberOfRatings + rating) / (numberOfRatings + 1);
	    product.setRating(updatedRating);
	    System.out.println(productId + rating);
	    product.setNumberOfRatings(numberOfRatings + 1);
	    productService.save(product);
	    
	    User user = defaultUserDetailService.findByUsername(userName);
	    List<Booking> listOfCurrentBookings = bookingService.findCurrentBookingByBorrower(user);
	    List<Booking> listOfPastBookings = bookingService.findPastBookingByBorrower(user);
	    modelMap.addAttribute("pastBookings", listOfPastBookings);
	    modelMap.addAttribute("currentBookings", listOfCurrentBookings);
	    
	    mainController.populateLoggedUserModel(modelMap);
	    return "showBookingsOfUser";
	  }
	  
	  @GetMapping("/ratinguser/{viewUserName}")
	  public String submitUserRating(@PathVariable String viewUserName, @RequestParam double rating, ModelMap modelMap) {
		User user = defaultUserDetailService.findByUsername(viewUserName);
	    double currentRating = user.getRating();
	    int numberOfRatings = user.getNumberOfRatings();
	    double updatedRating = (currentRating * numberOfRatings + rating) / (numberOfRatings + 1);
	    user.setRating(updatedRating);
	    System.out.println(viewUserName + rating);
	    user.setNumberOfRatings(numberOfRatings + 1);
	    defaultUserDetailService.saveUser(user);
	    
	    modelMap.addAttribute("viewUser", user);
	    mainController.populateLoggedUserModel(modelMap);
	    System.out.println("RATING: " + rating);
		System.out.println("RATING USERNAME" + viewUserName);
		
	    return "showOtherUserProfile";
	  }
}

