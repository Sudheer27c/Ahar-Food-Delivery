<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
Integer orderId = (Integer) session.getAttribute("lastOrderId");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Order Success - AHAR</title>

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: Arial, sans-serif;
}

body {
	background: #f5f5f5;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.success-box {
	background: white;
	padding: 50px;
	width: 600px;
	text-align: center;
	border-radius: 20px;
	box-shadow: 0 5px 20px rgba(0, 0, 0, .15);
}

.icon {
	font-size: 90px;
	margin-bottom: 20px;
}

h1 {
	color: #28a745;
	margin-bottom: 15px;
}

p {
	font-size: 18px;
	color: #555;
	margin-bottom: 15px;
}

.order-id {
	font-size: 22px;
	font-weight: bold;
	color: #ff5200;
	margin: 20px 0;
}

.btn {
	display: inline-block;
	padding: 14px 25px;
	background: #ff5200;
	color: white;
	text-decoration: none;
	border-radius: 8px;
	font-weight: bold;
	margin: 10px;
}

.btn:hover {
	background: #e64a00;
}
</style>

</head>

<body>

	<div class="success-box">

		<div class="icon">✅</div>

		<h1>Order Placed Successfully!</h1>

		<p>Thank you for ordering with AHAR.</p>

		<%
		if (orderId != null) {
		%>

		<div class="order-id">

			Order ID : #<%=orderId%>

		</div>

		<%
		}
		%>

		<p>Your food is being prepared and will be delivered soon.</p>

		<a href="orderHistory" class="btn"> 📦 Track Order </a> <a href="home"
			class="btn"> 🏠 Continue Shopping </a>

	</div>

</body>

</html>
