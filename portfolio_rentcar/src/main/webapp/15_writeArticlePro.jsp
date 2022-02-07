<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 올리는 중...</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");	// 게시글이 한글일 경우가 많으니 반드시 인코딩을 해줘야함

String title = request.getParameter("title");
String content = request.getParameter("content");
String userName = request.getParameter("userName");
String pw = request.getParameter("pw");

BoardDAO bDao = BoardDAO.getInstance();
BoardDTO upload = new BoardDTO(title, content, userName, pw);

if(bDao.writeArticle(upload)) {
	response.sendRedirect("12_boardList.jsp");
}
else {
	session.setAttribute("title", title);
	session.setAttribute("content", content);
	session.setAttribute("pw", pw);
	response.sendRedirect("14_writeArticle.jsp?action=fail");
}
%>
</body>
</html>