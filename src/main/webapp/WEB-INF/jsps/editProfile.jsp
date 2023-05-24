<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
<c:url value="/css/style3.css" var="jstlCss3" />
<link href="${jstlCss3}" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" /><br><br>
<div class="in-center">	
	<h2>Edit Profile</h2>
	<form action="/editProfile" method="post">
		<input type="hidden" name="username" value="${user.username}"/>
		<input type="hidden" name="password" value="${user.password}"/>
		First Name:<br> <input type="text" name="firstName" value="${user.firstName}" required /><br>
		Last Name:<br> <input type="text" name="lastName" value="${user.lastName}" required /><br>
		Phone Number:<br> <input type="number" name="phoneNumber" value="${user.phoneNumber}" required /><br>
		Email:<br> <input type="text" name="email" value="${user.email}" required /><br><br>
		Address <br><br>
		Street:<br> <input type="text" name="street" value="${user.address.street}" required /><br> 
		House Number:<br> <input type="text" name="houseNumber" value="${user.address.houseNumber}" required /><br>
		Postal Code:<br> <input type="number" name="postalCode" value="${user.address.postalCode}" required /><br>
		City:<br> <input type="text" name="city" value="${user.address.city}" required /><br>
		Country:<br> <input type="text" name="country" value="${user.address.city}" required /><br> 
		<input type="submit"/>
	</form>
		<br><br><a class="link" href="/">Go to Homepage</a>
	<br><br><br><br>
</div>	
	<jsp:include page="footer.jsp" />
</body>
</html>