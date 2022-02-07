<%@page import="board.BoardDAO"%>
<%@page import="board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정 사항 반영중...</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8"); //한글!

String title = request.getParameter("title");
String content = request.getParameter("content");
String userName = request.getParameter("userName");
String pw = request.getParameter("pw");

int articleNum = Integer.parseInt(request.getParameter("articleNum"));

BoardDTO modify = new BoardDTO(title, content, userName, pw);
BoardDAO bDao = BoardDAO.getInstance();
if(bDao.modifyArticle(modify, articleNum)) {
	response.sendRedirect("12_boardList.jsp");
}
else {
	session.setAttribute("title", title);
	session.setAttribute("content", content);
	session.setAttribute("pw", pw);

	response.sendRedirect(String.format("17_modifyArticle.jsp?articleNum=%d&action=fail",articleNum));
}
%>
</body>
</html>