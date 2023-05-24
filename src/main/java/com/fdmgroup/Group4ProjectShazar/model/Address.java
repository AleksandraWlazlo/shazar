package com.fdmgroup.Group4ProjectShazar.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue
	private Integer addressId;

	private String street;
	private String houseNumber;
	private int postalCode;
	private String city;
	private String country;
	
	public Address() {
		
	}

	public Address(String street, String houseNumber, int postalCode, String city, String country) {
		super();
		this.street = street;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, country, houseNumber, postalCode, street);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(houseNumber, other.houseNumber) && postalCode == other.postalCode
				&& Objects.equals(street, other.street);
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", street=" + street + ", houseNumber=" + houseNumber
				+ ", postalCode=" + postalCode + ", city=" + city + ", country=" + country + "]";
	}

	
	
}
