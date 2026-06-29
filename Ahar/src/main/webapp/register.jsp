<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ahar Registration</title>

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
	width: 450px;
	background: white;
	padding: 30px;
	border-radius: 12px;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
}

h2 {
	text-align: center;
	color: #ff5200;
	margin-bottom: 20px;
}

input, select {
	width: 100%;
	padding: 12px;
	margin: 10px 0;
	border: 1px solid #ccc;
	border-radius: 6px;
}

button {
	width: 100%;
	padding: 12px;
	background: #ff5200;
	color: white;
	border: none;
	border-radius: 6px;
	font-size: 16px;
	cursor: pointer;
}

button:hover {
	background: #e64a00;
}

.links {
	text-align: center;
	margin-top: 15px;
}

.links a {
	text-decoration: none;
	color: #ff5200;
	font-weight: bold;
}

.message {
	text-align: center;
	color: red;
	margin-bottom: 10px;
}
</style>

</head>

<body>

	<div class="container">

		<h2>Create Ahar Account</h2>

		<%
		String error = (String) request.getAttribute("error");
		if (error != null) {
		%>
		<div class="message">
			<%=error%>
		</div>
		<%
		}
		%>

		<form action="register" method="post">

			<input type="text" name="name" placeholder="Enter Full Name" required>

			<input type="email" name="email" placeholder="Enter Email" required>

			<input type="password" name="password" placeholder="Enter Password"
				required> <input type="password" name="confirmPassword"
				placeholder="Confirm Password" required> <input type="text"
				name="address" placeholder="Enter Address" required> <select
				name="role">

				<option value="customer">Customer</option>

				<option value="admin">Admin</option>

			</select>

			<button type="submit">Create Account</button>

		</form>

		<div class="links">

			<p>
				Already have an account? <a href="login.jsp">Login</a>
			</p>

			<br>

			<p>
				<a href="home">Back To Home</a>
			</p>

		</div>

	</div>

</body>
</html>