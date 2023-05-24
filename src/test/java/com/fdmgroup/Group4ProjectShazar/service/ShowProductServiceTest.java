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

import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.repository.BookingRepository;
import com.fdmgroup.Group4ProjectShazar.repository.ShowProductRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ShowProductServiceTest {

	@InjectMocks
	ShowProductService mockService;
	
	@MockBean
	ShowProductRepository mockRepository;
	
	@MockBean
	BookingRepository mockBookingRepository;
	
	@Mock
	User mockUser;
	
	@Mock
	Product mockProduct;
	
	@Test
	public void test_getListOfProductsOfUserMethodOfShowProductService_callsFindByOwnerMetodOfShowProductRepository() {
		
		mockService.getListOfProductsOfUser(mockUser);
		
		verify(mockRepository, times(1)).findByOwner(mockUser);
	}	
	

	@Test
	public void test_getCurrentBookingsOfProductsOfOwnerMethodOfShowProductService_callsFindCurrentBookingOfProductMetodOfShowProductRepository() {
		
		mockService.getCurrentBookingsOfProductsOfOwner(mockProduct);
		
		verify(mockBookingRepository, times(1)).findCurrentBookingOfProduct(mockProduct);
	}
	
	@Test
	public void test_getBookingsOfProductsOfOwnerMethodOfShowProductService_callsFindByProductOrderByStartDateDescMetodOfShowProductRepository() {
		
		mockService.getBookingsOfProductsOfOwner(mockProduct);
		
		verify(mockBookingRepository, times(1)).findByProductOrderByStartDateDesc(mockProduct);
	}
	
	@Test
	public void test_listTopSixProductsOfUserMethodOfShowProductService_callsFindTopProductsMetodOfShowProductRepository() {
		
		mockService.listTopSixProducts();
		
		verify(mockRepository, times(1)).findTopProducts();
	}
	
}
