<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃 하는중...</title>
</head>
<body>
<%
UserDAO.log = -1;
session.removeAttribute("log");

session.setAttribute("before", "myPage");
response.sendRedirect("04_login.jsp");
%>
</body>
</html>