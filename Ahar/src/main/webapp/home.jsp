<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tap.model.Restaurant"%>
<%@ page import="com.tap.model.User"%>

<%
List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("allRestaurants");

User loggedUser = (User) session.getAttribute("loggedInUser");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Ahar Food Delivery</title>

<link rel="icon"
	href="<%=request.getContextPath()%>/images/ahar-logo.png">

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: Arial, sans-serif;
}

body {
	background: #f5f5f5;
}

/* NAVBAR */
nav {
	background: #ff5200;
	min-height: 70px;
	padding: 0 30px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	position: sticky;
	top: 0;
	z-index: 1000;
}

.nav-search {
	display: flex;
	align-items: center;
	margin-left: 20px;
}

.nav-search input {
	width: 450px;
	height: 45px;
	border: none;
	outline: none;
	padding: 0 15px;
	border-radius: 25px 0 0 25px;
	font-size: 15px;
}

.nav-search button {
	width: 55px;
	height: 45px;
	border: none;
	background: white;
	cursor: pointer;
	border-radius: 0 25px 25px 0;
}

.left-section {
	display: flex;
	align-items: center;
	gap: 20px;
}

.logo {
	display: flex;
	align-items: center;
	gap: 10px;
	color: white;
	font-size: 28px;
	font-weight: bold;
}

.logo img {
	width: 50px;
	height: 50px;
	border-radius: 50%;
}

.right-section {
	display: flex;
	align-items: center;
	gap: 25px;
}

.welcome-user {
	color: white;
	font-size: 18px;
	font-weight: 700;
}

.nav-link {
	color: white;
	text-decoration: none;
	font-size: 18px;
	font-weight: 700;
}

.nav-link:hover {
	opacity: 0.8;
}

.welcome {
	color: white;
	font-weight: bold;
}

nav ul {
	display: flex;
	list-style: none;
	gap: 25px;
	align-items: center;
}

nav ul li a {
	text-decoration: none;
	color: white;
	font-weight: bold;
}

nav ul li a:hover {
	opacity: .8;
}

.cart-badge {
	background: white;
	color: #ff5200;
	padding: 2px 8px;
	border-radius: 50%;
	font-size: 12px;
	font-weight: bold;
}

/* HERO */
.hero {
	height: 100vh;
	position: relative;
}

.hero video {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.overlay {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, .55);
}

.hero-content {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	text-align: center;
	color: white;
	width: 100%;
	z-index: 10;
}

.hero-content h1 {
	font-size: 90px;
	margin-bottom: 20px;
}

.hero-content p {
	font-size: 24px;
	margin: 15px 0 30px;
}

.hero-search {
	display: flex;
	justify-content: center;
}

.hero-search input {
	width: 700px;
	height: 60px;
	border: none;
	outline: none;
	padding-left: 20px;
	font-size: 18px;
	border-radius: 12px 0 0 12px;
}

.hero-search button {
	width: 140px;
	border: none;
	background: #ff5200;
	color: white;
	font-size: 18px;
	cursor: pointer;
	border-radius: 0 12px 12px 0;
}

.stats {
	margin-top: 25px;
	display: flex;
	justify-content: center;
	gap: 30px;
	font-weight: bold;
	flex-wrap: wrap;
}

.offer-banner {
	background: #ff5200;
	color: white;
	padding: 15px;
	text-align: center;
	font-size: 20px;
	font-weight: bold;
}

.food-scroll {
	width: 95%;
	margin: 40px auto;
	display: flex;
	justify-content: center;
	gap: 35px;
	flex-wrap: wrap;
}

.food-item {
	text-decoration: none;
	color: black;
	text-align: center;
}

.food-item img {
	width: 150px;
	height: 150px;
	border-radius: 50%;
	object-fit: cover;
	transition: .3s;
}

.food-item img:hover {
	transform: scale(1.08);
}

.food-item span {
	display: block;
	margin-top: 10px;
	font-size: 26px;
	font-weight: 500;
}

.categories {
	width: 95%;
	margin: 25px auto;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-wrap: wrap;
	gap: 18px;
}

.category {
	background: white;
	color: #333;
	padding: 12px 22px;
	border-radius: 35px;
	text-decoration: none;
	font-weight: bold;
	box-shadow: 0 4px 12px rgba(0, 0, 0, .08);
	transition: .3s;
}

.category:hover {
	background: #ff5200;
	color: white;
	transform: translateY(-4px);
	box-shadow: 0 8px 18px rgba(255, 82, 0, .35);
}
/* HEADINGS */
.heading {
	text-align: center;
	font-size: 42px;
	margin-top: 30px;
	font-weight: 700;
}

.restaurant-count {
	text-align: center;
	color: gray;
	margin: 10px 0 20px;
}

.section-title {
	text-align: center;
	font-size: 40px;
	margin-top: 20px;
	margin-bottom: 20px;
}

/* RESTAURANT GRID */
.restaurants {
	width: 90%;
	margin: auto;
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
	gap: 30px;
}

.card {
	background: white;
	border-radius: 18px;
	overflow: hidden;
	position: relative;
	transition: .3s;
	box-shadow: 0 4px 15px rgba(0, 0, 0, .08);
}

.card:hover {
	transform: translateY(-6px);
}

.fav-btn {
	position: absolute;
	top: 15px;
	right: 15px;
	width: 42px;
	height: 42px;
	border-radius: 50%;
	background: white;
	display: flex;
	justify-content: center;
	align-items: center;
	color: red;
	font-size: 22px;
	text-decoration: none;
	box-shadow: 0 3px 10px rgba(0, 0, 0, .25);
	z-index: 100;
}

.fav-btn:hover {
	transform: scale(1.1);
	background: #fff0f0;
}

.card img {
	width: 100%;
	height: 260px;
	object-fit: cover;
	border-radius: 15px 15px 0 0;
}

.card-content {
	padding: 15px;
}

.card-content h3 {
	font-size: 22px;
	color: #222;
}

.card-content p {
	margin: 6px 0;
	color: #555;
}

.menu-btn {
	width: 100%;
	margin-top: 12px;
	background: #ff5200;
	color: white;
	border: none;
	padding: 12px;
	border-radius: 10px;
	cursor: pointer;
	font-weight: bold;
}

.card-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 12px;
}

.rating {
	background: #16a34a;
	color: white;
	padding: 4px 8px;
	border-radius: 5px;
	font-size: 12px;
	font-weight: bold;
}

.fav {
	text-decoration: none;
	font-size: 24px;
	color: red;
}

.menu-btn:hover {
	background: #e64a00;
}

@media ( max-width :1200px) {
	.restaurants {
		grid-template-columns: repeat(3, 1fr);
	}
}

@media ( max-width :900px) {
	.restaurants {
		grid-template-columns: repeat(2, 1fr);
	}
}

@media ( max-width :600px) {
	.restaurants {
		grid-template-columns: 1fr;
	}
}

.hero video {
	position: absolute;
	top: 0;
	left: 0;
}

/* FOOTER */
.footer {
	background: #171a29;
	color: white;
	text-align: center;
	padding: 25px;
	margin-top: 50px;
}

.footer h2 {
	margin-bottom: 15px;
}

/* MOBILE */
@media ( max-width :992px) {
	.hero-content h1 {
		font-size: 50px;
	}
	.hero-search input {
		width: 450px;
	}
}

@media ( max-width :768px) {
	nav {
		flex-direction: column;
		height: auto;
		padding: 15px;
	}
	.left-section {
		flex-direction: column;
	}
	.nav-search input {
		width: 250px;
	}
	.hero {
		height: 400px;
	}
	.hero-content h1 {
		font-size: 40px;
	}
	.hero-search {
		flex-direction: column;
		align-items: center;
	}
	.hero-search input {
		width: 90%;
		border-radius: 10px;
		margin-bottom: 10px;
	}
	.hero-search button {
		width: 90%;
		height: 50px;
		border-radius: 10px;
	}
}
</style>

</head>

<body>


	<nav>

		<div class="left-section">

			<div class="logo">
				<img src="<%=request.getContextPath()%>/images/ahar-logo.png">
				<span>AHAR</span>
			</div>

			<form action="home" method="get" class="nav-search">

				<input type="text" name="keyword"
					placeholder="Search restaurants, food, cuisines..."
					value="<%=request.getParameter("keyword") == null ? "" : request.getParameter("keyword")%>">
				<button type="submit">🔍</button>

			</form>

		</div>

		<div class="right-section">

			<%
			if (loggedUser != null) {
			%>

			<span class="welcome-user"> Welcome, <%=loggedUser.getUsername()%>
			</span> <a href="cart.jsp" class="nav-link"> 🛒 Cart <span
				class="cart-badge"> <%=session.getAttribute("cartCount") == null ? 0 : session.getAttribute("cartCount")%>
			</span>
			</a> <a href="profile" class="nav-link"> Profile </a>

			<%
			if ("admin".equalsIgnoreCase(loggedUser.getRole())) {
			%>

			<a href="couponManagement" class="nav-link"> 🎁 Coupons </a>

			<%
			}
			%>

			<%
			} else {
			%>

			<a href="login.jsp" class="nav-link"> Login </a> <a
				href="register.jsp" class="nav-link"> Signup </a>

			<%
			}
			%>

		</div>

	</nav>

	<!-- HERO SECTION -->

	<%
	boolean searching = request.getParameter("keyword") != null && !request.getParameter("keyword").trim().isEmpty();
	%>

	<%
	if (!searching) {
	%>

	<section class="hero">

		<video autoplay muted loop>
			<source src="<%=request.getContextPath()%>/videos/home-video.mp4"
				type="video/mp4">
		</video>

		<div class="overlay"></div>

		<div class="hero-content">

			<h1>Delicious Food Delivered Fast</h1>

			<p>Order from the best restaurants near you</p>

			<div class="stats">
				<span>⭐ <%=restaurants.size()%> Restaurants
				</span> <span>🍔 5000+ Food Items</span> <span>🚚 Fast Delivery</span> <span>❤️
					10K+ Customers</span>
			</div>

		</div>

	</section>

	<%
	}
	%>



	<!-- CATEGORIES -->

	<div class="categories">

		<div class="categories">

			<a href="home" class="category">🏠 All</a> <a href="home?sort=rating"
				class="category"> ⭐ Top Rated </a> <a href="home?sort=fast"
				class="category"> 🚀 Fast Delivery </a> <a href="home?sort=offers"
				class="category"> 🎁 Offers </a> <a href="home?keyword=Biryani"
				class="category"> 🍛 Biryani </a> <a href="home?keyword=Pizza"
				class="category"> 🍕 Pizza </a> <a href="home?keyword=Burger"
				class="category"> 🍔 Burger </a> <a href="home?keyword=Chinese"
				class="category"> 🍜 Chinese </a> <a href="home?keyword=Cafe"
				class="category"> ☕ Cafe </a>

		</div>
	</div>

	<%
	String keyword = request.getParameter("keyword");
	%>

	<h1 class="heading">

		<%
		if (keyword != null && !keyword.trim().isEmpty()) {
		%>

		Search Results For "<%=keyword%>"

		<%
		} else {
		%>

		Top brands for you

		<%
		}
		%>

	</h1>

	<div class="restaurant-count">

		Showing <b><%=restaurants.size()%></b> Restaurants
	</div>
	<h2 class="section-title">Food Delivery Restaurants in Bengaluru</h2>

	<%
	if (restaurants != null && !restaurants.isEmpty()) {
	%>

	<div class="restaurants">

		<%
		for (Restaurant r : restaurants) {
		%>

		<div class="card">

			<div
				style="position: absolute; left: 12px; top: 12px; background: #ff5200; color: white; padding: 5px 12px; border-radius: 20px; font-size: 13px; font-weight: bold; z-index: 10;">

				50% OFF</div>

			<!-- Favourite -->

			<a href="addFavourite?restaurantId=<%=r.getRestaurantId()%>"
				class="fav-btn"> ❤ </a>

			<!-- Image -->

			<a href="menu?restaurantId=<%=r.getRestaurantId()%>"> <img
				src="<%=request.getContextPath()%>/<%=r.getImagePath()%>"
				alt="<%=r.getName()%>">

			</a>

			<div class="card-content">

				<div class="card-header">

					<h3><%=r.getName()%></h3>

					<span class="rating"> ⭐ <%=String.format("%.1f", r.getRating())%>

					</span>

				</div>

				<p>
					🍽
					<%=r.getCuisineType()%>
				</p>

				<p>
					📍
					<%=r.getAddress()%>
				</p>

				<p>
					🚚

					<%=r.getDeliveryTime()%>

					mins • Free Delivery
				</p>

				<p style="color: green; font-weight: bold;">✔ Free Delivery</p>

				<a href="menu?restaurantId=<%=r.getRestaurantId()%>">

					<button class="menu-btn">View Menu</button>

				</a>

			</div>

		</div>
		<%
		}
		%>

	</div>

	<%
	} else {
	%>

	<div style="text-align: center; padding: 50px">

		<h2>😔 No Restaurants Found Try another restaurant or cuisine.</h2>

		<br> <a href="home"
			style="background: #ff5200; color: white; padding: 12px 25px; text-decoration: none; border-radius: 8px;">

			Back To Home </a>

	</div>

	<%
	}
	%>

	<!-- FOOTER -->

	<div class="footer">

		<h2>AHAR FOOD DELIVERY</h2>

		<p>Order Food Anytime, Anywhere</p>

		<br>

		<p>📍 Bengaluru, India</p>

		<p>📞 +91 7093639716</p>

		<p>📧 support@ahar.com</p>

		<br>

		<p>© 2026 AHAR Food Delivery Made with ❤ in Java Full Stack</p>

	</div>
	<script>
		document.querySelectorAll("a").forEach(function(link) {

			link.addEventListener("click", function() {

				document.getElementById("loader").style.display = "flex";

			});

		});

		document.querySelectorAll("form").forEach(function(form) {

			form.addEventListener("submit", function() {

				document.getElementById("loader").style.display = "flex";

			});

		});
	</script>
</body>
</html>