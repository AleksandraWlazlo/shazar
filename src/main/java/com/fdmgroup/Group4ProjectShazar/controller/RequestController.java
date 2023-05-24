package com.fdmgroup.Group4ProjectShazar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Group4ProjectShazar.model.Booking;
import com.fdmgroup.Group4ProjectShazar.model.Message;
import com.fdmgroup.Group4ProjectShazar.model.Request;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.BookingService;
import com.fdmgroup.Group4ProjectShazar.service.MessageService;
import com.fdmgroup.Group4ProjectShazar.service.RequestService;


@Controller
public class RequestController {

	@Autowired
	private MessageController messageController;
	
	@Autowired
	private MainController mainController;
	
	@Autowired
	private DefaultUserDetailService defaultUserService;

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private BookingService bookingService;
		
	public void sendRequestMessage(User user, Booking booking) {
		
		String text = "User: " + user.getUsername() + " requested your product: " + booking.getProduct().getName() + " | From: " + booking.getStartDate() + " To: " + booking.getEndDate();
		
		Request request = new Request(user, booking.getProduct().getOwner(), text, false, messageController.formattedCurrentDateTime(), booking);
		
		requestService.saveRequest(request);
		
	}
	
	@GetMapping("/confirmRequest")
	public String confirmRequest(ModelMap model, @RequestParam int requestId) {
		Request requestFromDatabase = requestService.getRequestFromDatabase(requestId);
		
		Booking bookingFromDatabase = bookingService.findByBookingId(requestFromDatabase.getBooking().getBookingId());
		
		bookingFromDatabase.setConfirmedBooking(true);
		
		String text = "Your request for product: " + bookingFromDatabase.getProduct().getName() + " has been confirmed.";
		
		Message message = new Message(bookingFromDatabase.getProduct().getOwner(), bookingFromDatabase.getBorrower(), text, false, messageController.formattedCurrentDateTime());
		
		messageService.saveMessage(message);
		
		bookingService.saveBooking(bookingFromDatabase);
		requestService.deleteRequest(requestFromDatabase.getRequestId());
		
		populateInbox(model);
		model.addAttribute("message", "Request Accepted");
		
		return "inbox";
	}
	
	@GetMapping("/rejectRequest")
	public String rejectRequest(ModelMap model, @RequestParam int requestId) {
		
		Request requestFromDatabase = requestService.getRequestFromDatabase(requestId);
		
		Booking bookingFromDatabase = bookingService.findByBookingId(requestFromDatabase.getBooking().getBookingId());
		
		String text = "Your request for product: " + bookingFromDatabase.getProduct().getName() + " has been rejected.";
		
		Message message = new Message(bookingFromDatabase.getProduct().getOwner(), bookingFromDatabase.getBorrower(), text, false, messageController.formattedCurrentDateTime());
		
		messageService.saveMessage(message);
		requestService.deleteRequest(requestFromDatabase.getRequestId());		
		bookingService.deleteBooking(bookingFromDatabase.getBookingId());
		
		populateInbox(model);		
		model.addAttribute("message", "Request Rejected");
		
		return "inbox";
	}
	
	public void populateInbox(ModelMap model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User userFromDatabase = defaultUserService.findByUsername(username);

		List<Message> allReceivedMessages = messageService.listAllReceivedMessages(userFromDatabase); 
		List<Request> allRequests = requestService.listRequests(userFromDatabase);
		
		mainController.populateLoggedUserModel(model);
		model.addAttribute("allReceivedMessages", allReceivedMessages);
		model.addAttribute("allRequests", allRequests);
	}
	
}
