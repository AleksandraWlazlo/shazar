package com.fdmgroup.Group4ProjectShazar.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users_table")
public class User {

	@Id
	@GeneratedValue
	private Integer userId;

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;

	private String answerQuestion;

	private double rating;
	private int numberOfRatings;

	@ManyToOne
	private Address address;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Role role;

	public User() {

	}

	public User(String username) {
		this.username = username;
	}

	public User(String username, String password, String firstName, String lastName, String phoneNumber, String email,
			String answerQuestion, double rating, int numberOfRatings, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.answerQuestion = answerQuestion;
		this.rating = rating;
		this.numberOfRatings = numberOfRatings;
		this.role = role;
	}

	public User(String username, String password, String firstName, String lastName, String phoneNumber, String email,
			String answerQuestion, double rating, int numberOfRatings, Address address, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.answerQuestion = answerQuestion;
		this.rating = rating;
		this.numberOfRatings = numberOfRatings;
		this.address = address;
		this.role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getNumberOfRatings() {
		return numberOfRatings;
	}

	public void setNumberOfRatings(int numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAnswerQuestion() {
		return answerQuestion;
	}

	public void setAnswerQuestion(String answerQuestion) {
		this.answerQuestion = answerQuestion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, answerQuestion, email, firstName, lastName, numberOfRatings, password, phoneNumber,
				rating, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(address, other.address) && Objects.equals(answerQuestion, other.answerQuestion)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && numberOfRatings == other.numberOfRatings
				&& Objects.equals(password, other.password) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Double.doubleToLongBits(rating) == Double.doubleToLongBits(other.rating)
				&& Objects.equals(role, other.role) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", answerQuestion=" + answerQuestion
				+ ", rating=" + rating + ", numberOfRatings=" + numberOfRatings + ", address=" + address + ", role="
				+ role + "]";
	}

}
