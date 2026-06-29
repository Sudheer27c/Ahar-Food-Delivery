<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.tap.model.OrderTable"%>
<%@ page import="com.tap.model.User"%>
<%@ page import="com.tap.model.Restaurant"%>
<%@ page import="com.tap.DAOImpl.RestaurantDAOImpl"%>

<%
User user = (User) session.getAttribute("loggedInUser");

if (user == null) {
	response.sendRedirect("login.jsp");
	return;
}

List<OrderTable> orders = (List<OrderTable>) request.getAttribute("orders");

RestaurantDAOImpl restaurantDAO = new RestaurantDAOImpl();
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>My Orders - AHAR</title>

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
	padding: 25px 60px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.header h1 {
	font-size: 40px;
}

.header p {
	margin-top: 5px;
	font-size: 15px;
}

.home-btn {
	background: white;
	color: #ff5200;
	text-decoration: none;
	padding: 12px 22px;
	border-radius: 10px;
	font-weight: bold;
}

.container {
	width: 90%;
	margin: 30px auto;
}

.order-card {
	background: white;
	border-radius: 16px;
	padding: 25px;
	margin-bottom: 20px;
	box-shadow: 0 3px 10px rgba(0, 0, 0, .08);
}

.restaurant-name {
	font-size: 28px;
	font-weight: bold;
	color: #222;
	margin-bottom: 10px;
}

.order-date {
	color: #666;
	margin-bottom: 10px;
}

.amount {
	color: #ff5200;
	font-size: 28px;
	font-weight: bold;
	margin-bottom: 10px;
}

.payment {
	font-size: 18px;
	color: #444;
	margin-bottom: 15px;
}

.status {
	display: inline-block;
	padding: 8px 16px;
	border-radius: 20px;
	color: white;
	font-weight: bold;
}

.timeline {
	display: flex;
	justify-content: space-between;
	margin-top: 20px;
}

.step {
	padding: 8px 15px;
	background: #ddd;
	border-radius: 20px;
	font-size: 13px;
}

.step.active {
	background: #16a34a;
	color: white;
}

.PENDING {
	background: #ff9800;
}

.CONFIRMED {
	background: #2196f3;
}

.PREPARING {
	background: #9c27b0;
}

.DELIVERED {
	background: #4caf50;
}

.CANCELLED {
	background: #f44336;
}

.btn-group {
	margin-top: 20px;
}

.btn {
	display: inline-block;
	background: #ff5200;
	color: white;
	text-decoration: none;
	padding: 12px 18px;
	border-radius: 8px;
	font-weight: bold;
	margin-right: 10px;
}

.review-btn {
	background: #16a34a;
}

.empty {
	background: white;
	padding: 60px;
	border-radius: 15px;
	text-align: center;
}

.order-top {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.order-left {
	flex: 1;
}

.order-right {
	text-align: right;
}
</style>

</head>

<body>


	<div class="header">


		<div>
			<h1>My Orders</h1>
			<p>Track your recent orders</p>
		</div>

		<a href="home" class="home-btn"> Home </a>

	</div>

	<div class="container">

		<%
		if (orders == null || orders.isEmpty()) {
		%>

		<div class="empty">


			<h2>No Orders Yet</h2>

			<br> <a href="home" class="btn"> Start Ordering </a>

		</div>

		<%
		} else {

		for (OrderTable order : orders) {

			Restaurant restaurant = restaurantDAO.getRestaurant(order.getRestaurantId());
		%>

		<div class="order-card">
			<img
				src="<%=request.getContextPath()%>/<%=restaurant.getImagePath()%>"
				alt="<%=restaurant.getName()%>"
				style="width: 100%; height: 220px; object-fit: cover; border-radius: 15px; margin-bottom: 15px;">

			<div class="order-top">

				<div class="order-left">

					<div class="restaurant-name">
						<%=restaurant.getName()%>
					</div>

				</div>

				<div class="order-right">

					<span class="status <%=order.getStatus()%>"> <%=order.getStatus()%>
					</span>

				</div>

			</div>
			<div class="order-date">

				Ordered On :
				<%=order.getOrderDate()%>

			</div>

			<div class="amount">

				₹
				<%=order.getTotalAmount()%>

			</div>

			<div class="payment">

				Payment :
				<%=order.getPaymentMethod()%>

			</div>

			<div class="timeline">

				<div
					class="<%=order.getStatus().equals("PENDING") || order.getStatus().equals("CONFIRMED")
		|| order.getStatus().equals("PREPARING") || order.getStatus().equals("DELIVERED") ? "step active" : "step"%>">
					Order Placed</div>

				<div
					class="<%=order.getStatus().equals("CONFIRMED") || order.getStatus().equals("PREPARING")
		|| order.getStatus().equals("DELIVERED") ? "step active" : "step"%>">
					Confirmed</div>

				<div
					class="<%=order.getStatus().equals("PREPARING") || order.getStatus().equals("DELIVERED") ? "step active" : "step"%>">
					Preparing</div>

				<div
					class="<%=order.getStatus().equals("DELIVERED") ? "step active" : "step"%>">
					Delivered</div>

			</div>

			<div class="btn-group">

				<a href="menu?restaurantId=<%=order.getRestaurantId()%>" class="btn">
					Reorder </a> <a href="orderDetails?orderId=<%=order.getOrderId()%>"
					class="btn"> View Details </a>

				<%
				if ("PENDING".equalsIgnoreCase(order.getStatus()) || "CONFIRMED".equalsIgnoreCase(order.getStatus())) {
				%>

				<a href="cancelOrder?orderId=<%=order.getOrderId()%>" class="btn"
					style="background: #e53935;"> Cancel Order </a>

				<%
				}
				%>

				<%
				if ("DELIVERED".equalsIgnoreCase(order.getStatus())) {
				%>

				<a href="addReview.jsp?restaurantId=<%=order.getRestaurantId()%>"
					class="btn review-btn"> Review </a> <a
					href="restaurantReviews?restaurantId=<%=order.getRestaurantId()%>"
					class="btn" style="background: #2563eb;"> Reviews </a>

				<%
				}
				%>

			</div>


		</div>

		<%
		}
		}
		%>

	</div>

</body>
</html>
