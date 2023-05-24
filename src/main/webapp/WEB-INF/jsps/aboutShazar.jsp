<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>About Shazar</title>
<c:url value="/css/style.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">

</head>
<body>
	<jsp:include page="header.jsp" /><br>
	<br>
	<div class="txt-pic">
		<div class="padding-around privacy ">
			<h1>About Shazar</h1>

			<p>
				Shazar is a unique platform that allows people to rent and lend
				products they have. The idea behind Shazar is simple - share what
				you have, gain some extra income, and help the environment by
				reducing the need to buy new things.<br> The platform was
				founded by a group of 5 individuals - Aleksandra, Benamar, Mariela,
				Maciej, and Weronika - who came from different cities. During their
				visits to each other, they realized that they often needed to borrow
				things from people and had the idea that borrowing was a better
				option than buying, especially for short-term use.<br> With
				Shazar, you can rent anything from a camera for a weekend trip to a
				drill for a DIY project. The platform brings together individuals
				with similar needs, making it easy to find what you're looking for.
				Moreover, it's a sustainable solution that helps the environment by
				reducing waste and conserving resources. <br> <br> By
				joining the Shazar community, you can become a lender and earn money
				by renting out your unused items. You can also save money by renting
				items you need instead of buying them. Whether you're looking to
				declutter your home, earn some extra income, or save money, Shazar
				is the perfect solution.<br> So why not join the Shazar
				community today? Sign up, create your profile, and start sharing and
				renting products with people in your area. You'll be making a
				positive impact on the environment and supporting the circular
				economy, all while enjoying the benefits of sharing and renting. <br>
			</p>
			<h2 class="gray-txt">Shazar - Share what you have, gain what you
				need!</h2>
		</div>

		<div class="padding-around">

			<img src="pictures/about.JPG" />
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>