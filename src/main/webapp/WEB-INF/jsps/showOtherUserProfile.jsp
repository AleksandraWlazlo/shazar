<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<c:url value="/css/style2.css" var="jstlCss2" />
<link href="${jstlCss2}" rel="stylesheet">
<c:url value="/css/style5.css" var="jstlCss5" />
<link href="${jstlCss5}" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" /><br>
	<br>
<div class="in-center">
<h2>Profile of ${viewUser.username}</h2>
<img  src="/pictures/user.png" class="user-pic"/>
	<div class="users">
		<div class="div1">
			
			<b>Username:</b> ${viewUser.username}<br><br>
			<b>Rating:</b> ${viewUser.rating} <br><br>
			<b>First Name:</b> ${viewUser.firstName}<br><br>
			<b>Last Name:</b> ${viewUser.lastName}<br><br>
			<b>Phone Number:</b> ${viewUser.phoneNumber}<br><br> 
			<b>Email:</b> ${viewUser.email}<br><br>
			<b>Address:</b> <br><br> 
			<b>Street:</b> ${viewUser.address.street}<br><br> 
			<b>House Number:</b> ${viewUser.address.houseNumber}<br><br> 
			<b>Postal Code:</b> ${viewUser.address.postalCode} <br><br> 
			<b>City:</b> ${viewUser.address.city} <br><br>
			<b>Country:</b> ${viewUser.address.country} <br> <br>
		</div>
		
		<div>
		<form id="rating-form" action="/ratinguser/${viewUser.username}" style="">
			Rate this owner: <br><br>
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
		  <input class="violetButton" type="submit" name="btn" value="Submit Lendor Rating">
		</form><br><br>
			<div class="div1">
				<a class="button" href="/goShowProductsOfUser/${viewUser.username}">Show products of ${viewUser.username}</a><br> <br> <br> 
			</div>
		</div>
		

	</div>
	<br>
	<br>
	<a class="link" href="/">Go to Homepage</a>
	<br><br><br><br>
</div>
	<jsp:include page="footer.jsp" />
</body>
</html>