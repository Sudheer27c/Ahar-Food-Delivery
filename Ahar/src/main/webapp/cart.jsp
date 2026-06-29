<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.tap.model.Cart"%>

<%
if (session.getAttribute("loggedInUser") == null) {
	response.sendRedirect("login.jsp");
	return;
}

Map<Integer, Cart> cartMap = (Map<Integer, Cart>) session.getAttribute("cart");

double grandTotal = 0;
int totalItems = 0;

if (cartMap != null) {
	for (Cart c : cartMap.values()) {
		grandTotal += c.getTotalPrice();
		totalItems += c.getQuantity();
	}
}

double deliveryFee = (grandTotal > 0) ? 40 : 0;
double gst = grandTotal * 0.05;
double finalTotal = grandTotal + deliveryFee + gst;
session.setAttribute("cartCount", totalItems);
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Ahar Cart</title>

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
	font-weight: bold;
	font-size: 18px;
}

/* LAYOUT */
.container {
	width: 90%;
	margin: 30px auto;
	display: flex;
	gap: 30px;
	align-items: flex-start;
}

.left {
	flex: 2;
}

.right {
	flex: 1;
}

/* CART CARD */
.card {
	display: flex;
	background: white;
	padding: 15px;
	margin-bottom: 15px;
	border-radius: 12px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, .12);
	transition: .3s;
}

.card:hover {
	transform: translateY(-4px);
}

.card img {
	width: 120px;
	height: 120px;
	object-fit: cover;
	border-radius: 10px;
	margin-right: 15px;
}

.details {
	flex: 1;
}

.details h2 {
	margin-bottom: 10px;
	font-size: 22px;
	color: #333;
}

.details p {
	margin: 8px 0;
}

/* QTY */
.qty-btn {
	background: white;
	color: #ff5200;
	border: 2px solid #ff5200;
	width: 35px;
	height: 35px;
	font-size: 18px;
	font-weight: bold;
	border-radius: 6px;
	cursor: pointer;
}

.qty-btn:hover {
	background: #ff5200;
	color: white;
}

.remove-btn {
	background: red;
	color: white;
	border: none;
	padding: 10px 18px;
	border-radius: 6px;
	cursor: pointer;
	font-weight: bold;
}

.remove-btn:hover {
	background: #cc0000;
}

/* BILL */
.bill-box {
	background: white;
	padding: 25px;
	border-radius: 15px;
	box-shadow: 0 3px 12px rgba(0, 0, 0, .15);
	position: sticky;
	top: 90px;
}

.bill-box h2 {
	color: #ff5200;
	margin-bottom: 20px;
}

.row {
	display: flex;
	justify-content: space-between;
	margin: 12px 0;
}

.total {
	font-size: 22px;
	font-weight: bold;
	padding-top: 15px;
}

.checkout-btn {
	width: 100%;
	height: 55px;
	background: #ff5200;
	color: white;
	font-size: 18px;
	font-weight: bold;
	border: none;
	border-radius: 10px;
	cursor: pointer;
	margin-top: 20px;
}

.checkout-btn:hover {
	background: #e64a00;
}

/* EMPTY */
.empty {
	text-align: center;
	padding: 100px;
}

.empty h2 {
	font-size: 35px;
	color: #666;
	margin-bottom: 20px;
}

.empty p {
	font-size: 18px;
	color: #888;
	margin-bottom: 20px;
}

.browse-btn {
	background: #ff5200;
	color: white;
	padding: 15px 25px;
	border: none;
	border-radius: 8px;
	font-size: 18px;
	font-weight: bold;
	cursor: pointer;
}

@media ( max-width :768px) {
	.container {
		flex-direction: column;
	}
	.card {
		flex-direction: column;
		text-align: center;
	}
	.card img {
		margin: 0 auto 15px;
	}
	.right {
		width: 100%;
	}
}
</style>

</head>

<body>
	


	<div class="header">

		<h1>
			🛒 My Cart (<%=totalItems%>
			Items)
		</h1>

		<a href="home"> ← Continue Shopping </a>

	</div>

	<%
	if (cartMap != null && !cartMap.isEmpty()) {
	%>

	<div class="container">

		<div class="left">

			<%
			for (Cart c : cartMap.values()) {
			%>

			<div class="card">

				<img src="<%=request.getContextPath()%>/<%=c.getImagePath()%>"
					onerror="this.src='<%=request.getContextPath()%>/images/default-food.jpg'">

				<div class="details">

					<h2><%=c.getItemName()%></h2>

					<p>
						Price : ₹
						<%=c.getPrice()%>
					</p>

					<p>

						<button type="button" class="qty-btn"
							onclick="location.href='updateCart?action=decrease&menuId=<%=c.getMenuId()%>'">
							-</button>

						<strong style="padding: 0 15px;"> <%=c.getQuantity()%>
						</strong>

						<button type="button" class="qty-btn"
							onclick="location.href='updateCart?action=increase&menuId=<%=c.getMenuId()%>'">
							+</button>

					</p>

					<p>
						Subtotal : ₹
						<%=String.format("%.2f", c.getTotalPrice())%>
					</p>

					<br> <a
						href="updateCart?action=remove&menuId=<%=c.getMenuId()%>">

						<button class="remove-btn">REMOVE</button>

					</a>

				</div>

			</div>

			<%
			}
			%>

		</div>

		<div class="right">

			<div class="bill-box">

				<div
					style="background: #e8f5e9; padding: 12px; border-radius: 8px; color: #2e7d32; font-weight: bold; margin-bottom: 15px;">
					🎉 You saved ₹100 on this order</div>

				<h2>Bill Details</h2>

				<div class="row">
					<span>Total Items</span> <span><%=totalItems%></span>
				</div>

				<div class="row">
					<span>Items Total</span> <span>₹ <%=String.format("%.2f", grandTotal)%></span>
				</div>

				<div class="row">
					<span>Delivery Fee</span> <span>₹ <%=String.format("%.2f", deliveryFee)%></span>
				</div>

				<div class="row">
					<span>GST (5%)</span> <span>₹ <%=String.format("%.2f", gst)%></span>
				</div>

				<hr style="margin: 15px 0;">

				<div class="row total">
					<span>To Pay</span> <span>₹ <%=String.format("%.2f", finalTotal)%></span>
				</div>

				<form action="checkout" method="get">

					<button type="submit" class="checkout-btn">PROCEED TO
						CHECKOUT</button>

				</form>

			</div>

		</div>

	</div>

	<%
	} else {
	%>

	<div class="empty">

		<h2>🛒 Cart Is Empty</h2>

		<p>Looks like you haven't added any food yet.</p>

		<a href="home">

			<button class="browse-btn">Browse Restaurants</button>

		</a>

	</div>

	<%
	}
	%>

</body>
</html>
