package com.fdmgroup.Group4ProjectShazar.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import com.fdmgroup.Group4ProjectShazar.model.Booking;
import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.BookingService;
import com.fdmgroup.Group4ProjectShazar.service.ShowProductService;

@SpringBootTest(classes = {ShowProductController.class})
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = Group4ProjectShazarApplication.class)
public class ShowProductControllerTest {

	@MockBean
	MainController mockMainController;
	
	@MockBean
	DefaultUserDetailService mockDefaultUserDetailService;
	
	@MockBean
	ShowProductService mockShowProductService;
	
	@MockBean
	BookingService mockBookingService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	Product mockProduct;
	
	@Mock
	Booking mockBooking;
	
	@Mock
	User mockUser;
	
	@Test
	@WithMockUser
	public void test_goToShowProductOfUserMethod_statusIsOk_returnsViewShowProductsOfUser_addsAttributeProductsOfUser() throws Exception {
		
		List<Product> mockListOfProductsOfUser = new ArrayList<>();
		mockListOfProductsOfUser.add(mockProduct);
		
		when(mockDefaultUserDetailService.findByUsername("testUser")).thenReturn(mockUser);
		when(mockShowProductService.getListOfProductsOfUser(mockUser)).thenReturn(mockListOfProductsOfUser);
		
		mockMvc.perform(get("/goShowProductsOfUser/testUser")).andExpect(status().isOk()).andExpect(model().attribute("productsOfUser", mockListOfProductsOfUser)).andExpect(view().name("showProductsOfUser"));
		
	}
	
	@Test
	@WithMockUser
	public void test_goToShowProductOfUserMethod_statusIsOk_returnsViewShowProductsOfUser_addsAttributeProductsOfUser_emptyList() throws Exception {
		
		List<Product> mockListOfProductsOfUser = new ArrayList<>();
		
		when(mockDefaultUserDetailService.findByUsername("testUser")).thenReturn(mockUser);
		when(mockShowProductService.getListOfProductsOfUser(mockUser)).thenReturn(mockListOfProductsOfUser);
		
		mockMvc.perform(get("/goShowProductsOfUser/testUser")).andExpect(status().isOk()).andExpect(model().attribute("productsOfUser", mockListOfProductsOfUser)).andExpect(view().name("showProductsOfUser"));
		
	}
	
	@Test
	@WithMockUser
	public void test_goToShowBookingsOfProductOfUser_statusIsOk_returnsViewShowBookingOfProductsOfUser_addsAttributesForModel() throws Exception {
		
		List<Product> mockListOfProductsOfUser = new ArrayList<>();
		mockListOfProductsOfUser.add(mockProduct);
		
		List<Booking> mockListOfBookings = new ArrayList<>();
		List<Booking> mockListOfCurrentBookings = new ArrayList<>();
		
		mockListOfBookings.add(mockBooking);
		mockListOfCurrentBookings.add(mockBooking);
		
		when(mockDefaultUserDetailService.findByUsername("testUser")).thenReturn(mockUser);
		when(mockShowProductService.getListOfProductsOfUser(mockUser)).thenReturn(mockListOfProductsOfUser);
		when(mockShowProductService.getBookingsOfProductsOfOwner(mockProduct)).thenReturn(mockListOfBookings);
		when(mockShowProductService.getCurrentBookingsOfProductsOfOwner(mockProduct)).thenReturn(mockListOfCurrentBookings);
		
		mockMvc.perform(get("/goShowBookingsOfProductsOfUser/testUser")).andExpect(status().isOk()).andExpect(model().attribute("bookingsOfProductsOfOwner", mockListOfBookings)).andExpect(model().attribute("currentBookingsOfProductsOfOwner", mockListOfCurrentBookings)).andExpect(view().name("showBookingOfProductsOfUser"));
		
	}
	
	@Test
	@WithMockUser
	public void test_goToShowUserProfile_statusIsOk_returnsViewShowOtherUserProfile_addsAttributeViewUser() throws Exception {
				
		when(mockDefaultUserDetailService.findByUsername("testUser")).thenReturn(mockUser);
		
		mockMvc.perform(get("/showOtherUserProfile/testUser")).andExpect(status().isOk()).andExpect(model().attribute("viewUser", mockUser)).andExpect(view().name("showOtherUserProfile"));
		
	}
	
	@Test
	@WithMockUser
	public void test_productReturned_statusIsOk_returnsShowBookingOfProductsOfUser_addsAttributeMessage() throws Exception {
				
		when(mockDefaultUserDetailService.findByUsername("testUser")).thenReturn(mockUser);
		when(mockBookingService.findByBookingId(0)).thenReturn(mockBooking);
		
		
		mockMvc.perform(get("/productReturned").param("bookingId", "0").param("username", "testUser")).andExpect(status().isOk()).andExpect(model().attribute("message", "Return of product confirmed")).andExpect(view().name("showBookingOfProductsOfUser"));
		
	}
	
	@Test
	@WithMockUser
	public void test_goToShowBookingsOfUser_statusIsOk_returnSshowBookingsOfUser_addsAttributesForModel() throws Exception {
		
		List<Booking> mockListOfCurrentBookings = new ArrayList<>();
		List<Booking> mockListOfPastBookings = new ArrayList<>();
		mockListOfCurrentBookings.add(mockBooking);
		mockListOfPastBookings.add(mockBooking);
		
		when(mockDefaultUserDetailService.findByUsername("testUser")).thenReturn(mockUser);
		when(mockBookingService.findCurrentBookingByBorrower(mockUser)).thenReturn(mockListOfCurrentBookings);
		when(mockBookingService.findPastBookingByBorrower(mockUser)).thenReturn(mockListOfPastBookings);
	
		mockMvc.perform(get("/goShowBookingsOfUser/testUser")).andExpect(status().isOk()).andExpect(model().attribute("pastBookings", mockListOfPastBookings)).andExpect(model().attribute("currentBookings", mockListOfCurrentBookings)).andExpect(view().name("showBookingsOfUser"));
		
	}
	
	@Test
	@WithMockUser
	public void test_goToShowBookingsOfUser_statusIsOk_returnSshowBookingsOfUser_addsAttributesForModel_emptyLists() throws Exception {
		
		List<Booking> mockListOfCurrentBookings = new ArrayList<>();
		List<Booking> mockListOfPastBookings = new ArrayList<>();
		
		when(mockDefaultUserDetailService.findByUsername("testUser")).thenReturn(mockUser);
		when(mockBookingService.findCurrentBookingByBorrower(mockUser)).thenReturn(mockListOfCurrentBookings);
		when(mockBookingService.findPastBookingByBorrower(mockUser)).thenReturn(mockListOfPastBookings);
	
		mockMvc.perform(get("/goShowBookingsOfUser/testUser")).andExpect(status().isOk()).andExpect(model().attribute("pastBookings", mockListOfPastBookings)).andExpect(model().attribute("currentBookings", mockListOfCurrentBookings)).andExpect(view().name("showBookingsOfUser"));
		
	}
	
}
