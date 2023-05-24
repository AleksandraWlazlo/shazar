<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Send Message</title>
<c:url value="/css/style2.css" var="jstlCss2" />
<link href="${jstlCss2}" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" /><br>
	
	<div class="in-center">
	<h2>Sent Messages</h2>
	<table class="table-in-center">
		<tr>
			<th>To</th>
			<th>Message</th>
			<th>Time</th>
		</tr>
		<c:forEach items="${allSentMessages}" var="sentMessage">
			<tr>
				<td>${sentMessage.receiver.username}</td>
				<td>${sentMessage.messageText}</td>
				<td>${sentMessage.sendDate}</td>
			</tr>
		</c:forEach>
	</table>
	<br><br>
	<br>
	<div class="links-in-messages">
		<a href="/goToInbox">Inbox</a>
		<a href="/showUsers">Write a message</a>
		<a href="/">Go to Homepage</a>
	</div>
	<br>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>