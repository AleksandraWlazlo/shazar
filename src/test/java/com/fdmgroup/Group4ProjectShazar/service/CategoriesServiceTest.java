package com.fdmgroup.Group4ProjectShazar.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.Group4ProjectShazar.repository.CategoriesRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CategoriesServiceTest {

	@InjectMocks
	CategoriesService mockService;
	
	@MockBean
	CategoriesRepository mockRepository;
	
	@Test
	public void test_listRequestMethodOfRequestService_callsFindByReceiverOrderBySendDateDesc_ofRequestRepository() {
		
		mockService.listProductsOfCategory("vehicles");
		
		verify(mockRepository, times(1)).findByCategoryIgnoreCase("vehicles");
	}
	
	
}
