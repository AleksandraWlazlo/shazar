package com.fdmgroup.Group4ProjectShazar.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import com.fdmgroup.Group4ProjectShazar.model.Address;
import com.fdmgroup.Group4ProjectShazar.model.Booking;
import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.Request;
import com.fdmgroup.Group4ProjectShazar.model.Role;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.BookingService;
import com.fdmgroup.Group4ProjectShazar.service.MessageService;
import com.fdmgroup.Group4ProjectShazar.service.RequestService;

@SpringBootTest(classes = {RequestController.class})
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = Group4ProjectShazarApplication.class)
public class RequestControllerTest {

	@InjectMocks
	RequestController mockRequestController;
	
	@MockBean
	MessageController mockMessageController;
	
	@MockBean
	MainController mockMainController;
	
	@MockBean
	DefaultUserDetailService mockDefaultUserService;
	
	@MockBean
	MessageService mockMessageService;
	
	@MockBean
	RequestService mockRequestService;
	
	@MockBean
	BookingService mockBookingService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	Request mockRequest;
	
	@Mock
	Booking mockBooking;
	
	@Mock
	User mockUser;
	
	@Mock
	Product mockProduct;
	
	@Test
	@WithMockUser
	public void test_confirmRequest_returnInbox_AddAttributeMessageRequestAccepted() throws Exception {
		Role role = new Role("Customer");
		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
		User receiver = new User("receiver", "password", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
		User sender = new User("sender", "password", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
		Product product = new Product(0, "name", "test", 0, "19:00", true, 5.0, 20, "black", "small", "vehicle", null, null, null, null, null, null, null, null, null, null, address, receiver, null);
		Booking booking = new Booking(product, sender, LocalDate.of(2023, 2, 10), LocalDate.of(2023, 2, 15));
		Request request = new Request(sender, receiver, "test", false, LocalDateTime.now(), booking);
		
		when(mockRequestService.getRequestFromDatabase(0)).thenReturn(request);
//		System.out.println("TEST: " + mockRequestService.getRequestFromDatabase(0));
		when(mockBookingService.findByBookingId(request.getBooking().getBookingId())).thenReturn(booking);
		
		mockMvc.perform(get("/confirmRequest").param("requestId", "0")).andExpect(status().isOk()).andExpect(model().attribute("message", "Request Accepted")).andExpect(view().name("inbox"));
		
	}
	
	@Test
	@WithMockUser
	public void test_rejectRequest_returnInbox_AddAttributeMessageRequestRejected() throws Exception {
		Role role = new Role("Customer");
		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
		User receiver = new User("receiver", "password", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
		User sender = new User("sender", "password", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
		Product product = new Product(0, "name", "test", 0, "19:00", true, 5.0, 20, "black", "small", "vehicle", null, null, null, null, null, null, null, null, null, null, address, receiver, null);
		Booking booking = new Booking(product, sender, LocalDate.of(2023, 2, 10), LocalDate.of(2023, 2, 15));
		Request request = new Request(sender, receiver, "test", false, LocalDateTime.now(), booking);
		
		when(mockRequestService.getRequestFromDatabase(0)).thenReturn(request);
//		System.out.println("TEST: " + mockRequestService.getRequestFromDatabase(0));
		when(mockBookingService.findByBookingId(request.getBooking().getBookingId())).thenReturn(booking);
		
		mockMvc.perform(get("/rejectRequest").param("requestId", "0")).andExpect(status().isOk()).andExpect(model().attribute("message", "Request Rejected")).andExpect(view().name("inbox"));
		
	}	
	
}
