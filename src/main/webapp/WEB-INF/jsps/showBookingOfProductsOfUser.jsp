<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Booking</title>
</head>
<body>
	<jsp:include page="header.jsp" /><br>
	<div class="in-center">
		<h2>Current bookings of your products</h2>
	
		${message}<br>
	
		<c:forEach items="${currentBookingsOfProductsOfOwner}" var="currentBooking">
			<div class="product-card">
				<a href="/goToProductPage/${currentBooking.product.productId}">
					<img class="cart-product-image" src=${currentBooking.product.mainProductImagePath }/>
				</a>
				<div class="cart-product-text">
					<b><p>${currentBooking.product.name}</p></b><br>
					From: ${currentBooking.startDate}<br><br>
					To: ${currentBooking.endDate} <br><br>
					By: ${currentBooking.borrower.username}<br>
				</div>
				<div class="product-buttons">
					<form action="/productReturned">
						<input type="hidden" name="username" value="${user.username }"/>
						<input type="hidden" name="bookingId" value="${currentBooking.bookingId}"/>
						<input class=violetButton type="submit" value="Product returned" />
					</form>
				</div>
				<br>
				<hr>
			</div>
		</c:forEach>
	
		<hr>
		<h2>All bookings of your products</h2>
	
		<c:forEach items="${bookingsOfProductsOfOwner}" var="booking">
			<div class="product-card">
				<a href="/goToProductPage/${booking.product.productId}">
					<img class="cart-product-image" src=${booking.product.mainProductImagePath }/>
				</a>
				<div class="cart-product-text">
					<b><p>${booking.product.name}</p></b><br>
					From: ${booking.startDate}<br><br>
					To: ${booking.endDate} <br><br>
					By: ${booking.borrower.username}<br><br>
				</div>
				<div class="product-buttons">
					<a class=violetButton href="/goToProductPage/${booking.product.productId}">Go to<br> product page</a>
				</div>
			</div>
		</c:forEach>
	
		<a class="link" href="/">Go to Homepage</a>
	</div>
	<br><br>
	<jsp:include page="footer.jsp" />
</body>
</html>