<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.tap.model.OrderTable"%>

<%
List<OrderTable> orders = (List<OrderTable>) request.getAttribute("orders");

if (orders == null) {
	orders = new ArrayList<>();
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Orders</title>

<style>
body {
	margin: 0;
	font-family: Arial, sans-serif;
	background: #f5f5f5;
}

.header {
	background: #ff5200;
	color: white;
	padding: 20px;
	text-align: center;
}

.container {
	width: 95%;
	margin: auto;
	padding: 20px;
}

.home-btn {
	float: right;
	text-decoration: none;
	background: #ff5200;
	color: white;
	padding: 10px 20px;
	border-radius: 8px;
}

table {
	width: 100%;
	border-collapse: collapse;
	background: white;
	margin-top: 20px;
}

th {
	background: #ff5200;
	color: white;
	padding: 12px;
}

td {
	padding: 12px;
	border: 1px solid #ddd;
	text-align: center;
}

select {
	padding: 8px;
}

button {
	background: #ff5200;
	color: white;
	border: none;
	padding: 8px 15px;
	border-radius: 5px;
	cursor: pointer;
}

button:hover {
	background: #e64a00;
}

.status {
	font-weight: bold;
}
</style>

</head>

<body>

	<div class="header">
		<h1>📦 Admin Order Management</h1>
	</div>

	<div class="container">

		<a href="adminDashboard" class="home-btn"> Dashboard </a>

		<h2>
			Total Orders :
			<%=orders.size()%>
		</h2>

		<%
		if (orders.isEmpty()) {
		%>

		<h3>No Orders Available</h3>

		<%
		} else {
		%>

		<table>

			<tr>

				<th>Order ID</th>
				<th>User ID</th>
				<th>Restaurant ID</th>
				<th>Amount</th>
				<th>Payment</th>
				<th>Status</th>
				<th>Change Status</th>

			</tr>

			<%
			for (OrderTable order : orders) {
			%>

			<tr>

				<td><%=order.getOrderId()%></td>

				<td><%=order.getUserId()%></td>

				<td><%=order.getRestaurantId()%></td>

				<td>₹ <%=order.getTotalAmount()%>
				</td>

				<td><%=order.getPaymentMethod()%></td>

				<td><b> <%=order.getStatus()%>
				</b></td>

				<td>

					<form action="updateOrderStatus" method="post">

						<input type="hidden" name="orderId"
							value="<%=order.getOrderId()%>"> <select name="status">

							<option value="PENDING"
								<%="PENDING".equals(order.getStatus()) ? "selected" : ""%>>
								PENDING</option>

							<option value="CONFIRMED"
								<%="CONFIRMED".equals(order.getStatus()) ? "selected" : ""%>>
								CONFIRMED</option>

							<option value="PREPARING"
								<%="PREPARING".equals(order.getStatus()) ? "selected" : ""%>>
								PREPARING</option>

							<option value="DELIVERED"
								<%="DELIVERED".equals(order.getStatus()) ? "selected" : ""%>>
								DELIVERED</option>

							<option value="CANCELLED"
								<%="CANCELLED".equals(order.getStatus()) ? "selected" : ""%>>
								CANCELLED</option>

						</select>

						<button type="submit">Update</button>

					</form>

				</td>

			</tr>

			<%
}
%>

		</table>

		<%
}
%>

	</div>

</body>
</html>