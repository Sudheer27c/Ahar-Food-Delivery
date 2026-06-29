<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Not Found</title>

<style>
body {
	margin: 0;
	background: #f5f5f5;
	font-family: Arial, sans-serif;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	text-align: center;
}

.container img {
	width: 300px;
	margin-bottom: 20px;
}

h1 {
	font-size: 55px;
	color: #222;
}

p {
	font-size: 20px;
	color: #666;
	margin-bottom: 30px;
}

.btn {
	background: #ff5200;
	color: white;
	text-decoration: none;
	padding: 15px 35px;
	border-radius: 8px;
	font-size: 18px;
}
</style>

</head>
<body>

	<div class="container">

		<img src="images/notfound.png">

		<h1>Page Not Found</h1>

		<p>Uh-oh! Looks like the page you're trying to access doesn't
			exist.</p>

		<a href="<%=request.getContextPath()%>/home">GO HOME</a>

	</div>

</body>
</html>