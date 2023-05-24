<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<c:url value="/css/style.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">
<c:url value="/pictures/logo.png" var="logo" />

</head>
<body>
	<header>
		<div class="navbar">
			<!-- navigation bar with basic functions -->
			<div>
				<a href="/"><img src="${logo}" class="logo"/></a>
			</div>

			<div class="functions">


				<c:choose>
					<c:when test="${loggedIn == true}">
					
						<a class="italic"><spring:message code="label.loggedInAs"/> ${user.username}</a>
						<a href="/logout"><spring:message code="label.logout"/></a>
						<!-------------- SEE PROFILE------------------------- -->
						<a href="/showProfile"><svg xmlns="http://www.w3.org/2000/svg"
								width="25" height="25" fill="currentColor"
								class="bi bi-person-circle" viewBox="0 0 16 16">
  						<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
 						 <path fill-rule="evenodd"
									d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
						</svg></a>


						<!-------------- INBOX-------------------------- -->
						<a href="/goToInbox"><svg xmlns="http://www.w3.org/2000/svg"
								width="25" height="25" fill="currentColor"
								class="bi bi-envelope" viewBox="0 0 16 16">
						  <path
									d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2Zm13 2.383-4.708 2.825L15 11.105V5.383Zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741ZM1 11.105l4.708-2.897L1 5.383v5.722Z" />
						</svg></a>
						<a href="/goToAddProduct"><spring:message code="label.addProduct"/></a>
					</c:when>
					<c:otherwise>
						<a href="/goRegisterPage"><spring:message code="label.register"/></a>
						<br>
						<a href="/login"><spring:message code="label.login"/></a>
					</c:otherwise>
				</c:choose>

				<!-------------- CART-------------------------- -->
				<a href="/goToCart"> <svg xmlns="http://www.w3.org/2000/svg"
						width="25" height="25" fill="currentColor" class="bi bi-cart"
						viewBox="0 0 16 16">
 				 <path
							d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
				</svg></a>

				<!-------------- FAQ-------------------------- -->
				<a href="/goToFAQ"><svg xmlns="http://www.w3.org/2000/svg"
						width="25" height="25" fill="currentColor"
						class="bi bi-question-circle" viewBox="0 0 16 16">
  				<path
							d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
  				<path
							d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z" />
				</svg></a>
			</div>
		</div>


		<!-- menu bar with categories -->
		<div class="menu-categories">
			<a href="/goToCategory/vehicles"><spring:message code="label.vehicles"/></a> 
			<a href="/goToCategory/electronics"><spring:message code="label.electronics"/></a> 
			<a href="/goToCategory/house_&_garden"><spring:message code="label.houseAndGarden"/></a> 
			<a href="/goToCategory/fashion"><spring:message code="label.fashion"/></a> 
			<a href="/goToCategory/sport_&_hobby"><spring:message code="label.sportAndHobby"/></a> 
			<a href="/goToCategory/health_&_beauty"><spring:message code="label.healthAndBeauty"/></a>
		</div>
	</header>
</body>
</html>