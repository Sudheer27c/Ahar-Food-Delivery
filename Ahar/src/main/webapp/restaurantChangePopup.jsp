<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Replace Cart ?</title>

<style>
body {
	font-family: Arial, sans-serif;
	background: #f5f5f5;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.popup {
	background: white;
	padding: 30px;
	width: 450px;
	border-radius: 15px;
	box-shadow: 0 5px 20px rgba(0, 0, 0, .2);
	text-align: center;
}

h2 {
	color: #ff5200;
	margin-bottom: 15px;
}

p {
	font-size: 18px;
	margin-bottom: 25px;
}

.btns {
	display: flex;
	justify-content: center;
	gap: 15px;
}

.btn {
	padding: 12px 25px;
	border: none;
	cursor: pointer;
	font-size: 16px;
	font-weight: bold;
	border-radius: 8px;
}

.cancel {
	background: #ddd;
}

.confirm {
	background: #ff5200;
	color: white;
}
</style>

</head>

<body>

	<div class="popup">

		<h2>Items already in cart</h2>

		<p>Your cart contains items from another restaurant. Do you want
			to clear the cart and add this item?</p>

		<div class="btns">

			<form action="cancelRestaurantChange">

				<button class="btn cancel">No</button>

			</form>

			<form action="confirmRestaurantChange">

				<button class="btn confirm">Yes Replace Cart</button>

			</form>

		</div>

	</div>

</body>
</html>