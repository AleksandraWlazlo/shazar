package com.fdmgroup.Group4ProjectShazar.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Booking {
	

	@Id
	@GeneratedValue
	@Column(name="BOOKING_ID")
	private int bookingId;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private User borrower;
	
	@Column(name="START_DATE")
	private LocalDate startDate;
	
	@Column(name="END_DATE")
	private LocalDate endDate;
	
	private boolean confirmedBooking;
	
	
	public Booking() {
		super();
	}

	public Booking(Product product, User borrower, LocalDate startDate, LocalDate endDate) {
		super();
		this.product = product;
		this.borrower = borrower;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Booking(Product product, User borrower, LocalDate startDate, LocalDate endDate, boolean confirmedBooking) {
		super();
		this.product = product;
		this.borrower = borrower;
		this.startDate = startDate;
		this.endDate = endDate;
		this.confirmedBooking = confirmedBooking;
	}

	public int getBookingId() {
		return bookingId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getBorrower() {
		return borrower;
	}

	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public boolean isConfirmedBooking() {
		return confirmedBooking;
	}

	public void setConfirmedBooking(boolean confirmedBooking) {
		this.confirmedBooking = confirmedBooking;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", product=" + product + ", borrower=" + borrower + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(borrower, endDate, product, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		return Objects.equals(borrower, other.borrower) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(product, other.product) && Objects.equals(startDate, other.startDate);
	}	
	
}
