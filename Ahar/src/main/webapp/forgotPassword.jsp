<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Forgot Password - AHAR</title>

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: Arial;
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

h2 {
	text-align: center;
	color: #ff5200;
	margin-bottom: 25px;
}

input {
	width: 100%;
	padding: 12px;
	margin: 10px 0;
	border: 1px solid #ccc;
	border-radius: 8px;
}

button {
	width: 100%;
	padding: 12px;
	background: #ff5200;
	color: white;
	border: none;
	border-radius: 8px;
	font-size: 16px;
	cursor: pointer;
}

button:hover {
	background: #e64a00;
}

.error {
	color: red;
	text-align: center;
	margin-bottom: 15px;
}

.success {
	color: green;
	text-align: center;
	margin-bottom: 15px;
}

a {
	text-decoration: none;
	color: #ff5200;
}
</style>

</head>

<body>

	<div class="container">

		<h2>Reset Password</h2>

		<%
		if (request.getParameter("error") != null) {
		%>

		<div class="error">Email Not Registered</div>

		<%
		}
		%>

		<%
		if (request.getParameter("success") != null) {
		%>

		<div class="success">Password Updated Successfully</div>

		<%
		}
		%>

		<form action="forgotPassword" method="post">

			<input type="email" name="email" placeholder="Enter Email" required>

			<input type="password" name="newPassword"
				placeholder="Enter New Password" required>

			<button type="submit">Update Password</button>

		</form>

		<br>

		<center>

			<a href="login.jsp"> Back To Login </a>

		</center>

	</div>

</body>
</html>