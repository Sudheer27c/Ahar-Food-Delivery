<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page import="com.tap.model.User"%>

<%
User admin = (User) session.getAttribute("loggedInUser");

Integer totalUsers = (Integer) request.getAttribute("totalUsers");

Integer totalRestaurants = (Integer) request.getAttribute("totalRestaurants");

Integer totalOrders = (Integer) request.getAttribute("totalOrders");

Integer deliveredOrders = (Integer) request.getAttribute("deliveredOrders");

Integer pendingOrders = (Integer) request.getAttribute("pendingOrders");

Double revenue = (Double) request.getAttribute("totalRevenue");

String topRestaurant = (String) request.getAttribute("topRestaurant");

String bestRestaurant = (String) request.getAttribute("bestRestaurant");

String revenueRestaurant = (String) request.getAttribute("revenueRestaurant");

Integer cancelledOrders = (Integer) request.getAttribute("cancelledOrders");

if (cancelledOrders == null)
	cancelledOrders = 0;

if (totalUsers == null)
	totalUsers = 0;
if (totalRestaurants == null)
	totalRestaurants = 0;
if (totalOrders == null)
	totalOrders = 0;
if (deliveredOrders == null)
	deliveredOrders = 0;
if (pendingOrders == null)
	pendingOrders = 0;
if (revenue == null)
	revenue = 0.0;
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Ahar Admin Dashboard</title>

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Segoe UI', sans-serif;
}

body {
	background: #f5f5f5;
}

.header {
	background: #ff5200;
	color: white;
	padding: 30px 50px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.header h1 {
	font-size: 38px;
}

.header h3 {
	font-weight: 400;
	margin-top: 8px;
}

.dashboard {
	width: 95%;
	margin: 30px auto;
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
	gap: 25px;
}

.card {
	background: white;
	padding: 35px;
	border-radius: 18px;
	text-align: center;
	box-shadow: 0 8px 20px rgba(0, 0, 0, .08);
	transition: .3s;
}

.card:hover {
	transform: translateY(-8px);
}

.card h2 {
	color: #ff5200;
	margin-bottom: 20px;
	font-size: 22px;
}

.card h1 {
	font-size: 42px;
	color: #222;
}

.menu-section {
	width: 95%;
	margin: 30px auto;
	background: white;
	padding: 30px;
	border-radius: 18px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, .08);
}

.menu-section h2 {
	margin-bottom: 25px;
	color: #333;
	text-align: center;
}

.btn-container {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	gap: 15px;
}

.btn {
	text-decoration: none;
	background: #ff5200;
	color: white;
	padding: 15px 25px;
	border-radius: 10px;
	font-size: 16px;
	font-weight: bold;
	transition: .3s;
}

.btn:hover {
	background: #e64a00;
	transform: translateY(-2px);
}

.logout {
	background: #e53935;
}

.logout:hover {
	background: #c62828;
}
</style>

</head>

<body>


	<div class="header">

		<div>
			<h1>Ahar Admin Dashboard</h1>
			<h3>
				Welcome,
				<%=admin.getUsername()%>
			</h3>
		</div>

	</div>
	<div class="dashboard">

		<div class="card">
			<h2>👥 Total Users</h2>
			<h1><%=totalUsers%></h1>
		</div>

		<div class="card">
			<h2>🍴 Restaurants</h2>
			<h1><%=totalRestaurants%></h1>
		</div>

		<div class="card">
			<h2>📦 Total Orders</h2>
			<h1><%=totalOrders%></h1>
		</div>

		<div class="card">
			<h2>✅ Delivered</h2>
			<h1><%=deliveredOrders%></h1>
		</div>

		<div class="card">
			<h2>⏳ Pending</h2>
			<h1><%=pendingOrders%></h1>
		</div>

		<div class="card">
			<h2>❌ Cancelled</h2>
			<h1><%=cancelledOrders%></h1>
		</div>

		<div class="card">
			<h2>💰 Revenue</h2>
			<h1>
				₹
				<%=String.format("%.0f", revenue)%></h1>
		</div>

	</div>
	<div class="menu-section">

		<h2>System Overview</h2>

		<div class="btn-container">

			<div class="card">
				<h2>Success Rate</h2>
				<h1>
					<%=totalOrders > 0 ? String.format("%.2f", (deliveredOrders * 100.0) / totalOrders) : "0"%>%
				</h1>
			</div>

			<div class="card">
				<h2>Pending Orders</h2>
				<h1><%=pendingOrders%></h1>
			</div>

		</div>

	</div>
	<div class="menu-section">

		<h2>📊 Restaurant Analytics</h2>

		<div class="btn-container">

			<div class="card">

				<h2>🏆 Most Ordered</h2>

				<h3>
					<%=topRestaurant%>
				</h3>

			</div>

			<div class="card">

				<h2>⭐ Highest Rated</h2>

				<h3>
					<%=bestRestaurant%>
				</h3>

			</div>

			<div class="card">

				<h2>💰 Highest Revenue</h2>

				<h3>
					<%=revenueRestaurant%>
				</h3>

			</div>

		</div>

	</div>
	<div class="menu-section">

		<h2>Management Modules</h2>

		<div class="btn-container">

			<a href="restaurantManagement" class="btn"> 🍴 Restaurants </a> <a
				href="menuManagement" class="btn"> 📋 Menus </a> <a
				href="couponManagement" class="btn"> 🎁 Coupons </a> <a
				href="adminOrders" class="btn"> 📦 Orders </a> <a
				href="generateReport" class="btn"> 📄 Generate Sales Report </a> <a
				href="userManagement" class="btn"> 👥 Users </a> <a
				href="addRestaurant.jsp" class="btn"> ➕ Add Restaurant </a> <a
				href="profile" class="btn"> 👤 Profile </a> <a href="logout"
				class="btn logout"> 🚪 Logout </a>


		</div>

	</div>

</body>
</html>
