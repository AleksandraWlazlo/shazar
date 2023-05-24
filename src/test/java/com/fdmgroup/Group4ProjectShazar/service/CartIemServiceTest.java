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
import com.fdmgroup.Group4ProjectShazar.model.CartItem;
import com.fdmgroup.Group4ProjectShazar.repository.CartItemRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CartIemServiceTest {

	@InjectMocks
	CartItemService cartItemService;
	
	@MockBean
	CartItemRepository mockCartItemRepository;
	
	@Mock
	CartItem mockCartItem;
	
	@Mock
	Cart mockCart;
	
	@Test
	public void test_findCartItemById_calls_getById_MethodOf_cartItemRepository() {
		cartItemService.findCartItemById(mockCartItem.getCartItemId());
		verify(mockCartItemRepository, times(1)).getById(mockCartItem.getCartItemId());
	}
	
	@Test
	public void test_saveCartItem_calls_cartItemRepository__Save_Method() {
		cartItemService.saveCartItem(mockCartItem);
		verify(mockCartItemRepository, times(1)).save(mockCartItem);	
	}
	
	@Test
	public void test_listFindCartItems_MethodOfCartItemService_calls_findByCartMethodOfCatyItemRepository() {
		cartItemService.findCartItems(mockCart);
		verify(mockCartItemRepository, times(1)).findByCart(mockCart);
	}
	
	@Test
	public void test_deleteCartItem_calls_deleteByIdMethodofCartItemRepostiory() {
		cartItemService.deleteCartItem(mockCartItem.getCartItemId());
		verify(mockCartItemRepository, times(1)).deleteById(mockCartItem.getCartItemId());	
	}
	

}
