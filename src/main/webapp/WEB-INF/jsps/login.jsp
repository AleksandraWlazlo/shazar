<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<c:url value="/css/style3.css" var="jstlCss3" />
<link href="${jstlCss3}" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" /><br>
	
	<div style="text-align: right;">
		<a href="${pageContext.request.contextPath}/login?lang=en">English</a>
		<a href="${pageContext.request.contextPath}/login?lang=de">German</a>
		<a href="${pageContext.request.contextPath}/login?lang=pl">Polish</a>		
	</div>
	
	<spring:message code="label.username" var="labelUsername" />
	<spring:message code="label.password" var="labelPassword" />
	<spring:message code="label.login" var="labelLogin" />
	
	
<div class="in-center">
	<br><br>
	<div>
		<h3><spring:message code="label.login" />!</h3>	
	
	<img src="pictures/user.png" class="user-pic"/>
	
	<br>
		<span style="color: red">${errorMessage}</span>
		<br>
		<form action="/login" method="post">
			<div>
				<input class="form-field" type="text" name="username" placeholder="${labelUsername}" /><br>
				<input class="form-field" type="password" name="password" placeholder="${labelPassword}" /><br> 
				<input class="form-field submit-button2" type="submit" value="${labelLogin}"><br>
				<br>
			</div>
			<div >
				<a class="link" href="/goQuestionPassword"><spring:message code="label.resetPassword" /></a> <br>
				<a class="link" href="/goRegisterPage">	<spring:message code="label.registerHere" /></a> <br>
			</div>
		</form>
	</div>
	<br><br>	
</div>
	
	<jsp:include page="footer.jsp" />
</body>
</html>