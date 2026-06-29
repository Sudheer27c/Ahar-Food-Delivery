<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.Map"%>
<%@ page import="com.tap.model.Cart"%>
<%@ page import="com.tap.model.User"%>
<%@ page import="com.tap.model.Coupon"%>

<%
if (session.getAttribute("loggedInUser") == null) {

	response.sendRedirect("login.jsp");
	return;
}

User user = (User) session.getAttribute("loggedInUser");

Map<Integer, Cart> cartMap = (Map<Integer, Cart>) session.getAttribute("cart");

if (cartMap == null || cartMap.isEmpty()) {

	response.sendRedirect("cart");
	return;
}

double grandTotal = 0;

for (Cart c : cartMap.values()) {

	grandTotal += c.getTotalPrice();
}

Coupon coupon = (Coupon) session.getAttribute("coupon");

double discount = 0;

if (coupon != null && grandTotal >= coupon.getMinAmount()) {

	discount = grandTotal * coupon.getDiscountPercent() / 100;
}

double deliveryFee = 40;

double gst = (grandTotal - discount) * 0.05;

double finalTotal = grandTotal - discount + deliveryFee + gst;

String couponMessage = (String) session.getAttribute("couponMessage");

session.removeAttribute("couponMessage");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Ahar Checkout</title>

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
	width: 850px;
	margin: 40px auto;
	background: white;
	padding: 30px;
	border-radius: 15px;
	box-shadow: 0 2px 15px rgba(0, 0, 0, .15);
}

h1 {
	color: #ff5200;
	margin-bottom: 20px;
}

.back-btn {
	display: inline-block;
	margin-bottom: 20px;
	text-decoration: none;
	color: #ff5200;
	font-weight: bold;
}

.user-box {
	background: #fff3e0;
	padding: 18px;
	border-radius: 10px;
	margin-bottom: 20px;
}

.user-box h3 {
	color: #ff5200;
	margin-bottom: 10px;
}

.user-box p {
	margin: 8px 0;
}

label {
	display: block;
	margin-top: 15px;
	font-weight: bold;
}

select {
	width: 100%;
	padding: 12px;
	margin-top: 6px;
	border: 1px solid #ccc;
	border-radius: 6px;
}

input[type=text] {
	width: 100%;
	padding: 12px;
	margin-top: 10px;
	border: 1px solid #ccc;
	border-radius: 6px;
	font-size: 15px;
}

.order-summary {
	margin-top: 25px;
	padding: 20px;
	background: #fafafa;
	border-radius: 10px;
}

.order-summary h2 {
	color: #ff5200;
	margin-bottom: 15px;
}

.item {
	padding: 10px 0;
	border-bottom: 1px solid #ddd;
}

.total-row {
	display: flex;
	justify-content: space-between;
	margin-top: 12px;
	font-size: 16px;
}

.final-total {
	margin-top: 20px;
	font-size: 24px;
	font-weight: bold;
	color: green;
}

button {
	width: 100%;
	padding: 15px;
	margin-top: 20px;
	background: #ff5200;
	color: white;
	border: none;
	border-radius: 8px;
	font-size: 18px;
	cursor: pointer;
}

button:hover {
	background: #e64a00;
}

.success {
	background: #e8f5e9;
	color: #2e7d32;
	padding: 12px;
	border-radius: 6px;
	margin-top: 15px;
	margin-bottom: 15px;
	font-weight: bold;
}

.error {
	background: #ffebee;
	color: #c62828;
	padding: 12px;
	border-radius: 6px;
	margin-top: 15px;
	margin-bottom: 15px;
	font-weight: bold;
}

.discount {
	color: green;
	font-weight: bold;
}
</style>

</head>
<body>

	<div class="container">

		<a href="cart" class="back-btn"> ← Back To Cart </a>

		<h1>Checkout</h1>

		<%
		String error = request.getParameter("error");

		if (error != null) {
		%>

		<div class="error">Unable to place your order. Please try again.
		</div>

		<%
		}
		%>

		<%
		if (couponMessage != null) {

			if (coupon != null) {
		%>

		<div class="success">

			<%=couponMessage%>

		</div>

		<%
		} else {
		%>

		<div class="error">

			<%=couponMessage%>

		</div>

		<%
		}
		}
		%>

		<div class="user-box">

			<h3>Delivery Details</h3>

			<p>

				<b>Name :</b>

				<%=user.getUsername()%>

			</p>

			<p>

				<b>Email :</b>

				<%=user.getEmail()%>

			</p>

			<p>

				<b>Address :</b>

				<%=user.getAddress()%>

			</p>

		</div>

		<form action="placeOrder" method="post">

			<label> Payment Method </label> <select name="paymentMethod" required>

				<option value="">Select Payment Method</option>

				<option value="Cash On Delivery">Cash On Delivery</option>

				<option value="UPI">UPI</option>

				<option value="Card">Credit / Debit Card</option>

			</select> <br>
			<br>

			<h3 style="color: #ff5200;">Apply Coupon</h3>

			<div style="display: flex; gap: 10px; margin-top: 10px;">

				<input type="text" name="couponCode" placeholder="Enter Coupon Code"
					value="<%=coupon != null ? coupon.getCouponCode() : ""%>"
					style="flex: 1;">

				<button type="submit" formaction="applyCoupon"
					style="width: 180px; margin-top: 0; background: #16a34a;">

					Apply Coupon</button>

			</div>

			<br>

			<div class="order-summary">

				<h2>Order Summary</h2>
				<%
				for (Cart c : cartMap.values()) {
				%>

				<div class="item">

					<b><%=c.getItemName()%></b> <br> Qty :
					<%=c.getQuantity()%>

					&nbsp;&nbsp;&nbsp; Price : ₹<%=String.format("%.2f", c.getPrice())%>

					<span style="float: right;"> ₹<%=String.format("%.2f", c.getTotalPrice())%>

					</span>

				</div>

				<%
				}
				%>

				<div class="total-row">

					<span> Items Total </span> <span> ₹ <%=String.format("%.2f", grandTotal)%>

					</span>

				</div>

				<div class="total-row">

					<span> Delivery Fee </span> <span> ₹ <%=String.format("%.2f", deliveryFee)%>

					</span>

				</div>

				<div class="total-row">

					<span> GST (5%) </span> <span> ₹ <%=String.format("%.2f", gst)%>

					</span>

				</div>

				<%
				if (coupon != null) {
				%>

				<div class="total-row">

					<span> Coupon Discount (<%=coupon.getCouponCode()%>)

					</span> <span class="discount"> - ₹ <%=String.format("%.2f", discount)%>

					</span>

				</div>

				<%
				}
				%>

				<hr style="margin: 18px 0;">

				<div class="final-total">

					To Pay : ₹
					<%=String.format("%.2f", finalTotal)%>

				</div>

			</div>

			<button type="submit">PLACE ORDER</button>

		</form>

	</div>

</body>

</html>