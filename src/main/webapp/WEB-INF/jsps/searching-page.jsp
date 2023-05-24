<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Searching page</title>
 <c:url value="/css/style4.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet"> 

<style>
.more-filters {
	display: flex;
}

.form-product-filter {
	padding-left: 20px
}
</style>

</head>
<body>
	<jsp:include page="header.jsp" />
	<br>
	<br>
	<jsp:include page="searchbar.jsp" />

	<div>
		<h2>More filters:</h2>

		<div class="filters-container">
			<form class="form-product-filter" action="/filtered" method="post">

				<input type="hidden" name="searchedPhrase" value="${searchedPhrase }"> 
				<input type="hidden" name="startDate" value="${startDate }"> 
				<input type="hidden" name="endDate" value="${endDate }"> 
				<input type="hidden" name="city" value="${city }">

				<div class="filter-item">
					<span class="text-filters"> Price:</span> <span style="color: red">${errorMessage}</span> 
					<input class = "price" type="number" value="${filters.minPrice }" name="minPrice" min=0.0 placeholder="Min price" /> 
					<input class = "price" type="number" value="${filters.maxPrice }" name="maxPrice" placeholder="Max price" />
				</div>
				<div class="filter-item">
				<span class="text-filters">Color:</span>
				<select class="select-class" name="color">
					<option value="${filters.color }" selected="selected">${filters.color }</option>
					<option value="red">red</option>
					<option value="blue">blue</option>
					<option value="white">white</option>
					<option value="black">black</option>
					<option value="yellow">yellow</option>
					<option value="brown">brown</option>
					<option value="green">green</option>
					<option value="purple">purple</option>
					<option value="orange">orange</option>
					<option value="gray">gray</option>
					<option value="pink">pink</option>
					<option value="No filter">No filter</option>
				</select>
				</div>
				<div class="filter-item">
				<span class="text-filters">Type:</span>
				<select class="select-class" name="type">
					<option value="${filters.type }" selected="selected">${filters.type }</option>
					<option value="For adults">For adults</option>
					<option value="For kids">For kids</option>
					<option value="No filter">No filter</option>

				</select>
				</div>
			<div class="filter-item">
				<span class="text-filters">Category:</span>
				<select class="select-class" name="category">
					<option value="${filters.category }" selected="selected">${filters.category }</option>

					<option value="Vehicles">Vehicles</option>
					<option value="Electronics">Electronics</option>
					<option value="House & Garden">House & Garden</option>
					<option value="fashion">Fashion</option>
					<option value="Sport & Hobby">Sport & Hobby</option>
					<option value="Health & Beauty">Health & Beauty</option>
					<option value="No filter">No filter</option>
					</select>
				 <input class="submit-filters" type="submit" value="Filter" />
				</div>
			</form>
		</div>
	</div>
	<br>
	<br>

	<div class="products">

		<c:forEach items="${foundProducts}" var="product" varStatus="status">

			<div id="col${status.index % 3 + 1}">
				<a class=products1 href="/goToProductPage/${product.productId}">
					<img src=${product.mainProductImagePath } /> <br>
				<br> <b>${product.name}</b> <br>
				<br> Rating: ${product.rating} <br> <b><span
						style="color: purple">${product.priceForDay} $</span></b> /day <br>
				<br> <a class=violetButton
					href="/goToProductPage/${product.productId}">See Details</a> <br>
				<br>
				</a>
			</div>

		</c:forEach>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>