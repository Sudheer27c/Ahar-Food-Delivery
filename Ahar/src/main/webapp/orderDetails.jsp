<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.tap.model.OrderTable"%>
<%@ page import="com.tap.model.OrderItem"%>
<%@ page import="com.tap.model.Restaurant"%>

<%
OrderTable order = (OrderTable) request.getAttribute("order");

List<OrderItem> orderItems = (List<OrderItem>) request.getAttribute("orderItems");

Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");

double finalAmount = order.getTotalAmount();

double deliveryFee = 40;

double taxableAmount = (finalAmount - deliveryFee) / 1.05;

double gst = taxableAmount * 0.05;

double itemsTotal = taxableAmount;
%>



<!DOCTYPE html>

<html>
<head>

<meta charset="UTF-8">

<title>Order Details</title>

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

.container {
	width: 85%;
	margin: 30px auto;
}

.card {
	background: white;
	padding: 30px;
	border-radius: 15px;
	box-shadow: 0 3px 12px rgba(0, 0, 0, .12);
}

.restaurant-name {
	color: #ff5200;
	font-size: 32px;
	font-weight: bold;
	margin-bottom: 8px;
}

.cuisine {
	color: #666;
	margin-bottom: 20px;
}

.summary {
	background: #fff8f2;
	border-left: 5px solid #ff5200;
	padding: 20px;
	border-radius: 10px;
	margin: 20px 0;
}

.summary p {
	margin: 10px 0;
	font-size: 18px;
}

.status {
	display: inline-block;
	padding: 8px 15px;
	border-radius: 20px;
	color: white;
	font-weight: bold;
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

h2 {
	margin: 25px 0 15px;
}

table {
	width: 100%;
	border-collapse: collapse;
	background: white;
}

table th {
	background: #ff5200;
	color: white;
	padding: 14px;
}

table td {
	padding: 14px;
	text-align: center;
	border-bottom: 1px solid #eee;
}

.btn {
	display: inline-block;
	margin-top: 20px;
	background: #ff5200;
	color: white;
	text-decoration: none;
	padding: 12px 20px;
	border-radius: 8px;
	font-weight: bold;
}

.btn:hover {
	background: #e64a00;
}
</style>

</head>

<body>


	<div class="container">


		<div class="card">
			<img src="<%=restaurant.getImagePath()%>"
				style="width: 100%; height: 230px; object-fit: cover; border-radius: 15px; margin-bottom: 20px;">

			<div class="restaurant-name">
				<p style="color: #666; font-size: 18px;">

					Order ID : <b>#<%=order.getOrderId()%></b>

				</p>

				🍴
				<%=restaurant.getName()%>

			</div>

			<div class="cuisine">

				<%=restaurant.getCuisineType()%>

			</div>

			<div class="summary">

				<p>

					<strong>Items Total :</strong> ₹
					<%=String.format("%.2f", itemsTotal)%>

				</p>

				<p>

					<strong>Delivery Fee :</strong> ₹
					<%=String.format("%.2f", deliveryFee)%>

				</p>

				<p>

					<strong>GST :</strong> ₹
					<%=String.format("%.2f", gst)%>

				</p>

				<hr style="margin: 15px 0;">

				<p style="font-size: 22px; color: #16a34a;">

					<strong>Grand Total :</strong> ₹
					<%=String.format("%.2f", finalAmount)%>

				</p>

				<p>

					<strong>Payment :</strong>

					<%=order.getPaymentMethod()%>

				</p>

				<p>

					<strong>Date :</strong>

					<%=order.getOrderDate()%>

				</p>

				<p>

					<strong>Status :</strong> <span
						class="status <%=order.getStatus()%>"> <%=order.getStatus()%>

					</span>

				</p>

				<p>

					<strong>Total Items :</strong>

					<%=orderItems.size()%>

				</p>

			</div>

			<h2>Ordered Items</h2>

			<table>

				<tr>
					<th>Image</th>
					<th>Item Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total</th>
				</tr>

				<%
				for (OrderItem item : orderItems) {
				%>

				<tr>

					<td><img src="<%=item.getImagePath()%>"
						style="width: 80px; height: 80px; border-radius: 12px; object-fit: cover; box-shadow: 0 2px 8px rgba(0, 0, 0, .2);"></td>

					<td><%=item.getItemName()%></td>

					<td>₹<%=item.getPrice()%>
					</td>

					<td><%=item.getQuantity()%></td>

					<td>₹<%=item.getItemTotal()%>
					</td>

				</tr>

				<%
				}
				%>

			</table>

			<a href="orderHistory" class="btn"> ← Back To Orders </a> <a
				href="downloadInvoice?orderId=<%=order.getOrderId()%>" class="btn"
				style="margin-left: 10px; background: #16a34a;"> 📄 Download
				Invoice </a>

		</div>


	</div>

</body>
</html>
