<%@page import="user.UserDTO"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
	span{white-space:pre;}
</style>

<title>마이페이지</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	if(UserDAO.log == -1) {	// 로그인이 되어있지 않으면 바로 로그인 페이지로 리다이렉트
		session.setAttribute("before", "myPage");	// 이전 페이지 기억
		response.sendRedirect("04_login.jsp");
	}
	else{
		UserDAO uDao = UserDAO.getInstance();
		UserDTO currentUser = uDao.getUser(Integer.parseInt(String.valueOf(session.getAttribute("log"))));
		
		String id = currentUser.getId();
		String userName = currentUser.getUserName();
		int age = currentUser.getAge();
		int rentCnt = currentUser.getRentCnt();
		String totalPrice = uDao.printPrice(currentUser);
		
		
		%>
		
		    <table>
		        <tr>
		            <th>마이페이지</th>
		        </tr>
		        <tr>
		            <td><span>아이디 &#9;&#9;: </span></td><td><%=id %></td>
		        </tr>
		        <tr>
		            <td><span>유저 이름 &#9;&#9;: </span></td><td><%=userName %></td>
		        </tr>
		        <tr>
		        	<td><span>유저 나이 &#9;&#9;: </span></td><td><%=age %></td>
		        </tr>
		        <tr>
		            <td><span>렌트 횟수 &#9;&#9;: </span></td><td><%=rentCnt %></td>
		        </tr>
		        <tr>
		            <td><span>총 렌트 비용 &#9;: </span></td><td><%=totalPrice %></td>
		        </tr>
		    </table>
		
		<%
		
	}
	%>
	<button onclick="location.href='01_mainPage.jsp'">메인 페이지로</button>
	<button onclick="location.href='11_logoutPro.jsp'">로그아웃</button>
</body>
</html>