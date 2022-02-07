<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link rel="stylesheet" href="css/main.css" type="text/css">
<link rel="stylesheet" href="css/all.css" type="text/css">
<link rel="stylesheet" href="css/login.css" type="text/css">

<title>Travel Community</title>
</head>

<body>
	<c:import url="header.jsp" />
	<div class="login-form">
		<h1 class="join-h1">회원가입</h1>
		<div class="form-div">
			<form action="service" method="POST">
				<div class="join-inputbox">
					<input type="hidden" name="command" value="join">
					<div class="join-area">
						<label class="join-label" for="joinId">아이디</label><br> <input
							type="text" name="id" id="id"
							placeholder="${sessionScope.checkId}"
							value="${sessionScope.user.id}" autocomplete="off" required>
					</div>
					<div class="join-area">
						<label class="join-label" for="joinPw">비밀번호</label><br> <input
							type="password" name="pw" id="pw" value="${sessionScope.user.pw}"
							autocomplete="off" required>
					</div>
					<div class="join-area">
						<label class="join-label" for="joinName">이름</label><br> <input
							type="text" name="userName" id="userName"
							value="${sessionScope.user.userName}" autocomplete="off" required>
					</div>
					<div class="join-area">
						<label class="join-label">전화번호</label> <br> <input
							type="text" name="tel" id="tel"
							onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"
							maxlength=11 placeholder="${sessionScope.checkTel}"
							value="${sessionScope.user.tel}" />
					</div>
					<div class="joinBtn-area">
						<!-- <button type="submit">회원가입</button> -->
						<input class="join-btn" type="submit" value="회원가입">
					</div>
				</div>
				<!-- end login-inputbox -->
			</form>
		</div>
	</div>
	<div class="footer-div" >
		<c:import url="footer.jsp" />
	</div>
</body>
</html>