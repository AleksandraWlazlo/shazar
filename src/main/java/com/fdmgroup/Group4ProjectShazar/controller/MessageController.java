package com.fdmgroup.Group4ProjectShazar.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Group4ProjectShazar.model.Message;
import com.fdmgroup.Group4ProjectShazar.model.Request;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.MessageService;
import com.fdmgroup.Group4ProjectShazar.service.RequestService;


@Controller
public class MessageController {

	@Autowired
	private DefaultUserDetailService defaultUserService;

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private RequestService requestService;

	@Autowired
	private MainController mainController;
	
	public LocalDateTime formattedCurrentDateTime() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedCurrentTimeString = currentTime.format(dateTimeFormatter);
		
		return LocalDateTime.parse(formattedCurrentTimeString, dateTimeFormatter);
	}

	@GetMapping("/goToInbox")
	public String goToInbox(ModelMap model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User userFromDatabase = defaultUserService.findByUsername(username);

		List<Message> allReceivedMessages = messageService.listAllReceivedMessages(userFromDatabase); 
		List<Request> allRequests = requestService.listRequests(userFromDatabase);
		
		mainController.populateLoggedUserModel(model);
		model.addAttribute("allReceivedMessages", allReceivedMessages);
		model.addAttribute("allRequests", allRequests);
		
		return "inbox";
	}
	
	@GetMapping("/goSentMessages")
	public String showSentMessage(ModelMap model) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User userFromDatabase = defaultUserService.findByUsername(username);

		List<Message> allSentMessages = messageService.listAllSentMessages(userFromDatabase); 
		
		mainController.populateLoggedUserModel(model);
		model.addAttribute("allSentMessages", allSentMessages);
		
		return "sentMessages";
	}

	@GetMapping("/showUsers")
	public String showAllUsers(ModelMap model) {
		mainController.populateLoggedUserModel(model);
		List<User> allUsers = defaultUserService.listAllUsers();

		model.addAttribute("allUsers", allUsers);

		return "messageShowAllUsers";
	}
	

	@GetMapping("/writeMessage")
	public String writeMessage(ModelMap model, @RequestParam String receiverUsername) {

		User receiver = defaultUserService.findByUsername(receiverUsername);

		model.addAttribute("receiver", receiver);

		mainController.populateLoggedUserModel(model);
		return "writeMessage";
	}

	@PostMapping("/sendMessage")
	public String sendMessage(ModelMap model, @RequestParam String username, @RequestParam String receiverUsername,
			@RequestParam String messageText) {
		User userFromDatabase = defaultUserService.findByUsername(username);
		User receiver = defaultUserService.findByUsername(receiverUsername);
		
		Message message = new Message(userFromDatabase, receiver, messageText, false, formattedCurrentDateTime());		
		
		messageService.saveMessage(message);
		
		mainController.populateLoggedUserModel(model);
		
		List<Message> allReceivedMessages = messageService.listAllReceivedMessages(userFromDatabase); 
		model.addAttribute("allReceivedMessages", allReceivedMessages);
		
		List<Request> allRequests = requestService.listRequests(userFromDatabase);
		model.addAttribute("allRequests", allRequests);
		
		System.out.println("MESSAGE SENDER: " + userFromDatabase);
		System.out.println("MESSAGE RECEIVER: " + receiver);
		System.out.println("MessageText: " + messageText);
		System.out.println("MESSAGE Date: " + LocalDateTime.now());
		
		return "inbox";
	}	
}
