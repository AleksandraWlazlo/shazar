package com.fdmgroup.Group4ProjectShazar.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Request {

	@Id
	@GeneratedValue
	@Column(name = "REQUEST_ID")
	private int requestId;

	@ManyToOne
	private User sender;

	@ManyToOne
	private User receiver;

	@Column(name = "MESSAGE_TEXT")
	private String messageText;

	private boolean wasRead;

	@Column(name = "SEND_DATE")
	private LocalDateTime sendDate;

	@OneToOne
	private Booking booking;

	public Request() {
		
	}
	
	public Request(User sender, User receiver, String messageText, boolean wasRead, LocalDateTime sendDate,
			Booking booking) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.messageText = messageText;
		this.wasRead = wasRead;
		this.sendDate = sendDate;
		this.booking = booking;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int request_Id) {
		this.requestId = request_Id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public boolean isWasRead() {
		return wasRead;
	}

	public void setWasRead(boolean wasRead) {
		this.wasRead = wasRead;
	}

	public LocalDateTime getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@Override
	public int hashCode() {
		return Objects.hash(booking, messageText, receiver, sendDate, sender, wasRead);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Objects.equals(booking, other.booking) && Objects.equals(messageText, other.messageText)
				&& Objects.equals(receiver, other.receiver) && Objects.equals(sendDate, other.sendDate)
				&& Objects.equals(sender, other.sender) && wasRead == other.wasRead;
	}

	@Override
	public String toString() {
		return "RequestMessage [request_Id=" + requestId + ", sender=" + sender + ", receiver="
				+ receiver + ", messageText=" + messageText + ", wasRead=" + wasRead + ", sendDate=" + sendDate
				+ ", booking=" + booking + "]";
	}

}
