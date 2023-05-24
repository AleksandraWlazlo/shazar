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

import com.fdmgroup.Group4ProjectShazar.model.Cart;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.repository.CartRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CartServiceTest {

	@InjectMocks
	CartService mockService;
	
	@MockBean
	CartRepository mockRepository;
	
	@Mock
	Cart mockCart;
	
	@Mock
	User mockUser;
	
	@Test
	public void test_saveCartMethodOfCartService_callsSaveMethodOfCartRepository() {
		
		mockService.saveCart(mockCart);
		
		verify(mockRepository, times(1)).save(mockCart);
	}
	
	@Test
	public void test_findCartByUserMethodOfCartService_callsfindByUserMethodOfCartRepository() {
		
		mockService.findCartByUser(mockUser);
		
		verify(mockRepository, times(1)).findByUser(mockUser);
	}
}
