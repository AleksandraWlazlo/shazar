package com.fdmgroup.Group4ProjectShazar.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.Group4ProjectShazar.model.Booking;
import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.repository.BookingRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BookingServiceTest {

	@InjectMocks
	BookingService bookingService;

	@MockBean
	BookingRepository mockBookingRepository;

	@Mock
	Booking mockBooking;

	@Mock
	User mockUser;

	@Test
	public void test_getBookingFromDatabase_callSearchForBookingMethodOfBookingRepository() {
		bookingService.getBookingFromDatabase(mockBooking);
		verify(mockBookingRepository, times(1)).searchForBooking(mockBooking.getProduct(), mockBooking.getStartDate(),
				mockBooking.getEndDate());
	}

	@Test
	public void test_saveBooking_returnsNull_calls_getBookingFromDataBaseMethod_calls_searchForBookingMethodOfRepostiory() {
		bookingService.saveBooking(mockBooking);
		verify(mockBookingRepository, times(1)).save(mockBooking);
	}

	@Test
	public void test_saveBooking_returnsNotEmptyList_calls_getBookingFromDataBaseMethod_calls_searchForBookingMethodOfRepostiory() {
		Product product = new Product();
		User borrower = new User();
		Booking booking = new Booking(product, borrower, LocalDate.of(2023, 2, 10), LocalDate.of(2023, 2, 15));
		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking);
		
		when(bookingService.getBookingFromDatabase(mockBooking)).thenReturn(bookings);

		bookingService.saveBooking(mockBooking);

		verify(mockBookingRepository, times(0)).save(mockBooking);
	}
	
	@Test
	public void test_saveBooking_returnsEmptyList_calls_getBookingFromDataBaseMethod_calls_saveMethodOfRepostiory() {
		List<Booking> bookings = new ArrayList<>();
		
		when(bookingService.getBookingFromDatabase(mockBooking)).thenReturn(bookings);

		bookingService.saveBooking(mockBooking);

		verify(mockBookingRepository, times(1)).save(mockBooking);
	}	

	@Test
	public void test_findByBookingId_callFindByIdMethodOfBookingRepostory() {

		bookingService.findByBookingId(mockBooking.getBookingId());
		verify(mockBookingRepository, times(1)).findById(mockBooking.getBookingId());
	}

	@Test
	public void test_deleteBooking_callsDeleteByIdMethodofBookingRepository() {

		bookingService.deleteBooking(mockBooking.getBookingId());
		verify(mockBookingRepository, times(1)).deleteById(mockBooking.getBookingId());
	}

	@Test
	public void test_findPastBookingByBorrower_calls_findPastBookingByBorrower_MethodOf_bookingRepository() {

		bookingService.findPastBookingByBorrower(mockUser);
		verify(mockBookingRepository, times(1)).findPastBookingByBorrower(mockUser);
	}

	@Test
	public void test_findCurrentBookingByBorrower_calls_findCurrentBookingByBorrower_MethodOf_bookingRepository() {

		bookingService.findCurrentBookingByBorrower(mockUser);
		verify(mockBookingRepository, times(1)).findCurrentBookingByBorrower(mockUser);
	}

	@Test
	public void test_editBooking_calls_save_methodOfBookingRepository() {
		bookingService.editBooking(mockBooking);
		verify(mockBookingRepository, times(1)).save(mockBooking);
	}
	
}
