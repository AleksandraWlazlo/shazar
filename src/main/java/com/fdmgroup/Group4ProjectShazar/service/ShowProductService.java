package com.fdmgroup.Group4ProjectShazar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Group4ProjectShazar.model.Booking;
import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.repository.BookingRepository;
import com.fdmgroup.Group4ProjectShazar.repository.ShowProductRepository;

@Service
public class ShowProductService {

	private ShowProductRepository showProductRepository;
	
	private BookingRepository bookingRepository;
	
	@Autowired
	public ShowProductService(ShowProductRepository showProductRepository, BookingRepository bookingRepository) {
		this.showProductRepository = showProductRepository;
		this.bookingRepository = bookingRepository;
	}
	
	public List<Product> getListOfProductsOfUser(User user) {
		
		return showProductRepository.findByOwner(user);
	}
	
	
	public List<Booking> getCurrentBookingsOfProductsOfOwner(Product product) {
		
		
		return bookingRepository.findCurrentBookingOfProduct(product);
	}
	
	public List<Booking> getBookingsOfProductsOfOwner(Product product) {
		
		return bookingRepository.findByProductOrderByStartDateDesc(product);
	}
	
	public List<Product> listTopSixProducts() {
		
		return showProductRepository.findTopProducts();
	}
	
}
