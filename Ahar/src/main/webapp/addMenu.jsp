<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title>Add Menu</title>

<style>

body{
font-family:Arial;
background:#f5f5f5;
}

.container{
width:500px;
margin:auto;
margin-top:40px;
background:white;
padding:30px;
border-radius:10px;
box-shadow:0 0 10px gray;
}

input,textarea{
width:100%;
padding:12px;
margin-top:10px;
margin-bottom:20px;
}

button{
background:#ff5200;
color:white;
padding:12px;
border:none;
width:100%;
}

</style>

</head>

<body>

<div class="container">

<h2>Add Menu Item</h2>

<form action="addMenu" method="post">

Restaurant ID

<input type="number"
name="restaurantId">

Item Name

<input type="text"
name="itemName">

Description

<textarea
name="description"></textarea>

Price

<input type="text"
name="price">

Image Path

<input type="text"
name="imagePath">

<button type="submit">

Add Menu

</button>

</form>

</div>

</body>
</html>