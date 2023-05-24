package com.fdmgroup.Group4ProjectShazar.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import com.fdmgroup.Group4ProjectShazar.model.Address;
import com.fdmgroup.Group4ProjectShazar.model.Role;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.AddressService;

@SpringBootTest(classes = {AccountController.class})
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = Group4ProjectShazarApplication.class)
public class AccountControllerTest {

	@MockBean
	private DefaultUserDetailService mockDefaultUserService;
	
	@MockBean
	private MainController mockMainController;
	
	@MockBean
	private AddressService mockAddressService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private User mockUser;
	
	@Test
	@WithMockUser
	public void test_goToShowProfile_statusOk() throws Exception {
			
		mockMvc.perform(get("/showProfile/")).andExpect(status().isOk()).andExpect(view().name("showProfile"));
	}
	
	@Test
	@WithMockUser
	public void test_goToEditProfilePage_statusOk() throws Exception {
		
		mockMvc.perform(get("/goEditProfilePage/")).andExpect(status().isOk()).andExpect(view().name("editProfile"));
	}
	
	@Test
	@WithMockUser
	public void test_editUser_statusOk() throws Exception {
		
		Role role = new Role("Customer");
		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
		User user = new User("testUser", "password", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
		
		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(user);
		
		mockMvc.perform(post("/editProfile").param("username", "testUser").param("firstName", "newName")).andExpect(status().isOk()).andExpect(view().name("showProfile"));
	}
	
	
}
