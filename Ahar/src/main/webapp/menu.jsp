<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tap.model.Menu"%>

<%
List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Ahar Menu</title>

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: Arial, sans-serif;
}

body {
	background: #f8f8f8;
}

/* HEADER */
.header {
	background: #ff5200;
	color: white;
	padding: 18px 40px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	position: sticky;
	top: 0;
	z-index: 1000;
}

.header h1 {
	font-size: 30px;
}

.header a {
	color: white;
	text-decoration: none;
	font-size: 18px;
	font-weight: bold;
}

/* RESTAURANT INFO */
.restaurant-banner {
	background: white;
	margin: 20px auto;
	width: 90%;
	padding: 25px;
	border-radius: 15px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, .1);
}

.restaurant-banner h2 {
	font-size: 32px;
	margin-bottom: 10px;
}

.restaurant-banner p {
	color: #666;
	font-size: 16px;
}

/* MENU GRID */
.container {
	width: 90%;
	margin: auto;
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
	gap: 20px;
	padding-bottom: 40px;
}

/* CARD */
.card {
	position: relative;
	background: white;
	border-radius: 15px;
	overflow: hidden;
	box-shadow: 0 2px 10px rgba(0, 0, 0, .15);
	transition: .3s;
}

.card:hover {
	transform: translateY(-5px);
}

.card img {
	width: 100%;
	height: 190px;
	object-fit: cover;
}

.offer-tag {
	position: absolute;
	top: 10px;
	left: 10px;
	background: #256fef;
	color: white;
	padding: 5px 10px;
	font-size: 12px;
	font-weight: bold;
	border-radius: 5px;
}

.image-rating {
	position: absolute;
	top: 10px;
	right: 10px;
	background: #1ba672;
	color: white;
	padding: 6px 12px;
	border-radius: 6px;
	font-weight: bold;
	font-size: 14px;
	z-index: 10;
}

.add-btn {
	background: white;
	color: #1ba672;
	border: 2px solid #1ba672;
	padding: 8px 20px;
	border-radius: 8px;
	font-weight: bold;
	cursor: pointer;
	transition: .3s;
}

.add-btn:hover {
	background: #1ba672;
	color: white;
}

.content {
	padding: 15px;
}

.content h3 {
	font-size: 20px;
	font-weight: 700;
	margin-bottom: 8px;
}

.desc {
	color: #666;
	font-size: 14px;
	line-height: 20px;
	margin-bottom: 10px;
}

.row {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin: 10px 0;
}

.price {
	font-size: 22px;
	font-weight: bold;
	color: #ff5200;
}

.rating {
	background: #1ba672;
	color: white;
	padding: 5px 10px;
	border-radius: 5px;
	font-weight: bold;
}

.status {
	margin-top: 10px;
	font-weight: bold;
}

.available {
	color: green;
}

.notavailable {
	color: red;
}

.floating-cart {
	position: fixed;
	bottom: 20px;
	right: 20px;
	background: #ff5200;
	color: white;
	text-decoration: none;
	padding: 15px 25px;
	border-radius: 30px;
	font-weight: bold;
	box-shadow: 0 3px 10px rgba(0, 0, 0, .25);
	z-index: 1000;
}

.empty {
	text-align: center;
	padding: 100px;
}

@media ( max-width :768px) {
	.container {
		grid-template-columns: 1fr;
	}
	.header {
		flex-direction: column;
		gap: 10px;
	}
}
</style>

</head>

<body>

	
	<div class="header">

		<h1>🍽 AHAR MENU</h1>

		<a href="home"> ← Back To Home </a>

	</div>

	<div class="restaurant-banner">

		<h2>Fresh & Delicious Food</h2>

		<p>Choose your favourite dishes and get them delivered fast.</p>

	</div>

	<div class="container">
		<%
		if (menuList != null && !menuList.isEmpty()) {

			for (Menu m : menuList) {
		%>

		<div class="card">

			<div class="offer-tag">50% OFF</div>

			<img src="<%=request.getContextPath()%>/<%=m.getImagePath()%>"
				alt="<%=m.getItemName()%>">

			<!-- Rating moved to image -->
			<div class="image-rating">
				⭐
				<%=m.getRating()%>
			</div>

			<div class="content">

				<div
					style="color: #ff5200; font-size: 13px; font-weight: bold; margin-bottom: 5px;">

					★ Bestseller</div>

				<div style="color: green; font-size: 16px; margin-bottom: 5px;">

					🟩</div>

				<h3>
					<%=m.getItemName()%>
				</h3>

				<p class="desc">
					<%=m.getDescription()%>
				</p>

				<div class="row">

					<div class="price">
						₹
						<%=m.getPrice()%>
					</div>

					<%
					if (m.isAvailable()) {
					%>

					<form action="cart" method="post">

						<input type="hidden" name="menuId" value="<%=m.getMenuId()%>">

						<input type="hidden" name="restaurantId"
							value="<%=m.getRestaurantId()%>">

						<button type="submit" class="add-btn">ADD</button>

					</form>

					<%
					}
					%>

				</div>

				<div class="status">

					<%
					if (m.isAvailable()) {
					%>

					<span class="available"> ✔ Available </span>

					<%
					} else {
					%>

					<span class="notavailable"> ✖ Not Available </span>

					<%
					}
					%>

				</div>

			</div>

		</div>

		<%
		}

		} else {
		%>

		<div class="empty">

			<h2>No Menu Items Available</h2>

		</div>

		<%
		}
		%>
	</div>

	<a href="cart" class="floating-cart"> 🛒 Cart (${cartCount}) </a>

</body>

</html>