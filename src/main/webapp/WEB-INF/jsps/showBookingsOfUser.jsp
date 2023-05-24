<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Bookings</title>
<c:url value="/css/style5.css" var="jstlCss5" />
<link href="${jstlCss5}" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<jsp:include page="header.jsp" /><br>

<div class="in-center">
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
				<form id="rating-form" action="/rating/${pastBooking.product.productId}">
					Rate this product: <br>
					<span class="rating" style="display: flex; flex-direction: row-reverse;" >
						<input class="rating" type="radio" name="rating" value="5" id="rating-5" />
					    <label class="rating" for="rating-5" aria-label="Five"><i class="fa fa-star"></i></label>
						<input class="rating" type="radio" name="rating" value="4" id="rating-4" />
					    <label class="rating" for="rating-4" aria-label="Four"><i class="fa fa-star"></i></label>
					    <input class="rating" type="radio" name="rating" value="3" id="rating-3" />
					    <label class="rating" for="rating-3" aria-label="Three"><i class="fa fa-star"></i></label>
					    <input class="rating" type="radio" name="rating" value="2" id="rating-2" />
					    <label class="rating" for="rating-2" aria-label="Two"><i class="fa fa-star"></i></label>
						<input class="rating" type="radio" name="rating" value="1" id="rating-1" />
					    <label class="rating" for="rating-1" aria-label="One"><i class="fa fa-star"></i></label>
					 </span>
				    <br>
				  <input type="hidden" name="userName" value="${ user.username }">
				  <input class=violetButton type="submit" name="btn" value="Submit Product Rating">
				</form>
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