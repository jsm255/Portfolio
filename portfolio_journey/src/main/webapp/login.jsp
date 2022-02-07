<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Travel Community</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<link rel="stylesheet" href="css/main.css" type="text/css">
 <link rel="stylesheet" href="css/all.css" type="text/css">
    <link rel="stylesheet" href="css/login.css" type="text/css">
</head>
<body>

<c:import url="header.jsp"/>
<%
if(request.getParameter("error") != null) {
	if(request.getParameter("error").equals("needLogin")){
	%>
	<script>alert("로그인 후 사용할 수 있는 메뉴입니다.")</script>
	<%
	}
}
%>

<div class = "login-form" >
    	
        <h1>로그인</h1>
        
        <h2> ${sessionScope.msg} </h2>
        
        <div class = "login-inputbox">
        <form action="service" method = "post">
        
        <input type="hidden" name="command" value="login">
        
            <div class="int-area">
            <label class="join-label" for="joinId">아이디</label> <br>
                <input type="text" name="id" id="id" autocomplete="off" required/>
            </div>
            <div class="int-area">
            <label class="join-label" for="joinId">비밀번호</label> <br>
                <input type="password" name="pw" id="pw" autocomplete="off" required/> 
            </div>
            
            <div class = "btn-area">
                <input class="login-btn" type="submit" value="로그인">
            </div>

        </form>
        </div> <!-- end login-inputbox div -->
        </div> <!-- end login-form div -->
        <div class="footer-div" >
        <c:import url="footer.jsp"/>
        </div>
        
</body>
</html>