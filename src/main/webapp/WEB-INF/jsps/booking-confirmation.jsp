<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking-confirmation</title>
</head>
<body>
	<jsp:include page="header.jsp" /><br>
	<br>


	<h1 class="title3">Your request for <span style="color:purple">${ product.name }</span> has been sent</h1>>
	<h1 class="title3">We have informed <span style="color:purple">${ product.owner.username }</span> of your reservation, 
	you will receive a reply shortly</h1>

		<div class="product-card">
			<a href="/goToProductPage/${booking.product.productId}">
			<img class="cart-product-image" src=${booking.product.mainProductImagePath }/>
			</a>
			<div class="cart-product-text">
				<b><p>${booking.product.name}</p></b><br>
				start date: ${booking.startDate}<br><br>
				end date: ${booking.endDate}<br><br>
				Pickup Time: ${booking.product.pickupTime }<br><br>
				Pickup Adress: ${ booking.product.pickupAdress.street } ${ booking.product.pickupAdress.houseNumber }
					${ booking.product.pickupAdress.postalCode } ${ booking.product.pickupAdress.city }
					${ booking.product.pickupAdress.country }<br><br>
				

			</div>
			<div class="product-buttons">
				<a class=violetButton href="/goToProductPage/${booking.product.productId}">Go to<br> product page</a>
			</div>
		<br>
		<br>
		<br>
		</div>
	<br><br>
	<a href="/">Go to Homepage</a>
	
	<br>
	<jsp:include page="footer.jsp" />
</body>
</html>