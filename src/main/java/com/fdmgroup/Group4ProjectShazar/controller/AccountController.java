package com.fdmgroup.Group4ProjectShazar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.Group4ProjectShazar.model.Address;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.AddressService;

@Controller
public class AccountController {

	@Autowired
	private DefaultUserDetailService defaultUserService;
	
	@Autowired
	MainController mainController;
	
	@Autowired
	AddressService addressService;
	
	@GetMapping("/showProfile")
	public String goToShowProfile(ModelMap model) {

		mainController.populateLoggedUserModel(model);
		
		return "showProfile";
	}
		
	@GetMapping("/goEditProfilePage")
	public String goToEditProfilePage(ModelMap model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = defaultUserService.findByUsername(username);
		
		System.out.println("Logged in User: " + username);
		
		model.addAttribute("user", user);
		
		mainController.populateLoggedUserModel(model);
		
		return "editProfile";
	}
	
	@PostMapping("/editProfile")
	public String editUser(ModelMap model, @ModelAttribute("user") User user, @ModelAttribute("address") Address address) {
		
		User userFromDatabase = defaultUserService.findByUsername(user.getUsername());
		
		userFromDatabase.setFirstName(user.getFirstName());
		userFromDatabase.setLastName(user.getLastName());
		userFromDatabase.setEmail(user.getEmail());
		userFromDatabase.setPhoneNumber(user.getPhoneNumber());
		
		addressService.saveAddress(address);
		Address addressFromDatabase = addressService.getAddressFromDatabase(address);
		
		userFromDatabase.setAddress(addressFromDatabase);
		
		System.out.println("EDIT PROFILE USER: " + user);
		
		defaultUserService.saveUser(userFromDatabase);
		
		mainController.populateLoggedUserModel(model);
		
		return "showProfile";
	}
	
	
	
}
