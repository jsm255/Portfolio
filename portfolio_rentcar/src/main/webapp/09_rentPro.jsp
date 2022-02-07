<%@page import="user.UserDAO"%>
<%@page import="user.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="car.CarDTO"%>
<%@page import="car.CarDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Progress</title>
</head>
<body>
<%
String carCode = request.getParameter("carCode");
CarDAO cDao = CarDAO.getInstance();

int month = Integer.parseInt(request.getParameter("month"));

// 로그인이 되어있지 않으면 로그인화면으로 리다이렉트
if(session.getAttribute("log") == null) {
	session.setAttribute("carCode", carCode);
	session.setAttribute("month", month);
	session.setAttribute("before", "rent");
	response.sendRedirect("04_login.jsp?action=pleaseLogin");
}
// 만약 세션은 로그인이 되어있는데 static 변수 UserDAO.log 와 값이 다를때(강제 초기화 및 재로그인 요청)
else if(session.getAttribute("log") != null && Integer.parseInt(String.valueOf(session.getAttribute("log"))) != UserDAO.log) {
	session.removeAttribute("log");
	session.setAttribute("carCode", carCode);
	session.setAttribute("month", month);
	session.setAttribute("before", "rent");
	response.sendRedirect("04_login.jsp?action=pleaseLogin");
}
else {
	CarDTO chosen = cDao.getCar(carCode);

	int pay = chosen.getPrice() * Integer.parseInt(request.getParameter("month"));

	UserDAO uDao = UserDAO.getInstance();
	UserDTO user = uDao.getUser(Integer.parseInt(String.valueOf(session.getAttribute("log"))));
	cDao.carRented(chosen);
	uDao.userRented(user, pay);

	response.sendRedirect(String.format("10_resultPage.jsp?carCode=%s&month=%d",carCode, month));
}
%>
</body>
</html>