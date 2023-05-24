<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product ${ product.name}</title>
<c:url value="/css/style4.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">
</head>
<body>

	<jsp:include page="header.jsp" /><br>
	<br>
	<div class="main-container">
		<div class="product-container">
			<h1 class="title2">${ product.name }</h1>
			<h4 class="category-text"><a href="/goToCategory/vehicles">${ product.category }</a></h4>
			<div class="product-image-container">
			<div class="details rating">Rating: ${ product.rating }</div>
			<img class="main-photo" src=${product.mainProductImagePath } />
			</div>
			<div class="details-container">
				<h1 class="desc">Details</h1>
				<div class="details-container2">

					<div class="another-container">
						<div class="details">Pick Up Time:</div>
						<div class="details">Pick Up Adress:</div>
						<div class="details">Max number of days to rent:</div>
						<div class="details">Color:</div>
						<div class="details">Type:</div>
					</div>
				<div class="another-container">
				<div class="details">${ product.pickupTime }</div>
				<div class="details"> ${ product.pickupAdress.city }</div>
				<div class="details"> ${ product.maxNumberOfDays }</div>
				<div class="details"> ${ product.color }</div>
				<div class="details"> ${ product.type }</div>
				</div>
			</div>
			</div>
			<div class="description-container">
			<h1 class="desc">Description</h1>
			<p class="description">${ product.description }</p>
			</div>
		</div>
		

		<!--Side menu - recents------------------------------------------------------------>

		<div class="side-menu-container">
			<h2>Owner information</h2>
			<div class="owner"><a href="/showOtherUserProfile/${product.owner.username}">Owner ${product.owner.username} </a></div>
			<div class="recomends">Recommends: ${product.owner.rating}</div>
			<h3 class="price2">${ product.priceForDay } $/day</h3>
			
		
			<c:choose>
				<c:when
					test="${loggedIn == true && user.username.equals(product.owner.username)}">
					<form action="/goToEditProduct/${product.productId}" method="get">
						<input class="ask-button2" type="submit" name="message" value="Edit product" />
						</form>

					<a class="home" href="/">Go to Homepage</a>
				</c:when>
				
				
				
				<c:otherwise>
							<div class="container">
				<form action="/writeMessage" method="get">
						<input type="hidden" name="userUsername" value="${user.username}" />
						<input type="hidden" name="receiverUsername" value="${product.owner.username}" />
						<input class="ask-button" type="submit" name="message" value="Ask owner" />
						</form>
						<form action="/goShowProductsOfUser/${product.owner.username}" method="get">
						<input class="ask-button" type="submit" name="message" value="Other offers" />
						</form>
			</div>
					<div class="center">
						<form action="/addBooking" method="post">
							<span style="color: red">${errorMessage}</span> <span
								style="color: red">${errorNoDate}</span> <span
								style="color: red">${errorDate}</span><br> <input
								type="hidden" name="user" value="${user.username}"> <input
								type="hidden" name="productId" value="${product.productId}" />
				
							<input class="input" type="date" name="startDate" placeholder="Start date" /> 
							 <input class="input" type="date" name="endDate" placeholder="End date" /> 
							 <input class="ask-button2" type="submit" name="book" value="Book" /> 
							 <input class="ask-button2" type="submit" name="addToCart" value="Add to Cart" formaction="/addToCart" />
						</form>
					</div>
					
				</c:otherwise>
			</c:choose>
		</div>
		</div>
	<br>
	<jsp:include page="footer.jsp" />
</body>
</html>