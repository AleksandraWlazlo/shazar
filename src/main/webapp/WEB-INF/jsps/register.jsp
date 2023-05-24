<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>

<c:url value="/css/style3.css" var="jstlCss3" />
<link href="${jstlCss3}" rel="stylesheet">

</head>
<body>

	<jsp:include page="header.jsp" /><br>
	
	<div style="text-align: right;">
		<a href="${pageContext.request.contextPath}/goRegisterPage?lang=en">English</a>
		<a href="${pageContext.request.contextPath}/goRegisterPage?lang=de">German</a>
		<a href="${pageContext.request.contextPath}/goRegisterPage?lang=pl">Polish</a>		
	</div>
		
	<spring:message code="label.username" var="labelUsername" />
	<spring:message code="label.password" var="labelPassword" />
	<spring:message code="label.confirmPassword" var="labelConfirmPassword" />
	<spring:message code="label.firstName" var="labelFirstName" />
	<spring:message code="label.lastName" var="labelLastName" />
	<spring:message code="label.phoneNumber" var="labelPhoneNumber" />
	<spring:message code="label.email" var="labelEmail" />
	
	<spring:message code="label.street" var="labelStreet" />
	<spring:message code="label.houseNumber" var="labelHouseNumber" />
	<spring:message code="label.postalCode" var="labelPostalCode" />
	<spring:message code="label.city" var="labelCity" />
	<spring:message code="label.country" var="labelCountry" />
	<spring:message code="label.questionFavouriteCity" var="labelQuestionFavouriteCity" />

	<spring:message code="label.register" var="labelRegister" />
	
	
<div class="in-center">
	<h3><spring:message code="label.registerHere" /></h3>
	
	<img src="pictures/user.png" class="user-pic"/>
	<br>
		
	<span style="color: red">${errorMessage}</span>
	<br>
	<form action="/register" method="post">
		<input class="form-field" type="text" name="username" placeholder="${labelUsername}" required /><br>
		<input class="form-field" type="password" name="password" placeholder="${labelPassword}" required /><br>
		<input class="form-field" type="password" name="confirmPassword" placeholder="${labelConfirmPassword}" /><br>
		<input class="form-field" type="text" name="firstName" placeholder="${labelFirstName}" required /><br>
		<input class="form-field" type="text" name="lastName" placeholder="${labelLastName}" required /><br>
		<input class="form-field" type="number" name="phoneNumber" placeholder="${labelPhoneNumber}" required /><br>
		<input class="form-field" type="email" name="email" placeholder="${labelEmail}" required /><br> 
		<br>Adress for product pickups<br>
		<input class="form-field" type="text" name="street" placeholder="${labelStreet}" required /><br>
		<input class="form-field" type="text" name="houseNumber" placeholder="${labelHouseNumber}" required /><br>
		<input class="form-field" type="number" name="postalCode" placeholder="${labelPostalCode}" required /><br>
		 <input class="form-field" type="text" name="city" placeholder="${labelCity}" required /><br> 
		 <input class="form-field"	type="text" name="country" placeholder="${labelCountry}" required /><br><br><br> 
		<spring:message code="label.questionForResettingPassword" /><br> 
		<br> 
		<input class="form-field" type="text" name="answerQuestion" placeholder="${labelQuestionFavouriteCity}" required /><br> 
		<input class="form-field submit-button2" type="submit" value="${labelRegister}">
	</form>
	<br>
	<br>
</div>

	<jsp:include page="footer.jsp" />

</body>
</html>