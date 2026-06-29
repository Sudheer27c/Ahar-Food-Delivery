<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="loader">

	<div class="loader-box">

		<img src="<%=request.getContextPath()%>/images/ahar-logo.png"
			class="loader-logo">

		<div class="spinner"></div>

		<h2>AHAR FOOD DELIVERY</h2>

		<p>Preparing Delicious Food...</p>

	</div>

</div>

<style>
#loader {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: #ffffff;
	display: flex;
	justify-content: center;
	align-items: center;
	z-index: 999999;
	transition: opacity .5s;
}

.loader-box {
	text-align: center;
}

.loader-logo {
	width: 90px;
	height: 90px;
	border-radius: 50%;
	margin-bottom: 20px;
}

.spinner {
	width: 70px;
	height: 70px;
	margin: auto;
	border: 7px solid #eeeeee;
	border-top: 7px solid #ff5200;
	border-radius: 50%;
	animation: spin .8s linear infinite;
}

@
keyframes spin { 100%{
	transform: rotate(360deg);
}

}
.loader-box h2 {
	margin-top: 20px;
	color: #ff5200;
	font-size: 32px;
}

.loader-box p {
	margin-top: 10px;
	font-size: 18px;
	color: #666;
	animation: blink 1.2s infinite;
}

@
keyframes blink { 50%{
	opacity: .3;
}

}
.loader-hide {
	opacity: 0;
	visibility: hidden;
}
</style>

<script>

window.addEventListener("load",function(){

    setTimeout(function(){

        document.getElementById("loader").classList.add("loader-hide");

    },500);

});

</script>