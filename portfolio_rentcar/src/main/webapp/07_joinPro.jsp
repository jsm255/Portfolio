<%@page import="user.UserDAO"%>
<%@page import="user.UserDTO"%>
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
request.setCharacterEncoding("utf-8");

String id = request.getParameter("id");
String pw = request.getParameter("pw");
String userName = request.getParameter("userName");
int age = Integer.parseInt(request.getParameter("age"));

session.setAttribute("id", id);
session.setAttribute("pw", pw);
session.setAttribute("userName", userName);
session.setAttribute("age", age);

UserDTO user = new UserDTO(id, pw, userName, age);

UserDAO uDao = UserDAO.getInstance();

final int DUPLICATE = -1;
final int ERROR = -2;
final int NP = 0;

int result = uDao.join(user);

if(result == NP){
	response.sendRedirect("04_login.jsp");
	
	session.removeAttribute("id");
	session.removeAttribute("pw");
	session.removeAttribute("userName");
	session.removeAttribute("age");
}
else if(result == ERROR){
	response.sendRedirect("06_join.jsp?action=error");
}
else if(result == DUPLICATE){
	response.sendRedirect("06_join.jsp?action=duplicate");
}
%>
</body>
</html>