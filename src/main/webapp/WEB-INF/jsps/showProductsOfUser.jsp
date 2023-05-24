<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Products</title>
</head>
<body>	
	<jsp:include page="header.jsp" /><br>
	

	<div class="in-center">
		<h2>Products of User ${user.username}</h2>
		<div class="products">
			<c:forEach items="${productsOfUser}" var="productOfUser">
				<div id="col${status.index % 3 + 1}">
				<a class=products1 href="/goToProductPage/${productOfUser.productId}"><br>
					<img src=${productOfUser.mainProductImagePath }/>
					<b>${productOfUser.name}</b><br><br>
					Rating: ${productOfUser.rating}<br>
					<b style="color:purple">${productOfUser.priceForDay}</b>/day<br><br>
					
					<a class=violetButton href="/goToProductPage/${productOfUser.productId}">See Details</a>
					
				<br>
				</a>
				</div>
			</c:forEach>
		</div>
		<a href="/">Go to Homepage</a>
	</div>
	
	<br><br>
	<jsp:include page="footer.jsp" />
</body>
</html>