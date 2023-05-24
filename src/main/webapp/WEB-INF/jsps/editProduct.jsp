<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit product</title>
<c:url value="/css/style3.css" var="jstlCss3" />
<link href="${jstlCss3}" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" /><br>
	<br>
<div class="in-center">
	<h2>Edit Product</h2>

	<form class="description" action="/editProduct" method="post" enctype="multipart/form-data">
		<input type="hidden" name="productId" value="${product.productId}"/>
		<input type="hidden" name="user" value="${user.username}">
		Name: <input type="text" name="name" value="${product.name}" /><br>
		Product description: <input type="text" name="description"
			value="${product.description}" /><br> Pickup Time: <input
			type="text" name="pickupTime" value="${product.pickupTime}" /><br>
		Price/Day: <input type="number" name="price" step="0.01"
			value="${product.priceForDay}" /><br> Max days to rent: <input
			type="number" name="maxDays" step="1" value="${product.maxNumberOfDays}" /><br>
		Product Color: <select name="color">
			<option value="${product.color}">${product.color}</option>
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
		</select> <br>
		Product Type: <select name="type">
			<option value="${product.type}">${product.type}</option>
			<option value="For all">For all</option>
			<option value="For kids">For kids</option>
			<option value="For adults">For adults</option>
		</select> <br>
		Product Category: <select name="category">
			<option value="${product.category}">${product.category}</option>
			<option value="Vehicles">Vehicles</option>
			<option value="Electronics">Electronics</option>
			<option value="House & Garden">House & Garden</option>
			<option value="fashion">Fashion</option>
			<option value="Sport & Hobby">Sport & Hobby</option>
			<option value="Health & Beauty">Health & Beauty</option>
			</select><br>
			<div class="productPictures">
				<p>Select product pictures to upload:</p>
				<div>
					<p>
						<label>Main Product Picture:</label>
					</p>
					<p>
						<input type="file" name="main" accept="image/png, image/jpeg" />
					</p>
				</div>
				<div>
					<p>
						<label>Additional Product Picture #1:</label>
					</p>
					<p>
						<input type="file" name="additionalImage"
							accept="image/png, image/jpeg" />
					</p>
				</div>
				<div>
					<p>
						<label>Additional Product Picture #2:</label>
					</p>
					<p>
						<input type="file" name="additionalImage"
							accept="image/png, image/jpeg" />
					</p>
				</div>
				<div>
					<p>
						<label>Additional Product Picture #3:</label>
					</p>
					<p>
						<input type="file" name="additionalImage"
							accept="image/png, image/jpeg" />
					</p>
				</div>
				<div>
					<p>
						<label>Additional Product Picture #4:</label>
					</p>
					<p>
						<input type="file" name="additionalImage"
							accept="image/png, image/jpeg" />
					</p>
				</div>
				<div>
					<p>
						<label>Additional Product Picture #5:</label>
					</p>
					<p>
						<input type="file" name="additionalImage"
							accept="image/png, image/jpeg" />
					</p>
				</div>
				<div>
					<p>
						<label>Additional Product Picture #6:</label>
					</p>
					<p>
						<input type="file" name="additionalImage"
							accept="image/png, image/jpeg" />
					</p>
				</div>
				<div>
					<p>
						<label>Additional Product Picture #7:</label>
					</p>
					<p>
						<input type="file" name="additionalImage"
							accept="image/png, image/jpeg" />
					</p>
				</div>
				<div>
					<p>
						<label>Additional Product Picture #8:</label>
					</p>
					<p>
						<input type="file" name="additionalImage"
							accept="image/png, image/jpeg" />
					</p>
				</div>
				<div>
					<p>
						<label>Additional Product Picture #9:</label>
					</p>
					<p>
						<input type="file" name="additionalImage"
							accept="image/png, image/jpeg" />
					</p>
				</div>
			</div>

		<br> <input type="submit" value="Save Changes" />
	</form>
	
</div>

	<br>
	<jsp:include page="footer.jsp" />
</body>
</html>