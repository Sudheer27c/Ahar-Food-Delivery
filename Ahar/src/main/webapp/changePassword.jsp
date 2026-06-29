<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="changePassword" method="post">

		Current Password <input type="password" name="oldPassword">

		New Password <input type="password" name="newPassword">

		Confirm Password <input type="password" name="confirmPassword">

		<button type="submit">Change Password</button>

	</form>
</body>
</html>