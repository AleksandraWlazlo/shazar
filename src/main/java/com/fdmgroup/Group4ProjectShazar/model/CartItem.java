package com.fdmgroup.Group4ProjectShazar.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartItem {
	
	@Id
	@GeneratedValue
	@Column(name="CART_ITEM_ID")
	private int cartItemId;
	
	@ManyToOne
	private Cart cart;
	
	@ManyToOne
	private Product product;
	
	@Column(name="START_DATE")
	private LocalDate startDate;
	
	@Column(name="END_DATE")
	private LocalDate endDate;
	
	private double price;
	
	private int discount;

	public CartItem() {
		super();
	}

	public CartItem(Cart cart, Product product, LocalDate startDate, LocalDate endDate, double price, int discount) {
		super();
		this.cart = cart;
		this.product = product;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.discount = discount;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public int getCartItemId() {
		return cartItemId;
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

	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", cart=" + cart + ", product=" + product + ", startDate="
				+ startDate + ", endDate=" + endDate + ", price=" + price + ", discount=" + discount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cart, discount, endDate, price, product, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		return Objects.equals(cart, other.cart) && discount == other.discount && Objects.equals(endDate, other.endDate)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(product, other.product) && Objects.equals(startDate, other.startDate);
	}
	
	



	
}
