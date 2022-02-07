<%@page import="user.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

String before = "";
if(session.getAttribute("before") != null) {
	before = String.valueOf(session.getAttribute("before"));	// 이전 페이지가 있으면 변수에 넘겨주고
}

String id = request.getParameter("id");
String pw = request.getParameter("pw");

UserDAO uDao = UserDAO.getInstance();

ArrayList<UserDTO> users = uDao.getUsers();

if(uDao.login(id, pw)) {
	session.setAttribute("log", UserDAO.log);
	session.removeAttribute("before");							// 제대로 입력이 됐다면 퇴장
	if(before.equals("")) response.sendRedirect("01_mainPage.jsp");
	else if(before.equals("myPage")) response.sendRedirect("03_myPage.jsp");
	else if(before.equals("rent")) response.sendRedirect("08_rentPage.jsp");
	else if(before.equals("boardWrite")) response.sendRedirect("14_writeArticle.jsp");
	else if(before.equals("modifyArticle")) {
		int articleNum = Integer.parseInt(String.valueOf(session.getAttribute("articleNum")));
		session.removeAttribute("articleNum");
		response.sendRedirect(String.format("17_modifyArticle.jsp?articleNum=%d",articleNum));
	}
}
else {
	response.sendRedirect("04_login.jsp?action=loginFailed");
}
%>
</body>
</html>