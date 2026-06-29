<%@page import="java.util.List"%>
<%@page import="com.tap.model.User"%>

<%
List<User> users = (List<User>) request.getAttribute("users");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Management</title>

<style>
body {
	font-family: Arial;
	background: #f5f5f5;
}

.container {
	width: 95%;
	margin: auto;
	padding: 20px;
}

h1 {
	color: #ff5200;
}

table {
	width: 100%;
	background: white;
	border-collapse: collapse;
}

th {
	background: #ff5200;
	color: white;
	padding: 12px;
}

td {
	padding: 12px;
	text-align: center;
	border: 1px solid #ddd;
}

.btn {
	background: red;
	color: white;
	padding: 8px 12px;
	text-decoration: none;
	border-radius: 5px;
}

.home {
	background: #ff5200;
	padding: 10px 15px;
	color: white;
	text-decoration: none;
	border-radius: 5px;
}
</style>
</head>

<body>


	<div class="container">

		<a href="adminDashboard" class="home">Dashboard</a> <br>
		<br>

		<h1>User Management</h1>

		<table>

			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Role</th>
				<th>Address</th>
				<th>Action</th>
			</tr>

			<%
        if(users != null){

            for(User user : users){
        %>

			<tr>

				<td><%=user.getUserId()%></td>
				<td><%=user.getUsername()%></td>
				<td><%=user.getEmail()%></td>
				<td><%=user.getRole()%></td>
				<td><%=user.getAddress()%></td>

				<td><a href="deleteUser?userId=<%=user.getUserId()%>"
					class="btn" onclick="return confirm('Delete this user?')">

						Delete </a></td>

			</tr>

			<%
            }
        }
        %>

		</table>

	</div>

</body>
</html>