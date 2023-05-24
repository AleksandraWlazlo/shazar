<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Write Message</title>
<c:url value="/css/style2.css" var="jstlCss2" />
<link href="${jstlCss2}" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" /><br>
	
	<div class="in-center">

		<h2>Write a message</h2>
		<form action="/sendMessage" method="post">
			<input type="hidden" name="username" value="${user.username}"/>
			<input type="hidden" name="receiverUsername" value="${receiver.username}"/>
			<textarea name="messageText" rows="10" cols="130"></textarea><br><br>
			<input type="submit" class="submit-button-messages" value="Send Message"/>
		</form>	
		<br><br>
		<div class="links-in-messages">
			<a href="/goToInbox">Inbox</a>
			<a href="/goSentMessages">Sent Messages</a>
			<a href="/">Go to Homepage</a>
		</div>
		<br>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>