<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
	<br>



	
	<div class="center">
	<span style="color: red">${errorDate}</span><br>
	<span style="color: red">${errorNoDate}</span><br>
	<span style="color: red">${errorNothingToSearch}</span><br><br>
	</div>
	${test}
	<div class="center">
		<form action="/goToSearchingPage" method="get">
			<input class="search-input" type="text" name="productName" placeholder="Search a product" value = "${searchedPhrase}">
			<input class="date-input" type="date" name="startDate" placeholder="Start date" value = "${startDate}">
			<input class="date-input" type="date" name="endDate" data-placeholder="End date" value = "${endDate}">
			<input class="location-input" type="text" name="city" placeholder="Location" value = "${city}">
			<input class="submit-button" type="submit" value="Search">
		</form>
	</div>

	<br>


</body>
</html>