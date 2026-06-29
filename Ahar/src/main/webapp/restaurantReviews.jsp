<%@page import="com.tap.model.Restaurant"%>
<%@page import="java.util.List"%>
<%@page import="com.tap.model.Review"%>

<%
List<Review> reviews = (List<Review>) request.getAttribute("reviews");

Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");

Double averageRating = (Double) request.getAttribute("averageRating");

if (averageRating == null) {
	averageRating = 0.0;
}
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Restaurant Reviews</title>

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Segoe UI', sans-serif;
}

body {
	background: #f8f8f8;
}

.header {
	background: #ff5200;
	color: white;
	padding: 25px;
	text-align: center;
}

.header h1 {
	font-size: 34px;
}

.summary {
	width: 75%;
	margin: 25px auto;
	background: white;
	padding: 30px;
	border-radius: 15px;
	text-align: center;
	box-shadow: 0 2px 10px rgba(0, 0, 0, .08);
}

.summary h2 {
	font-size: 34px;
	color: #222;
	margin-bottom: 10px;
}

.summary p {
	color: #666;
	margin-bottom: 20px;
}

.avg-rating {
	display: inline-block;
	background: #16a34a;
	color: white;
	padding: 10px 20px;
	border-radius: 10px;
	font-size: 22px;
	font-weight: bold;
}

.count {
	margin-top: 12px;
	color: #777;
	font-size: 16px;
}

.container {
	width: 75%;
	margin: 0 auto 40px auto;
}

.review-card {
	background: white;
	padding: 25px;
	border-radius: 15px;
	margin-bottom: 20px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, .08);
	transition: 0.3s;
}

.review-card:hover {
	transform: translateY(-3px);
}

.review-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.user-info {
	font-size: 18px;
	font-weight: 600;
	color: #222;
}

.rating {
	background: #16a34a;
	color: white;
	padding: 8px 15px;
	border-radius: 8px;
	font-weight: bold;
}

.review-text {
	margin-top: 15px;
	font-size: 16px;
	line-height: 24px;
	color: #444;
}

.date {
	margin-top: 12px;
	color: #888;
	font-size: 13px;
}

.home-btn {
	display: block;
	width: 220px;
	margin: 30px auto;
	text-align: center;
	text-decoration: none;
	background: #ff5200;
	color: white;
	padding: 14px;
	border-radius: 10px;
	font-weight: bold;
}

.home-btn:hover {
	background: #e64a00;
}
</style>

</head>

<body>

	<div class="header">
		<h1>Customer Reviews</h1>
	</div>

	<div class="summary">

	
		<h2><%=restaurant.getName()%></h2>

		<p>
			<%=restaurant.getCuisineType()%>
		</p>

		<div class="avg-rating">
			Rating
			<%=String.format("%.1f", averageRating)%>/5
		</div>

		<div class="count">
			Total Reviews :
			<%=reviews.size()%>
		</div>
	

	</div>

	<div class="container">

		<%
		if (reviews != null && !reviews.isEmpty()) {

			for (Review review : reviews) {
		%>

		<div class="review-card">

			
			<div class="review-header">

				<div class="user-info">
					<%=review.getUserName()%>
				</div>

				<div class="rating">
					<%=review.getRating()%>/5
				</div>

			</div>

			<div class="review-text">
				<%=review.getReviewText()%>
			</div>

			<div class="date">
				<%=review.getReviewDate()%>
			</div>

		</div>

		<%
		}
		} else {
		%>

		<div class="review-card">
			<h3>No Reviews Yet</h3>
			<p>Be the first customer to review this restaurant.</p>
		</div>

		<%
		}
		%>

		<a href="home" class="home-btn"> Back To Home </a>

	</div>

</body>
</html>
