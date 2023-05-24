<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact</title>
<c:url value="/css/style.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">

</head>
<body>
	<jsp:include page="header.jsp" /><br>

	<h2 class="gray-txt">Contact Us</h2>
<div class="privacy">
	At Shazar, we are dedicated to providing our customers with the best
	possible service. If you have any questions or concerns, our customer
	service team is here to help. We encourage you to reach out to us at
	any time, and we will do our best to assist you.
	<br>

	<p class="bold-txt">Contact Details:</p>
	<p>
		Phone:<br> Polish: +48 123 456 789<br> German: +49 123 456
		789<br> Email:<br> support@shazar.com<br> Address:<br> 123
		Main Street<br> Anytown, USA 12345
	<p>
		<br> Our customer service team is available from Monday to
		Friday, 9:00 AM to 5:00 PM CET.<br> If you have an urgent matter outside
		of these hours, please send us an email, and we will get back to you
		as soon as possible.<br> We value your feedback and appreciate
		the opportunity to assist you. Please do not hesitate to contact us if
		there is anything we can do to improve your experience with Shazar.
		Thank you for choosing us as your go-to platform for sharing and
		renting products!<br>
</div>
<br>
<br>

<img src="pictures/logo2.PNG" class="logo2" />

<br><br>
		<jsp:include page="footer.jsp" />
</body>
</html>