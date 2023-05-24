<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FAQ</title>
<c:url value="/css/style2.css" var="jstlCss2" />
<link href="${jstlCss2}" rel="stylesheet">
<c:url value="/css/style3.css" var="jstlCss3" />
<link href="${jstlCss3}" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" /><br>
	<br>
	<p class=privacy style="color:red; font-weight:bold;">${message}</p> 

	<h2 class="gray-txt">Frequently asked questions</h2>

	<div class="privacy">
		<div>
			<p class="bold-txt">I want a product outside my local region.
				What can I do?</p>
			<p>At Shazar, we understand that you may want to borrow or lend a
				product that is located outside of your local area. However, due to
				the logistics and shipping costs involved, we currently do not
				support the shipping of products outside of a local region. If
				you're interested in borrowing a product that is located outside of
				your local region, we suggest reaching out to the lender directly to
				see if they are willing to make arrangements for shipping the
				product to you. In such cases, the borrower would be responsible for
				any shipping costs, taxes, and other fees.</p>
			<br>

			<p class="bold-txt">The owner of the product isn't responding.
				What can I do?</p>
			<p>
				If you have been trying to reach the owner of a product you're
				interested in borrowing or lending and have not received a response,
				we recommend trying to contact them again. It's possible that they
				may have missed your previous message or are currently unavailable.<br>

				If you still haven't received a response after a reasonable amount
				of time, you may want to consider searching for a similar product on
				the Shazar website or reaching out to other owners who may be able
				to assist you.<br> If you are unable to resolve the issue with
				the owner and wish to report the problem to Shazar, you may submit a
				support request through our website. Our support team will review
				the situation and do their best to assist you in finding a
				resolution.<br> <br>
			</p>

			<p class="bold-txt">I want to offer a product. How can I register
				as a lendor?</p>
			<p>Lorem Ipsum is simply dummy text of the printing and
				typesetting industry. Lorem Ipsum has been the industry's standard
				dummy text ever since the 1500s, when an unknown printer took a
				galley of type and scrambled it to make a type specimen book. It has
				survived not only five centuries, but also the leap into electronic
				typesetting, remaining essentially unchanged. It was popularised in
				the 1960s with the release of Letraset sheets containing Lorem Ipsum
				passages, and more recently with desktop publishing software like
				Aldus PageMaker including versions of Lorem Ipsum.</p>
			<br>
		</div>
		<br>
		<div>
			<p class="gray-txt bold-txt">Your question is not in the
				overview? Add it below:</p>
			<form action="/sendMessageToAdmin" method="post">
				<input type="hidden" name="username" value="${user.username}">
				<label>E-mail: </label><input type="email" name="email"
					placeholder="Enter your e-mail address"><br> <label>Question:
				</label><input type="text" name="question" placeholder="Enter your question"><br>
				<input type="submit" value="Send question">
			</form>
		</div>
	</div>
	<br>
	<br>
	<img src="pictures/logo2.PNG" class="logo2" />



	<br>
	<br>
	<jsp:include page="footer.jsp" />
</body>
</html>