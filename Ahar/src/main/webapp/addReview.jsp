<%@ page contentType="text/html;charset=UTF-8"%>

<%
int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Review</title>

<style>
body {
	font-family: Arial;
	background: #f5f5f5;
}

.container {
	width: 500px;
	margin: 50px auto;
	background: white;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
}

h2 {
	color: #ff5200;
	margin-bottom: 20px;
}

select, textarea {
	width: 100%;
	padding: 10px;
	margin-top: 10px;
	margin-bottom: 20px;
}

button {
	background: #ff5200;
	color: white;
	border: none;
	padding: 12px 20px;
	cursor: pointer;
	border-radius: 5px;
}
</style>

</head>

<body>

	<div class="container">

		<h2>Rate Restaurant</h2>

		<form action="addReview" method="post">

			<input type="hidden" name="restaurantId" value="<%=restaurantId%>">

			<label>Rating</label> <select name="rating" required>

				<option value="5">⭐⭐⭐⭐⭐</option>
				<option value="4">⭐⭐⭐⭐</option>
				<option value="3">⭐⭐⭐</option>
				<option value="2">⭐⭐</option>
				<option value="1">⭐</option>

			</select> <label>Review</label>

			<textarea name="reviewText" rows="5" required></textarea>

			<button type="submit">Submit Review</button>

		</form>

	</div>

</body>
</html>