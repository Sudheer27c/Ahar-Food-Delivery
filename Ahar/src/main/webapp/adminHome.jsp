<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Integer userCount = (Integer) request.getAttribute("userCount");
Integer restaurantCount = (Integer) request.getAttribute("restaurantCount");
Integer orderCount = (Integer) request.getAttribute("orderCount");
Double revenue = (Double) request.getAttribute("revenue");

if(userCount == null) userCount = 0;
if(restaurantCount == null) restaurantCount = 0;
if(orderCount == null) orderCount = 0;
if(revenue == null) revenue = 0.0;
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
	font-family: Arial, sans-serif;
}

body {
	background: #f5f5f5;
}

.header {
	background: #ff5200;
	color: white;
	padding: 25px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.header h1 {
	font-size: 40px;
}

.container {
	width: 95%;
	margin: 30px auto;
}

.dashboard-cards {
	display: grid;
	grid-template-columns: repeat(4, 1fr);
	gap: 25px;
}

.card-link {
	text-decoration: none;
	color: black;
}

.card {
	background: white;
	padding: 30px;
	border-radius: 15px;
	text-align: center;
	box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
	transition: 0.3s;
}

.card:hover {
	transform: translateY(-5px);
}

.card h3 {
	color: #ff5200;
	margin-bottom: 20px;
}

.card h2 {
	font-size: 40px;
}

.modules {
	margin-top: 50px;
}

.modules h2 {
	margin-bottom: 20px;
}

.module-grid {
	display: grid;
	grid-template-columns: repeat(4, 1fr);
	gap: 25px;
}

.module-card {
	background: white;
	padding: 25px;
	border-radius: 15px;
	text-align: center;
	box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
}

.module-card h3 {
	margin-bottom: 10px;
}

.module-card p {
	color: #666;
	margin-bottom: 20px;
}

.btn {
	display: inline-block;
	background: #ff5200;
	color: white;
	text-decoration: none;
	padding: 12px 20px;
	border-radius: 8px;
}

.btn:hover {
	background: #e64a00;
}

@media ( max-width :900px) {
	.dashboard-cards, .module-grid {
		grid-template-columns: 1fr;
	}
}
</style>

</head>
<body>

	<div class="header">
		<h1>🍽 AHAR ADMIN</h1>
		<h2>Admin Dashboard</h2>
	</div>

	<div class="container">

		<div class="dashboard-cards">

			<a href="restaurantManagement" class="card-link">
				<div class="card">
					<h3>Restaurants</h3>
					<h2><%=restaurantCount%></h2>
				</div>
			</a> <a href="userManagement" class="card-link">
				<div class="card">
					<h3>Users</h3>
					<h2><%=userCount%></h2>
				</div>
			</a> <a href="adminOrders" class="card-link">
				<div class="card">
					<h3>Orders</h3>
					<h2><%=orderCount%></h2>
				</div>
			</a> <a href="adminDashboard" class="card-link">
				<div class="card">
					<h3>Revenue</h3>
					<h2>
						₹
						<%=String.format("%.2f", revenue)%></h2>
				</div>
			</a>

		</div>

		<div class="modules">

			<h2>Management Modules</h2>

			<div class="module-grid">

				<div class="module-card">
					<h3>Restaurant Management</h3>
					<p>Add, Edit and Delete Restaurants</p>
					<a href="restaurantManagement" class="btn"> Manage Restaurants
					</a>
				</div>

				<div class="module-card">
					<h3>Menu Management</h3>
					<p>Manage restaurant food items</p>
					<a href="menuManagement" class="btn"> Manage Menus </a>
				</div>

				<div class="module-card">
					<h3>Order Management</h3>
					<p>Track and update order status</p>
					<a href="adminOrders" class="btn"> Manage Orders </a>
				</div>

				<div class="module-card">
					<h3>User Management</h3>
					<p>View registered users</p>
					<a href="userManagement" class="btn"> View Users </a>
				</div>

				<div class="module-card">
					<h3>Add Restaurant</h3>
					<p>Create new restaurant listing</p>
					<a href="addRestaurant.jsp" class="btn"> Add Restaurant </a>
				</div>

				<div class="module-card">
					<h3>Customer Website</h3>
					<p>Open Ahar home page</p>
					<a href="home" class="btn"> Open Website </a>
				</div>

				<div class="module-card">
					<h3>Profile</h3>
					<p>View admin profile</p>
					<a href="profile" class="btn"> Profile </a>
				</div>

				<div class="module-card">
					<h3>Logout</h3>
					<p>Securely sign out</p>
					<a href="logout" class="btn"> Logout </a>
				</div>

			</div>

		</div>

	</div>

</body>
</html>