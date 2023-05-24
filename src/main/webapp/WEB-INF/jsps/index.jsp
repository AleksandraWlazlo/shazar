<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<!-- <link rel="styles" type="text/css" href="path/to/styles.css">  -->
<!--  <link rel='stylesheet' href='<c:url value="/resources/webapp/css/styles.css" />' /> -->

<style>

	
.title {
  font-size: 40px;
  font-weight: bold;
  color: #fff;
  text-shadow: 2px 2px 5px rgba(0,0,0,0.3);
}

.subtitle {
  font-size: 32px;
  font-weight: bold;
  color: #fff;
  text-shadow: 2px 2px 5px rgba(0,0,0,0.3);
} 

.text{
font-size: 22px;
}

.pic {
  background-image: url('pictures/backgroundPicture.jpg');
  background-size: cover;
  padding: 150px;
  color: #fff;
  text-shadow: 2px 2px 5px rgba(0,0,0,0.3);
  text-align: left;
  
  
  padding: 150px; background-size: 100% 100%;
}  



</style>



</head>
<body>
	<div class="font">

		<jsp:include page="header.jsp" />
	
		<div style="text-align: right;">
			<a href="${pageContext.request.contextPath}/?lang=en">English</a>
			<a href="${pageContext.request.contextPath}/?lang=de">German</a>
			<a href="${pageContext.request.contextPath}/?lang=pl">Polish</a>		
		</div>
		
		<div class="pic">
		
			<div class="title">SHAZAR: </div>
			<div class="subtitle">Rent and Lend!<br></div>
			<div class="text">Discover the convenience and savings of
				renting on Shazar,<br> where you can access a wide range of products<br>
				without the commitment of ownership.<br>
				
			</div>
		</div>
		
		<jsp:include page="searchbar.jsp" />
		
		<br></br>
		<hr>
		
		<h2><spring:message code="label.categories"/></h2> <br>
		
		
		<div class="categories">
			<div class="category">
				<a href="/goToCategory/vehicles"><spring:message code="label.vehicles"/></a> <br><br>
				<a href="/goToCategory/vehicles"> <svg xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="gray" class="bi bi-car-front-fill" viewBox="0 0 16 16">
  				<path d="M2.52 3.515A2.5 2.5 0 0 1 4.82 2h6.362c1 0 1.904.596 2.298 1.515l.792 1.848c.075.175.21.319.38.404.5.25.855.715.965 1.262l.335 1.679c.033.161.049.325.049.49v.413c0 .814-.39 1.543-1 1.997V13.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-1.338c-1.292.048-2.745.088-4 .088s-2.708-.04-4-.088V13.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-1.892c-.61-.454-1-1.183-1-1.997v-.413a2.5 2.5 0 0 1 .049-.49l.335-1.68c.11-.546.465-1.012.964-1.261a.807.807 0 0 0 .381-.404l.792-1.848ZM3 10a1 1 0 1 0 0-2 1 1 0 0 0 0 2Zm10 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2ZM6 8a1 1 0 0 0 0 2h4a1 1 0 1 0 0-2H6ZM2.906 5.189a.51.51 0 0 0 .497.731c.91-.073 3.35-.17 4.597-.17 1.247 0 3.688.097 4.597.17a.51.51 0 0 0 .497-.731l-.956-1.913A.5.5 0 0 0 11.691 3H4.309a.5.5 0 0 0-.447.276L2.906 5.19Z"/>
				</svg></a>
			</div>
			<div class="category">
				<a href="/goToCategory/electronics"><spring:message code="label.electronics"/></a> <br><br>
				<a href="/goToCategory/electronics">
				<svg xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="gray" class="bi bi-plugin" viewBox="0 0 16 16">
  				<path fill-rule="evenodd" d="M1 8a7 7 0 1 1 2.898 5.673c-.167-.121-.216-.406-.002-.62l1.8-1.8a3.5 3.5 0 0 0 4.572-.328l1.414-1.415a.5.5 0 0 0 0-.707l-.707-.707 1.559-1.563a.5.5 0 1 0-.708-.706l-1.559 1.562-1.414-1.414 1.56-1.562a.5.5 0 1 0-.707-.706l-1.56 1.56-.707-.706a.5.5 0 0 0-.707 0L5.318 5.975a3.5 3.5 0 0 0-.328 4.571l-1.8 1.8c-.58.58-.62 1.6.121 2.137A8 8 0 1 0 0 8a.5.5 0 0 0 1 0Z"/>
				</svg></a> 
				
			</div>
			<div class="category">
				<a href="/goToCategory/house_&_garden"><spring:message code="label.houseAndGarden"/></a> <br><br>
				<a href="/goToCategory/house_&_garden"><svg xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="gray" class="bi bi-house-door" viewBox="0 0 16 16">
  				<path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146ZM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5Z"/>
				</svg></a>
			</div>
			<div class="category">
				<a href="/goToCategory/fashion"><spring:message code="label.fashion"/></a><br><br>
				<a href="/goToCategory/fashion"><svg xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="gray" class="bi bi-sunglasses" viewBox="0 0 16 16">
  				<path d="M3 5a2 2 0 0 0-2 2v.5H.5a.5.5 0 0 0 0 1H1V9a2 2 0 0 0 2 2h1a3 3 0 0 0 3-3 1 1 0 1 1 2 0 3 3 0 0 0 3 3h1a2 2 0 0 0 2-2v-.5h.5a.5.5 0 0 0 0-1H15V7a2 2 0 0 0-2-2h-2a2 2 0 0 0-1.888 1.338A1.99 1.99 0 0 0 8 6a1.99 1.99 0 0 0-1.112.338A2 2 0 0 0 5 5H3zm0 1h.941c.264 0 .348.356.112.474l-.457.228a2 2 0 0 0-.894.894l-.228.457C2.356 8.289 2 8.205 2 7.94V7a1 1 0 0 1 1-1z"/>
				</svg></a><br>
			</div>
			<div class="category">
				<a href="/goToCategory/sport_&_hobby"><spring:message code="label.sportAndHobby"/></a><br><br>
				<a href="/goToCategory/sport_&_hobby"><svg xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="gray" class="bi bi-water" viewBox="0 0 16 16">
  				<path d="M.036 3.314a.5.5 0 0 1 .65-.278l1.757.703a1.5 1.5 0 0 0 1.114 0l1.014-.406a2.5 2.5 0 0 1 1.857 0l1.015.406a1.5 1.5 0 0 0 1.114 0l1.014-.406a2.5 2.5 0 0 1 1.857 0l1.015.406a1.5 1.5 0 0 0 1.114 0l1.757-.703a.5.5 0 1 1 .372.928l-1.758.703a2.5 2.5 0 0 1-1.857 0l-1.014-.406a1.5 1.5 0 0 0-1.114 0l-1.015.406a2.5 2.5 0 0 1-1.857 0l-1.014-.406a1.5 1.5 0 0 0-1.114 0l-1.015.406a2.5 2.5 0 0 1-1.857 0L.314 3.964a.5.5 0 0 1-.278-.65zm0 3a.5.5 0 0 1 .65-.278l1.757.703a1.5 1.5 0 0 0 1.114 0l1.014-.406a2.5 2.5 0 0 1 1.857 0l1.015.406a1.5 1.5 0 0 0 1.114 0l1.014-.406a2.5 2.5 0 0 1 1.857 0l1.015.406a1.5 1.5 0 0 0 1.114 0l1.757-.703a.5.5 0 1 1 .372.928l-1.758.703a2.5 2.5 0 0 1-1.857 0l-1.014-.406a1.5 1.5 0 0 0-1.114 0l-1.015.406a2.5 2.5 0 0 1-1.857 0l-1.014-.406a1.5 1.5 0 0 0-1.114 0l-1.015.406a2.5 2.5 0 0 1-1.857 0L.314 6.964a.5.5 0 0 1-.278-.65zm0 3a.5.5 0 0 1 .65-.278l1.757.703a1.5 1.5 0 0 0 1.114 0l1.014-.406a2.5 2.5 0 0 1 1.857 0l1.015.406a1.5 1.5 0 0 0 1.114 0l1.014-.406a2.5 2.5 0 0 1 1.857 0l1.015.406a1.5 1.5 0 0 0 1.114 0l1.757-.703a.5.5 0 1 1 .372.928l-1.758.703a2.5 2.5 0 0 1-1.857 0l-1.014-.406a1.5 1.5 0 0 0-1.114 0l-1.015.406a2.5 2.5 0 0 1-1.857 0l-1.014-.406a1.5 1.5 0 0 0-1.114 0l-1.015.406a2.5 2.5 0 0 1-1.857 0L.314 9.964a.5.5 0 0 1-.278-.65zm0 3a.5.5 0 0 1 .65-.278l1.757.703a1.5 1.5 0 0 0 1.114 0l1.014-.406a2.5 2.5 0 0 1 1.857 0l1.015.406a1.5 1.5 0 0 0 1.114 0l1.014-.406a2.5 2.5 0 0 1 1.857 0l1.015.406a1.5 1.5 0 0 0 1.114 0l1.757-.703a.5.5 0 1 1 .372.928l-1.758.703a2.5 2.5 0 0 1-1.857 0l-1.014-.406a1.5 1.5 0 0 0-1.114 0l-1.015.406a2.5 2.5 0 0 1-1.857 0l-1.014-.406a1.5 1.5 0 0 0-1.114 0l-1.015.406a2.5 2.5 0 0 1-1.857 0l-1.757-.703a.5.5 0 0 1-.278-.65z"/>
				</svg></a><br>
			</div>
			<div class="category">
				<a href="/goToCategory/health_&_beauty"><spring:message code="label.healthAndBeauty"/></a><br><br>
				<a href="/goToCategory/health_&_beauty"><svg xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="gray" class="bi bi-heart-pulse" viewBox="0 0 16 16">
  				<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053.918 3.995.78 5.323 1.508 7H.43c-2.128-5.697 4.165-8.83 7.394-5.857.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17c3.23-2.974 9.522.159 7.394 5.856h-1.078c.728-1.677.59-3.005.108-3.947C13.486.878 10.4.28 8.717 2.01L8 2.748ZM2.212 10h1.315C4.593 11.183 6.05 12.458 8 13.795c1.949-1.337 3.407-2.612 4.473-3.795h1.315c-1.265 1.566-3.14 3.25-5.788 5-2.648-1.75-4.523-3.434-5.788-5Z"/>
  				<path d="M10.464 3.314a.5.5 0 0 0-.945.049L7.921 8.956 6.464 5.314a.5.5 0 0 0-.88-.091L3.732 8H.5a.5.5 0 0 0 0 1H4a.5.5 0 0 0 .416-.223l1.473-2.209 1.647 4.118a.5.5 0 0 0 .945-.049l1.598-5.593 1.457 3.642A.5.5 0 0 0 12 9h3.5a.5.5 0 0 0 0-1h-3.162l-1.874-4.686Z"/>
				</svg></a>
			</div>
		</div>
	
		<br>
		<hr>

		<h2><spring:message code="label.topProducts"/></h2>

		<div class="products">

			<c:forEach items="${topProducts}" var="topProduct" varStatus="status">

				<div id="col${status.index % 3 + 1}">
					<a class=products1 href="/goToProductPage/${topProduct.productId}">
						<img src=${topProduct.mainProductImagePath }/>
						<br><br>
						<b>${topProduct.name}</b>
						<br><br>
						<spring:message code="label.rating"/>: ${topProduct.rating}
						<br> 
						<b><span style="color:purple">${topProduct.priceForDay} $</span></b> /day
						<br><br>
						<a class=violetButton href="/goToProductPage/${topProduct.productId}"><spring:message code="label.seeDetails"/></a>
						<br><br>
					</a>
				</div>

			</c:forEach>
		</div>


		<br><br>
		<jsp:include page="footer.jsp" />
</div>
		

</body>
</html>