<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.Restaurant"%>

<%
Restaurant restaurant =
(Restaurant)request.getAttribute("restaurant");
%>

<!DOCTYPE html>
<html>
<head>

<title>Edit Restaurant</title>

<style>

body{
font-family:Arial;
background:#f5f5f5;
}

.container{
width:600px;
margin:40px auto;
background:white;
padding:30px;
border-radius:10px;
box-shadow:0 0 10px gray;
}

input{
width:100%;
padding:12px;
margin:10px 0;
}

button{
width:100%;
padding:15px;
background:#ff5200;
color:white;
border:none;
cursor:pointer;
}

</style>

</head>

<body>

<div class="container">

<h1>Edit Restaurant</h1>

<form action="updateRestaurant" method="post">

<input
type="hidden"
name="restaurantId"
value="<%=restaurant.getRestaurantId()%>">

Name:

<input
type="text"
name="name"
value="<%=restaurant.getName()%>">

Cuisine:

<input
type="text"
name="cuisineType"
value="<%=restaurant.getCuisineType()%>">

Delivery Time:

<input
type="number"
name="deliveryTime"
value="<%=restaurant.getDeliveryTime()%>">

Address:

<input
type="text"
name="address"
value="<%=restaurant.getAddress()%>">

Rating:

<input
type="number"
step="0.1"
name="rating"
value="<%=restaurant.getRating()%>">

Image Path:

<input
type="text"
name="imagePath"
value="<%=restaurant.getImagePath()%>">

<button type="submit">

Update Restaurant

</button>

</form>

</div>

</body>
</html>