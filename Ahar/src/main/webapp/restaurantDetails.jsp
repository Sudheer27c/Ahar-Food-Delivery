<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tap.model.Restaurant"%>
<%@ page import="com.tap.model.Review"%>

<%
Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");

List<Review> reviews = (List<Review>) request.getAttribute("reviews");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant Details</title>

<style>
body {
	font-family: Arial;
	background: #f5f5f5;
	margin: 0;
}

.header {
	background: #ff5200;
	color: white;
	padding: 20px;
}

.container {
	width: 90%;
	margin: 20px auto;
}

.card {
	background: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, .15);
	margin-bottom: 20px;
}

.review {
	background: white;
	padding: 15px;
	margin-bottom: 10px;
	border-radius: 10px;
}

h1 {
	color: #ff5200;
}

.back {
	display: inline-block;
	padding: 10px 15px;
	background: #ff5200;
	color: white;
	text-decoration: none;
	border-radius: 5px;
	margin-bottom: 20px;
}
</style>

</head>

<body>

	<div class="header">

		<h1>Restaurant Details</h1>

	</div>

	<div class="container">

		<a href="home" class="back"> Back To Home </a>

		<div class="card">

			<h2><%=restaurant.getName()%></h2>

			<p>
				<b>Cuisine :</b>
				<%=restaurant.getCuisineType()%>
			</p>

			<p>
				<b>Rating :</b> ⭐
				<%=restaurant.getRating()%>
			</p>

			<p>
				<b>Delivery Time :</b>
				<%=restaurant.getDeliveryTime()%>
				mins
			</p>

			<p>
				<b>Address :</b>
				<%=restaurant.getAddress()%>
			</p>

		</div>

		<h2>Customer Reviews</h2>

		<%
		if (reviews != null && !reviews.isEmpty()) {

			for (Review review : reviews) {
		%>

		<div class="review">

			<p>

				⭐ Rating : <b><%=review.getRating()%>/5</b>

			</p>

			<p>

				<%=review.getReviewText()%>

			</p>

			<p>

				<small> <%=review.getReviewDate()%>

				</small>

			</p>

		</div>

		<%
    }
}
else{
%>

		<div class="review">No Reviews Yet</div>

		<%
}
%>

	</div>

</body>
</html>