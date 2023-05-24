package com.fdmgroup.Group4ProjectShazar.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.Group4ProjectShazar.Group4ProjectShazarApplication;
import com.fdmgroup.Group4ProjectShazar.model.Address;
import com.fdmgroup.Group4ProjectShazar.model.Message;
import com.fdmgroup.Group4ProjectShazar.model.Request;
import com.fdmgroup.Group4ProjectShazar.model.Role;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.MessageService;
import com.fdmgroup.Group4ProjectShazar.service.RequestService;

@SpringBootTest(classes = {MessageController.class})
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = Group4ProjectShazarApplication.class)
public class MessageControllerTest {

	@MockBean
	DefaultUserDetailService mockDefaultUserService;
	
	@MockBean
	MessageService mockMessageService;
	
	@MockBean
	RequestService mockRequestService;
	
	@MockBean
	MainController mockMainController;
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	User mockUser;
	
	@Mock
	Message mockMessage;
	
	@Mock
	Request mockRequest;
	
	@Test
	@WithMockUser
	public void test_goToInboxMethod_statusIsOk_returnsViewInbox_addsAttributeAllReceivedMessages_andAllRequests() throws Exception {
		
		List<Message> mockListOfReceivedMessages = new ArrayList<>();
		List<Request> mockListOfRequests = new ArrayList<>();
		
		when(mockMessageService.listAllReceivedMessages(mockUser)).thenReturn(mockListOfReceivedMessages);
		when(mockRequestService.listRequests(mockUser)).thenReturn(mockListOfRequests);
		
		mockMvc.perform(get("/goToInbox")).andExpect(status().isOk()).andExpect(model().attribute("allReceivedMessages", mockListOfReceivedMessages)).andExpect(model().attribute("allRequests", mockListOfRequests)).andExpect(view().name("inbox"));
		
		
	}
	
	@Test
	@WithMockUser
	public void test_goToInboxMethod_statusIsOk_returnsViewInbox_addsAttributeAllReceivedMessages_andAllRequests_nonEmptyLists() throws Exception {
		
		List<Message> mockListOfReceivedMessages = new ArrayList<>();
		List<Request> mockListOfRequests = new ArrayList<>();
		
		mockListOfReceivedMessages.add(mockMessage);
		mockListOfRequests.add(mockRequest);
		
		// Default WithMockUser username = "user";
		when(mockDefaultUserService.findByUsername("user")).thenReturn(mockUser);
		when(mockMessageService.listAllReceivedMessages(mockUser)).thenReturn(mockListOfReceivedMessages);
		when(mockRequestService.listRequests(mockUser)).thenReturn(mockListOfRequests);
		
		mockMvc.perform(get("/goToInbox")).andExpect(status().isOk()).andExpect(model().attribute("allReceivedMessages", mockListOfReceivedMessages)).andExpect(model().attribute("allRequests", mockListOfRequests)).andExpect(view().name("inbox"));	
		
	}
	
	@Test
	@WithMockUser
	public void test_showSentMessage_statusIsOk_returnsViewSentMessages_addsAttributeAllSentMessages() throws Exception {
		
		List<Message> mockListOfSentMessages = new ArrayList<>();
		
		when(mockMessageService.listAllSentMessages(mockUser)).thenReturn(mockListOfSentMessages);
		
		mockMvc.perform(get("/goSentMessages")).andExpect(status().isOk()).andExpect(model().attribute("allSentMessages", mockListOfSentMessages)).andExpect(view().name("sentMessages"));	
	}
	
	@Test
	@WithMockUser
	public void test_showSentMessage_statusIsOk_returnsViewSentMessages_addsAttributeAllSentMessages_notAnEmptyList() throws Exception {
		
		List<Message> mockListOfSentMessages = new ArrayList<>();
		mockListOfSentMessages.add(mockMessage);
		
		when(mockDefaultUserService.findByUsername("user")).thenReturn(mockUser);
		when(mockMessageService.listAllSentMessages(mockUser)).thenReturn(mockListOfSentMessages);
		
		mockMvc.perform(get("/goSentMessages")).andExpect(status().isOk()).andExpect(model().attribute("allSentMessages", mockListOfSentMessages)).andExpect(view().name("sentMessages"));	
	}
	
	@Test
	@WithMockUser
	public void test_showAllUsers_statusIsOk_returnsViewMessageShowAllUsers_addsAttributeAllUsers() throws Exception {

		List<User> mockListOfAllUsers = new ArrayList<>();
		mockListOfAllUsers.add(mockUser);
		
		when(mockDefaultUserService.listAllUsers()).thenReturn(mockListOfAllUsers);
		
		mockMvc.perform(get("/showUsers")).andExpect(status().isOk()).andExpect(model().attribute("allUsers", mockListOfAllUsers)).andExpect(view().name("messageShowAllUsers"));	
	}
	
	@Test
	@WithMockUser
	public void test_writeMessageMethod_statusIsOk_returnsViewWriteMessage_addsAttributeReceiver() throws Exception {
		
		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(mockUser);
		
		mockMvc.perform(get("/writeMessage/").param("receiverUsername", "testUser")).andExpect(status().isOk()).andExpect(model().attribute("receiver", mockUser)).andExpect(view().name("writeMessage"));		
	}
	
	@Test
	@WithMockUser
	public void test_sendMessageMethod_statusIsOk_returnsViewWriteMessage_addsAttributeAllReceivedMessages_andAllRequests() throws Exception {
		
		List<Message> mockListOfReceivedMessages = new ArrayList<>();
		List<Request> mockListOfRequests = new ArrayList<>();
		
		
//		when(mockDefaultUserService.findByUsername("username")).thenReturn(mockUser);
//		when(mockDefaultUserService.findByUsername("receiverUser")).thenReturn(mockUser);

		mockMvc.perform(post("/sendMessage").param("username", "testUser").param("receiverUsername", "receiverUsername").param("messageText", "test")).andExpect(status().isOk()).andExpect(model().attribute("allReceivedMessages", mockListOfReceivedMessages)).andExpect(model().attribute("allRequests", mockListOfRequests)).andExpect(view().name("inbox"));		
	}
	
	@Test
	@WithMockUser
	public void test_sendMessageMethod_statusIsOk_returnsViewWriteMessage_addsAttributeAllReceivedMessages_andAllRequests_nonEmptyList() throws Exception {
		
		List<Message> mockListOfReceivedMessages = new ArrayList<>();
		List<Request> mockListOfRequests = new ArrayList<>();
		
		mockListOfReceivedMessages.add(mockMessage);
		mockListOfRequests.add(mockRequest);
		
		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(mockUser);
		when(mockMessageService.listAllReceivedMessages(mockUser)).thenReturn(mockListOfReceivedMessages);
		when(mockRequestService.listRequests(mockUser)).thenReturn(mockListOfRequests);
//		when(mockDefaultUserService.findByUsername("receiverUser")).thenReturn(mockUser);

		mockMvc.perform(post("/sendMessage").param("username", "testUser").param("receiverUsername", "receiverUsername").param("messageText", "test")).andExpect(status().isOk()).andExpect(model().attribute("allReceivedMessages", mockListOfReceivedMessages)).andExpect(model().attribute("allRequests", mockListOfRequests)).andExpect(view().name("inbox"));		
	}
	
	
}
