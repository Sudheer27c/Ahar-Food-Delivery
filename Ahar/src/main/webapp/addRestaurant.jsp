<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Add Restaurant | Ahar Admin</title>

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:Arial,sans-serif;
}

body{
background:#f5f5f5;
}

.navbar{
background:#ff5200;
padding:20px;
display:flex;
justify-content:space-between;
align-items:center;
}

.logo{
color:white;
font-size:30px;
font-weight:bold;
}

.navbar a{
color:white;
text-decoration:none;
font-weight:bold;
margin-left:20px;
}

.container{
width:600px;
margin:40px auto;
background:white;
padding:30px;
border-radius:10px;
box-shadow:0 0 10px rgba(0,0,0,.2);
}

h1{
text-align:center;
color:#ff5200;
margin-bottom:25px;
}

.form-group{
margin-bottom:15px;
}

label{
display:block;
margin-bottom:5px;
font-weight:bold;
}

input{
width:100%;
padding:12px;
border:1px solid #ccc;
border-radius:6px;
}

button{
width:100%;
padding:15px;
background:#ff5200;
color:white;
border:none;
border-radius:8px;
font-size:18px;
cursor:pointer;
font-weight:bold;
}

button:hover{
background:#e64a00;
}

</style>

</head>

<body>

<div class="navbar">

<div class="logo">
AHAR ADMIN
</div>

<div>

<a href="adminHome">Dashboard</a>

<a href="restaurantManagement">Restaurants</a>

<a href="logout">Logout</a>

</div>

</div>

<div class="container">

<h1>Add Restaurant</h1>

<form action="addRestaurant" method="post">

<div class="form-group">

<label>Restaurant Name</label>

<input
type="text"
name="name"
required>

</div>

<div class="form-group">

<label>Cuisine Type</label>

<input
type="text"
name="cuisineType"
required>

</div>

<div class="form-group">

<label>Delivery Time (Minutes)</label>

<input
type="number"
name="deliveryTime"
required>

</div>

<div class="form-group">

<label>Address</label>

<input
type="text"
name="address"
required>

</div>

<div class="form-group">

<label>Rating</label>

<input
type="number"
step="0.1"
min="1"
max="5"
name="rating"
required>

</div>

<div class="form-group">

<label>Image Path</label>

<input
type="text"
name="imagePath"
placeholder="images/kfc.jpg"
required>

</div>

<button type="submit">

Add Restaurant

</button>

</form>

</div>

</body>
</html>