package com.fdmgroup.Group4ProjectShazar.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.Group4ProjectShazar.model.Booking;
import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	//Checking whether the input date from user is already in database.
	@Query("SELECT b FROM Booking b WHERE b.product = :product AND (:startDate BETWEEN b.startDate AND b.endDate OR :endDate BETWEEN b.startDate AND b.endDate OR b.startDate >= :startDate AND b.endDate <= :endDate)")
	List<Booking> searchForBooking(@Param("product")Product product, @Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);
	

	@Query("SELECT p FROM Booking b JOIN b.product p WHERE p.name LIKE :name  AND :startDate BETWEEN b.startDate AND b.endDate OR :endDate BETWEEN b.startDate AND b.endDate OR b.startDate >= :startDate AND b.endDate <= :endDate")
	List<Product> searchForBookingForSearchFilter(@Param("name")String name, @Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);


	@Query("SELECT b FROM Booking b WHERE b.borrower = :borrower AND b.endDate < SYSDATE")
	List<Booking> findPastBookingByBorrower(User borrower);
	
	@Query("SELECT b FROM Booking b WHERE b.borrower = :borrower AND b.endDate >= SYSDATE")
	List<Booking> findCurrentBookingByBorrower(User borrower);

	List<Booking> findByProductOrderByStartDateDesc(Product product);

	@Query("SELECT b FROM Booking b WHERE b.product = :product AND b.endDate > SYSDATE AND b.startDate <= SYSDATE AND b.endDate NOT LIKE SYSDATE")
	List<Booking> findCurrentBookingOfProduct(Product product);

}
