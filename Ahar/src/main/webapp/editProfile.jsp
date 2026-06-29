<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.User"%>

<%
User user =
(User)session.getAttribute("loggedInUser");

if(user == null){

    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Edit Profile</title>

<style>

body{
font-family:Arial;
background:#f5f5f5;
}

.container{
width:500px;
margin:50px auto;
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
padding:12px;
background:#ff5200;
color:white;
border:none;
cursor:pointer;
}

</style>

</head>

<body>

<div class="container">

<h2>Edit Profile</h2>

<form action="updateProfile"
method="post">

<input type="text"
name="username"
value="<%=user.getUsername()%>"
required>

<input type="email"
name="email"
value="<%=user.getEmail()%>"
required>

<input type="text"
name="address"
value="<%=user.getAddress()%>"
required>

<button type="submit">

Update Profile

</button>

</form>

</div>

</body>
</html>