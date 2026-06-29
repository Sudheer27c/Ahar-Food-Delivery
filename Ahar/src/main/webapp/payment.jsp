<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
if(session.getAttribute("loggedInUser") == null){

    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Ahar Payment</title>

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

.container{
width:600px;
margin:50px auto;
background:white;
padding:30px;
border-radius:10px;
box-shadow:0 0 10px rgba(0,0,0,.2);
}

h1{
color:#ff5200;
text-align:center;
margin-bottom:25px;
}

.method{
padding:15px;
margin:15px 0;
border:1px solid #ddd;
border-radius:10px;
font-size:18px;
cursor:pointer;
}

.method:hover{
background:#fff3ed;
}

input[type="radio"]{
margin-right:10px;
}

button{
width:100%;
padding:15px;
background:#ff5200;
color:white;
border:none;
font-size:18px;
border-radius:8px;
cursor:pointer;
margin-top:20px;
}

button:hover{
background:#e64a00;
}

</style>

</head>

<body>

<div class="container">

<h1>Choose Payment Method</h1>

<form action="placeOrder" method="post">

<label class="method">

<input
type="radio"
name="paymentMethod"
value="Cash On Delivery"
checked>

Cash On Delivery

</label>

<label class="method">

<input
type="radio"
name="paymentMethod"
value="UPI">

UPI Payment

</label>

<label class="method">

<input
type="radio"
name="paymentMethod"
value="Card">

Credit / Debit Card

</label>

<button type="submit">

Place Order

</button>

</form>

</div>

</body>

</html>