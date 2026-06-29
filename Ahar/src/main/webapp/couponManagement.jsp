<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.tap.model.Coupon"%>

<%
List<Coupon> coupons = (List<Coupon>) request.getAttribute("coupons");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Coupon Management</title>

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

.container {
	width: 95%;
	margin: 30px auto;
}

h1 {
	color: #ff5200;
	margin-bottom: 20px;
}

.card {
	background: white;
	padding: 25px;
	border-radius: 12px;
	box-shadow: 0 3px 10px rgba(0, 0, 0, .1);
	margin-bottom: 30px;
}

input {
	width: 100%;
	padding: 12px;
	margin: 10px 0;
	border: 1px solid #ccc;
	border-radius: 6px;
}

button {
	background: #ff5200;
	color: white;
	padding: 12px 18px;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	font-weight: bold;
}

button:hover {
	background: #e64a00;
}

table {
	width: 100%;
	border-collapse: collapse;
	background: white;
}

th {
	background: #ff5200;
	color: white;
	padding: 14px;
}

td {
	padding: 12px;
	text-align: center;
	border: 1px solid #ddd;
}

.delete-btn {
	background: #e53935;
	padding: 8px 14px;
	color: white;
	text-decoration: none;
	border-radius: 5px;
}

.delete-btn:hover {
	background: #c62828;
}

.back-btn {
	display: inline-block;
	margin-bottom: 20px;
	padding: 10px 15px;
	background: #2196f3;
	color: white;
	text-decoration: none;
	border-radius: 6px;
}

.active {
	color: green;
	font-weight: bold;
}

.inactive {
	color: red;
	font-weight: bold;
}
</style>

</head>

<body>


	<div class="container">

		<a href="adminDashboard" class="back-btn"> ← Dashboard </a>

		<h1>🎁 Coupon Management</h1>

		<div class="card">

			<h2>Add New Coupon</h2>

			<br>

			<form action="addCoupon" method="post">

				<input type="text" name="couponCode" placeholder="Coupon Code"
					required> <input type="number" step="0.01" name="discount"
					placeholder="Discount Percentage" required> <input
					type="number" step="0.01" name="minAmount"
					placeholder="Minimum Order Amount" required>

				<button type="submit">Add Coupon</button>

			</form>

		</div>

		<div class="card">

			<h2>Available Coupons</h2>

			<br>

			<form action="couponManagement" method="get">

				<input type="text" name="keyword" placeholder="Search Coupon"
					style="width: 300px; padding: 10px;">

				<button type="submit">Search</button>

			</form>

			<br> <br>

			<table>

				<tr>

					<th>ID</th>

					<th>Coupon</th>

					<th>Discount</th>

					<th>Minimum Amount</th>

					<th>Status</th>

					<th>Action</th>

				</tr>

				<%
				if (coupons != null) {

					for (Coupon coupon : coupons) {
				%>

				<tr>

					<td><%=coupon.getCouponId()%></td>

					<td><%=coupon.getCouponCode()%></td>

					<td><%=coupon.getDiscountPercent()%> %</td>

					<td>₹ <%=coupon.getMinAmount()%>

					</td>

					<td>
						<%
						if (coupon.isActive()) {
						%> <span class="active"> ACTIVE </span> <%
 } else {
 %> <span class="inactive"> INACTIVE </span> <%
 }
 %>

					</td>

					<td>
						<%
						if (coupon.isActive()) {
						%> <a
						href="toggleCoupon?couponId=<%=coupon.getCouponId()%>&active=true"
						style="background: #e53935; padding: 8px 15px; color: white; text-decoration: none; border-radius: 5px;">

							Disable </a> <%
 } else {
 %> <a
						href="toggleCoupon?couponId=<%=coupon.getCouponId()%>&active=false"
						style="background: #16a34a; padding: 8px 15px; color: white; text-decoration: none; border-radius: 5px;">

							Enable </a> <%
 }
 %>

					</td>

				</tr>

				<%
				}

				}
				%>

			</table>

		</div>

	</div>

</body>

</html>