<%@page import="com.tap.model.User"%>

<%
User user = (User) session.getAttribute("loggedInUser");

if (user == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile - Ahar</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Segoe UI', sans-serif;
}

body {
	background: #f5f5f5;
}

/* HEADER */
.profile-header {
	background: #ff5200;
	color: white;
	padding: 40px 80px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.user-info h1 {
	font-size: 50px;
	margin-bottom: 10px;
}

.user-info p {
	font-size: 22px;
}

.edit-btn {
	background: white;
	color: #ff5200;
	text-decoration: none;
	padding: 15px 30px;
	border-radius: 10px;
	font-weight: bold;
	font-size: 18px;
}

.edit-btn:hover {
	background: #fff2e8;
}

/* MAIN */
.container {
	width: 92%;
	margin: 40px auto;
	display: flex;
	gap: 30px;
}

/* SIDEBAR */
.sidebar {
	width: 280px;
	background: white;
	border-radius: 15px;
	padding: 25px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, .1);
}

.sidebar ul {
	list-style: none;
}

.sidebar li {
	margin-bottom: 15px;
}

.sidebar a {
	display: block;
	text-decoration: none;
	color: #333;
	font-size: 18px;
	font-weight: 600;
	padding: 15px;
	border-radius: 10px;
	transition: .3s;
}

.sidebar a:hover {
	background: #ff5200;
	color: white;
}

.sidebar i {
	width: 30px;
}

/* CONTENT */
.content {
	flex: 1;
	background: white;
	border-radius: 15px;
	padding: 35px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, .1);
}

.content h2 {
	font-size: 45px;
	margin-bottom: 30px;
	color: #222;
}

.profile-card {
	border: 1px solid #eee;
	border-radius: 15px;
	padding: 40px;
}

.profile-row {
	display: flex;
	align-items: center;
	margin-bottom: 25px;
	font-size: 24px;
}

.label {
	width: 180px;
	font-weight: 700;
	color: #ff5200;
}

.value {
	color: #222;
	font-weight: 500;
}
</style>

</head>

<body>

	
	<div class="profile-header">

		<div class="user-info">
			<h1><%=user.getUsername()%></h1>
			<p><%=user.getEmail()%></p>
		</div>

		<a href="editProfile.jsp" class="edit-btn"> EDIT PROFILE </a>

	</div>

	<div class="container">

		<!-- SIDEBAR -->

		<div class="sidebar">

			<ul>

				<li><a href="orderHistory"> <i class="fa-solid fa-box"></i>
						Orders
				</a></li>

				<li><a href="favourites"> <i class="fa-solid fa-heart"></i>
						Favorites
				</a></li>

				<li><a href="changePassword.jsp"> <i
						class="fa-solid fa-lock"></i> Change Password
				</a></li>

				<li><a href="home"> <i class="fa-solid fa-house"></i> Home
				</a></li>

				<li><a href="logout"> <i
						class="fa-solid fa-right-from-bracket"></i> Logout
				</a></li>

			</ul>

		</div>

		<!-- CONTENT -->

		<div class="content">

			<h2>My Profile</h2>

			<div class="profile-card">

				<div class="profile-row">
					<span class="label">Name</span> <span class="value"><%=user.getUsername()%></span>
				</div>

				<div class="profile-row">
					<span class="label">Email</span> <span class="value"><%=user.getEmail()%></span>
				</div>

				<div class="profile-row">
					<span class="label">Role</span> <span class="value"><%=user.getRole()%></span>
				</div>

				<div class="profile-row">
					<span class="label">Address</span> <span class="value"><%=user.getAddress()%></span>
				</div>
				

			</div>

		</div>

	</div>

</body>
</html>