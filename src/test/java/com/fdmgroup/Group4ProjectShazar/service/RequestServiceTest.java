package com.fdmgroup.Group4ProjectShazar.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.Group4ProjectShazar.model.Request;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.repository.RequestRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RequestServiceTest {

	@InjectMocks
	RequestService service;
	
	@MockBean
	RequestRepository mockRepository;
	
	@Mock
	Request mockRequest;
	
	@Mock
	User mockUser;
	
	@Test
	public void test_saveRequestMethodOfRequestService_callsSaveMethodOfRequestRepository() {
		
		service.saveRequest(mockRequest);
		
		verify(mockRepository, times(1)).save(mockRequest);
	}
	
	@Test
	public void test_listRequestMethodOfRequestService_callsFindByReceiverOrderBySendDateDesc_ofRequestRepository() {
		
		service.listRequests(mockUser);
		
		verify(mockRepository, times(1)).findByReceiverOrderBySendDateDesc(mockUser);
	}
	
	@Test
	public void test_deleteRequestMethodOfRequestService_callsDeleteByIdMethodOfRequestRepository() {
		
		service.deleteRequest(mockUser.getUserId());
		
		verify(mockRepository, times(1)).deleteById(mockUser.getUserId());
	}
	
	@Test
	public void test_getRequestFromDatabaseMethodOfRequestService_callsFindByIdMethodOfRequestRepository() {
		
		service.getRequestFromDatabase(mockUser.getUserId());
	
		verify(mockRepository, times(1)).findById(mockUser.getUserId());
	}
	
}
