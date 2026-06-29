<%@page import="java.util.List"%>
<%@page import="com.tap.model.Restaurant"%>

<%
List<Restaurant> favourites = (List<Restaurant>) request.getAttribute("favouriteRestaurants");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Favorites - Ahar</title>

<style>
body {
	margin: 0;
	font-family: Arial, sans-serif;
	background: #f5f5f5;
}

.header {
	background: #ff5200;
	color: white;
	text-align: center;
	padding: 30px;
	font-size: 24px;
	font-weight: bold;
}

.container {
	width: 90%;
	margin: 30px auto;
}

.grid {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
	gap: 25px;
}

.card {
	background: white;
	border-radius: 15px;
	overflow: hidden;
	box-shadow: 0 4px 10px rgba(0, 0, 0, .1);
	transition: .3s;
}

.card:hover {
	transform: translateY(-5px);
}

.card img {
	width: 100%;
	height: 220px;
	object-fit: cover;
}

.card-body {
	padding: 18px;
}

.card-body h3 {
	font-size: 26px;
	color: #222;
	margin-bottom: 12px;
}

.rating {
	display: inline-block;
	background: #16a34a;
	color: white;
	padding: 6px 12px;
	border-radius: 6px;
	font-size: 14px;
	font-weight: bold;
	margin-bottom: 15px;
}

.card-body p {
	margin: 10px 0;
	color: #555;
	font-size: 16px;
	display: flex;
	align-items: center;
	gap: 10px;
}

.btn {
	display: block;
	text-align: center;
	background: #ff5200;
	color: white;
	text-decoration: none;
	padding: 12px;
	border-radius: 8px;
	margin-top: 15px;
	font-weight: bold;
}

.remove-btn {
	display: block;
	text-align: center;
	margin-top: 10px;
	background: #dc2626;
	color: white;
	text-decoration: none;
	padding: 12px;
	border-radius: 8px;
	font-weight: bold;
	transition: .3s;
}

.remove-btn:hover {
	background: #b91c1c;
}

.empty {
	text-align: center;
	background: white;
	padding: 100px;
	border-radius: 15px;
}
</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>

<body>

	<div class="header">
		<h1>My Favorite Restaurants</h1>
	</div>

	<div class="container">

		<%
		if (favourites != null && !favourites.isEmpty()) {
		%>

		<div class="grid">

			<%
			for (Restaurant r : favourites) {
			%>

			<div class="card">

				<img src="<%=request.getContextPath()%>/<%=r.getImagePath()%>"
					alt="<%=r.getName()%>">

				<div class="card-body">

					<h3><%=r.getName()%></h3>

					<span class="rating"> <i class="fa-solid fa-star"></i> <%=String.format("%.1f", r.getRating())%>
					</span>

					<p>
						<i class="fa-solid fa-utensils"></i>
						<%=r.getCuisineType()%>
					</p>

					<p>
						<i class="fa-solid fa-location-dot"></i>
						<%=r.getAddress()%>
					</p>

					<p>
						<i class="fa-solid fa-truck"></i>
						<%=r.getDeliveryTime()%>
						mins
					</p>

					<a href="menu?restaurantId=<%=r.getRestaurantId()%>" class="btn">
						View Menu </a> <a
						href="removeFavourite?restaurantId=<%=r.getRestaurantId()%>"
						class="remove-btn"> Remove Favorite </a>

				</div>
			</div>

			<%
			}
			%>

		</div>

		<%
		} else {
		%>

		<div class="empty">

			<h2>No Favorites Added Yet</h2>

			<br>

			<p>Add restaurants from Home Page</p>

			<br> <a href="home" class="btn"> Back To Home </a>

		</div>

		<%
		}
		%>

	</div>

</body>
</html>