package com.fdmgroup.Group4ProjectShazar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Group4ProjectShazar.model.Booking;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.repository.BookingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

	private BookingRepository bookingRepository;

	@Autowired
	public BookingService(BookingRepository bookingRepository) {
		super();
		this.bookingRepository = bookingRepository;
	}

	public List<Booking> getBookingFromDatabase(Booking booking) {
		List<Booking> bookingFromDatabase = bookingRepository.searchForBooking(booking.getProduct(), booking.getStartDate(),
				booking.getEndDate());
		
		return bookingFromDatabase;
	}

	public void saveBooking(Booking booking) {
		List<Booking> bookingFromDatabase = getBookingFromDatabase(booking);

		if (bookingFromDatabase.size() == 0) {
			bookingRepository.save(booking);
		}
	}

	public Booking findByBookingId(Integer bookingId) {
		Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
		return optionalBooking.orElse(null);
	}
	
	public void deleteBooking(Integer bookingId) {
		
		bookingRepository.deleteById(bookingId);
	}
	
	public List<Booking> findPastBookingByBorrower(User borrower){
		return bookingRepository.findPastBookingByBorrower(borrower);
	}
	
	public List<Booking> findCurrentBookingByBorrower(User borrower){
		return bookingRepository.findCurrentBookingByBorrower(borrower);
	}
	
	public void editBooking(Booking booking) {
		bookingRepository.save(booking);
	}
	
	
}
