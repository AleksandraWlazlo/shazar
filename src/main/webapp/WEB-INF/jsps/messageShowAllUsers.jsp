<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Messages</title>
<c:url value="/css/style2.css" var="jstlCss2" />
<link href="${jstlCss2}" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" /><br>
	
	<div class="in-center">
	
	<h2>Users</h2>	
	
	<div class="users-message">
		<c:forEach items="${allUsers}" var="userFromList" varStatus="status">	
			<div id="col${status.index % 3 + 1}">
				<form action="/writeMessage">
					${userFromList.username}
					<input type="hidden" name=receiverUsername value="${userFromList.username}" />
					<input type="submit" class="submit-button-messages" value="Send Message"/>
				</form>
			</div>
		</c:forEach>
	</div>
	
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