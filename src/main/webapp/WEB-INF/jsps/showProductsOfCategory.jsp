<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show products of category</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	
<div class="in-center">
		
	<h2>${category}</h2>
	
	<div class="products">
	<c:forEach items="${products}" var="product">
		<div id="col${status.index % 3 + 1}">
		<a class=products1 href="/goToProductPage/${product.productId}"><br>
			<img src=${product.mainProductImagePath }/>
			<b>${product.name}</b><br><br>
			Rating: ${product.rating}<br>
			<b style="color:purple">${product.priceForDay}</b>/day<br><br>
			
			<a class=violetButton href="/goToProductPage/${product.productId}">See Details</a>
			
		<br>
		</a>
		</div>
	</c:forEach>
	</div>
	
			
	<a href="/">Go to Homepage</a>
	<br><br><br>
	
</div>


	<jsp:include page="footer.jsp" />
</body>
</html>