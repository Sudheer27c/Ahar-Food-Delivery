<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tap.model.Menu"%>

<%
List<Menu> menus = (List<Menu>) request.getAttribute("menus");
%>

<!DOCTYPE html>
<html>
<head>

<title>Menu Management</title>

<style>
body {
	font-family: Arial;
	background: #f5f5f5;
}

.container {
	width: 95%;
	margin: auto;
	padding: 20px;
}

.btn {
	background: #ff5200;
	color: white;
	padding: 10px 15px;
	text-decoration: none;
	border-radius: 5px;
}

table {
	width: 100%;
	background: white;
	border-collapse: collapse;
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

img {
	width: 80px;
	height: 60px;
	object-fit: cover;
}
</style>

</head>

<body>

	<div class="container">

		<h1>Menu Management</h1>

		<br> <a href="addMenu.jsp" class="btn"> Add Menu Item </a>

		<table>

			<tr>

				<th>ID</th>
				<th>Restaurant</th>
				<th>Image</th>
				<th>Item</th>
				<th>Price</th>
				<th>Action</th>

			</tr>

			<%
			for (Menu m : menus) {
			%>

			<tr>

				<td><%=m.getMenuId()%></td>

				<td><%=m.getRestaurantId()%></td>

				<td><img
					src="<%=request.getContextPath()%>/<%=m.getImagePath()%>"></td>

				<td><%=m.getItemName()%></td>

				<td>₹ <%=m.getPrice()%></td>

				<td><a href="deleteMenu?menuId=<%=m.getMenuId()%>" class="btn">

						Delete </a></td>

			</tr>

			<%
}
%>

		</table>

	</div>

</body>
</html>