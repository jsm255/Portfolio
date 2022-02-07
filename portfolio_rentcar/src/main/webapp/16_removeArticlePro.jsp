<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
</head>
<body>
<%
int articleNum = Integer.parseInt(request.getParameter("articleNum"));

BoardDAO bDao = BoardDAO.getInstance();
if(bDao.removeArticle(articleNum)) response.sendRedirect("12_boardList.jsp");
else response.sendRedirect(String.format("13_boardView.jsp?articleNum=%d&action=removeError",articleNum));
%>
</body>
</html>