<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
</head>
<body>

	<jsp:include page="header.jsp" /><br>
	<br> <h2>Your cart</h2>
	<br>
	<br>
<div class="in-center">
	<c:forEach items="${cartItems}" var="cartItem">
		<div class="product-card">
			<a href="/goToProductPage/${cartItem.product.productId}">
				<img class="cart-product-image" src=${cartItem.product.mainProductImagePath }/>
			</a>
			<div class="cart-product-text">
				<b>${cartItem.product.name}</b><br><br>
				start date: ${cartItem.startDate}<br><br>
				end date: ${cartItem.endDate}<br><br>
				Pickup Time: ${cartItem.product.pickupTime }<br><br>
				Pickup Adress: ${ cartItem.product.pickupAdress.city }
					${ cartItem.product.pickupAdress.country }<br><br>
				<b><p style="color:purple">price: ${cartItem.price} $</p></b>
			</div>
			<div class="product-buttons">
				<form action="/addBookingFromCart" method="post">
					<span style="color: red">${errorMessage}</span> 
					<span style="color: red">${errorNoDate}</span> 
					<span style="color: red">${errorDate}</span><br>
					<input type="hidden" name="user" value="${user.username}"> 
					<input type="hidden" name="productId" value="${cartItem.product.productId}" /> 
					<input type="hidden" name="startDate" value="${cartItem.startDate}" /> 
					<input type="hidden" name="endDate" value="${cartItem.endDate}" /> 
					<input type="hidden" name="cartItemId" value="${cartItem.cartItemId}" /> 
					<input class="violetButton" type="submit" name="book" value="book" /> <br><br>
					<input class="violetButton" type="submit" name="delete" value="delete" formaction="/deleteFromCart" />
				</form>
			</div>
		</div>
	</c:forEach>
	<hr>
	<br><br>
	<h2>Current bookings</h2>
	<br>
	<c:forEach items="${currentBookings}" var="currentBooking">
		<div class="product-card">
			<a href="/goToProductPage/${currentBooking.product.productId}">
			<img class="cart-product-image" src=${currentBooking.product.mainProductImagePath }/>
			</a>
			<div class="cart-product-text">
				<b><p>${currentBooking.product.name}</p></b><br>
				start date: ${currentBooking.startDate}<br><br>
				end date: ${currentBooking.endDate}<br><br>
				Pickup Time: ${currentBooking.product.pickupTime }<br><br>
				Pickup Adress: ${ currentBooking.product.pickupAdress.street } ${ currentBooking.product.pickupAdress.houseNumber }
					${ currentBooking.product.pickupAdress.postalCode } ${ currentBooking.product.pickupAdress.city }
					${ currentBooking.product.pickupAdress.country }<br><br>
				
				<b><p style="color:purple">confirmed: 
					<c:choose>
					<c:when test="${currentBooking.confirmedBooking}">
						yes
					</c:when>
					<c:otherwise>
						no
					</c:otherwise>
					</c:choose></p></b>
			</div>
			<div class="product-buttons">
				<a class=violetButton href="/goToProductPage/${currentBooking.product.productId}">Go to<br> product page</a>
			</div>
		<br>
		<br>
		<br>
		</div>
	</c:forEach>
	<hr>
	<br><br>
	<h2>Past bookings</h2>
	<br>
	<c:forEach items="${pastBookings}" var="pastBooking">
		<div class="product-card">	
			<a href="/goToProductPage/${currentBooking.product.productId}">
				<img class="cart-product-image" src=${pastBooking.product.mainProductImagePath }/>
			</a>
			<div class="cart-product-text">
				<b><p>product: ${pastBooking.product.name}</p></b><br>
				start date: ${pastBooking.startDate}<br><br>
				end date: ${pastBooking.endDate}<br><br>
			</div>
			<div class="product-buttons">
				<a class=violetButton href="/goToProductPage/${pastBooking.product.productId}">Go to<br> product page</a>
			</div>
			
		<br>
		<br>
		</div>
	</c:forEach>
		
	<br><br><a class="link" href="/">Go to Homepage</a>
<br><br><br><br>
</div>
	<jsp:include page="footer.jsp" />

</body>
</html>