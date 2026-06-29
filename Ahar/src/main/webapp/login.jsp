<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Ahar Login</title>

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

.container {
	width: 420px;
	background: white;
	padding: 35px;
	border-radius: 15px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, .2);
}

.logo {
	text-align: center;
	margin-bottom: 20px;
}

.logo h1 {
	color: #ff5200;
	font-size: 40px;
}

.logo p {
	color: #666;
	margin-top: 5px;
}

.error {
	background: #ffe5e5;
	color: red;
	padding: 10px;
	border-radius: 5px;
	text-align: center;
	margin-bottom: 15px;
	font-weight: bold;
}

input {
	width: 100%;
	padding: 12px;
	margin: 10px 0;
	border: 1px solid #ccc;
	border-radius: 8px;
	font-size: 15px;
}

button {
	width: 100%;
	padding: 12px;
	background: #ff5200;
	color: white;
	border: none;
	border-radius: 8px;
	font-size: 16px;
	font-weight: bold;
	cursor: pointer;
}

button:hover {
	background: #e64a00;
}

.links {
	margin-top: 15px;
	display: flex;
	justify-content: space-between;
}

.links a {
	text-decoration: none;
	color: #ff5200;
	font-weight: bold;
}

.links a:hover {
	text-decoration: underline;
}

.home-link {
	text-align: center;
	margin-top: 20px;
}

.home-link a {
	text-decoration: none;
	color: #555;
}
</style>

</head>

<body>


	<div class="container">

		<div class="logo">

			<h1>AHAR</h1>

			<p>Delicious Food Delivered Fast</p>

		</div>

		<%
		String error = request.getParameter("error");

		if (error != null) {
		%>

		<div class="error">

			<%=error%>

		</div>

		<%
		}
		%>

		<%
		if (request.getParameter("success") != null) {
		%>

		<div
			style="background: #e8ffe8; color: green; padding: 10px; text-align: center; margin-bottom: 15px; border-radius: 5px;">

			Password Updated Successfully</div>

		<%
		}
		%>

		<form action="login" method="post">

			<input type="email" name="email" placeholder="Enter Email" required>

			<input type="password" name="password" placeholder="Enter Password"
				required>

			<button type="submit">Login</button>

		</form>

		<div class="links">

			<a href="register.jsp"> Create Account </a> <a
				href="forgotPassword.jsp"> Forgot Password? </a>

		</div>

		<div class="home-link">

			<br> <a href="home"> ← Back To Home </a>

		</div>

	</div>

</body>
</html>