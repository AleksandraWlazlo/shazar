package com.fdmgroup.Group4ProjectShazar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.ShowProductService;

@Controller
public class MainController {
	
	@Autowired
	private DefaultUserDetailService defaultUserService;

	@Autowired
	private ShowProductService showProductService;
	
	public void populateLoggedUserModel(ModelMap model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		System.out.println("Username: " + username);
		
		if(username.equals("anonymousUser")) {
			model.addAttribute("loggedIn", false);
		} else {
			model.addAttribute("loggedIn", true);
			User user = defaultUserService.findByUsername(username);
			model.addAttribute("user", user);
		}
			
	}
	
	@GetMapping(value = "/")
	public String getIndex(ModelMap model) {

		populateLoggedUserModel(model);
		
		
		List<Product> listOfTopProducts = showProductService.listTopSixProducts();
		model.addAttribute("searchedPhrase", "");
		model.addAttribute("city", "");
		model.addAttribute("startDate", "");
		model.addAttribute("endDate", "");
		model.addAttribute("topProducts", listOfTopProducts.stream().limit(6).toList());
		
		return "index";
	}
	
	@GetMapping("/goToFAQ")
	public String toFAQ(ModelMap model) {
		
		populateLoggedUserModel(model);
		return "FAQ";
	}
	
	@PostMapping("/sendMessageToAdmin")
	public String sendMessageToAdmin(ModelMap model, @RequestParam String username, @RequestParam String email, @RequestParam String question) {
	
		populateLoggedUserModel(model);
		
		System.out.println("FAQ: USERNAME: " + username + ", EMAIL: " + email + ", QUESTION: " + question);
		
		model.addAttribute("message", "Your message has been sent to support team");
		return "FAQ";
	}
	
	@GetMapping("/toPrivacyPolicy")
	public String toPolicy(ModelMap model) {
		
		populateLoggedUserModel(model);
		return "privacy";
	}
	
	@GetMapping("/toTermsAndConditions")
	public String toTermsAndConditions(ModelMap model) {
		
		populateLoggedUserModel(model);
		return "termsCondition";
	}
	@GetMapping("/toAboutShazar")
	public String toAboutShazar(ModelMap model) {
		
		populateLoggedUserModel(model);
		return "aboutShazar";
	}
	
	@GetMapping("/toContact")
	public String toContactUs(ModelMap model) {
		
		populateLoggedUserModel(model);
		return "contact";
	}
}
