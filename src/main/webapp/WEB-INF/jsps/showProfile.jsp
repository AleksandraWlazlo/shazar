<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
<c:url value="/css/style2.css" var="jstlCss2" />
<link href="${jstlCss2}" rel="stylesheet">
<c:url value="/css/style5.css" var="jstlCss5" />
<link href="${jstlCss5}" rel="stylesheet">


</head>
<body>

	<jsp:include page="header.jsp" /><br>
	<br>
	
	
<div class="in-center">
<h2>Profile ${user.username}</h2>
<img  src="/pictures/user.png" class="user-pic"/>
	<div class="users">
		<div class="div1">
			
			<b>Username:</b> ${user.username}<br><br> 
			<b>Rating:</b> ${user.rating} <br><br>
			<b>First Name:</b> ${user.firstName}<br><br> 
			<b>Last Name:</b> ${user.lastName}<br><br>
			<b>Phone Number:</b> ${user.phoneNumber}<br><br> 
			<b>Email:</b> ${user.email}<br><br><br>
			<b>Address:</b> <br><br> 
			<b>Street:</b> ${user.address.street}<br><br> 
			<b>House Number:</b> ${user.address.houseNumber}<br><br> 
			<b>Postal Code:</b> ${user.address.postalCode} <br><br> 
			<b>City:</b> ${user.address.city} <br><br>
			<b>Country:</b> ${user.address.country} <br> <br>
		</div>
		<div class="div1">
			<a class="button" href="/goEditProfilePage">Edit Profile</a> <br> <br> <br> 
			<a class="button" href="/goChangePasswordPage">Change Password</a> <br><br> <br> 
			<a class="button" href="/goShowBookingsOfUser/${user.username}">Show my bookings</a><br><br><br>  
			<a class="button" href="/goShowProductsOfUser/${user.username}">Show my products</a> <br><br><br>  
			<a class="button" href="/goShowBookingsOfProductsOfUser/${user.username}">Show bookings of my products</a><br><br><br> 
		</div>
	</div>

	<br><br><a class="link" href="/">Go to Homepage</a>
	<br><br><br><br>
</div>
	<jsp:include page="footer.jsp" />
</body>
</html>