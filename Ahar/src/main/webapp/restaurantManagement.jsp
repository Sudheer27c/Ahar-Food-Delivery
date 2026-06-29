<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tap.model.Restaurant"%>

<%
List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
%>

<!DOCTYPE html>
<html>
<head>

<title>Restaurant Management</title>

<style>
body {
	font-family: Arial;
	background: #f5f5f5;
}

.container {
	width: 95%;
	margin: auto;
	padding: 30px;
}

table {
	width: 100%;
	background: white;
	border-collapse: collapse;
}

th, td {
	padding: 15px;
	border: 1px solid #ddd;
	text-align: center;
}

th {
	background: #ff5200;
	color: white;
}

.btn {
	padding: 8px 15px;
	color: white;
	text-decoration: none;
	border-radius: 5px;
}

.edit {
	background: green;
}

.delete {
	background: red;
}

.add {
	background: #ff5200;
	padding: 12px 20px;
	display: inline-block;
	margin-bottom: 20px;
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

		<h1>Restaurant Management</h1>

		<a href="addRestaurant.jsp" class="btn add"> Add Restaurant </a>

		<table>

			<tr>

				<th>ID</th>
				<th>Image</th>
				<th>Name</th>
				<th>Cuisine</th>
				<th>Rating</th>
				<th>Actions</th>

			</tr>

			<%
			for (Restaurant r : restaurants) {
			%>

			<tr>

				<td><%=r.getRestaurantId()%></td>

				<td><img
					src="<%=request.getContextPath()%>/<%=r.getImagePath()%>"></td>

				<td><%=r.getName()%></td>

				<td><%=r.getCuisineType()%></td>

				<td><%=r.getRating()%></td>

				<td><a class="btn edit"
					href="editRestaurant?restaurantId=<%=r.getRestaurantId()%>">

						Edit </a> <a class="btn delete"
					href="deleteRestaurant?restaurantId=<%=r.getRestaurantId()%>">

						Delete </a></td>

			</tr>

			<%
}
%>

		</table>

	</div>

</body>
</html>
