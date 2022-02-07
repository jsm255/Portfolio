<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@page import="user.UserDTO"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
textarea{
width: 400px;
height: 300px;
resize: none;
}
</style>
<title>게시글 수정</title>
</head>
<body>
	<%
	UserDAO uDao = UserDAO.getInstance();
	UserDTO user = null;
	
	int articleNum = Integer.parseInt(request.getParameter("articleNum"));
	
	BoardDAO bDao = BoardDAO.getInstance();
	BoardDTO article = bDao.getArticle(articleNum);
	
	String title = article.getTitle();
	String content = article.getContent();
	String pw = article.getPw();
	
	// 로그인이 되어있지 않으면 로그인화면으로 리다이렉트
	if(session.getAttribute("log") == null) {
		session.setAttribute("before", "modifyArticle");
		session.setAttribute("articleNum", articleNum);
		response.sendRedirect("04_login.jsp?action=pleaseLogin");
	}
	// 만약 세션은 로그인이 되어있는데 static 변수 UserDAO.log 와 값이 다를때(강제 초기화 및 재로그인 요청)
	else if(session.getAttribute("log") != null && Integer.parseInt(String.valueOf(session.getAttribute("log"))) != UserDAO.log) {
		session.removeAttribute("log");
		session.setAttribute("before", "modifyArticle");
		session.setAttribute("articleNum", articleNum);
		response.sendRedirect("04_login.jsp?action=pleaseLogin");
	}
	else user = uDao.getUser(Integer.parseInt(String.valueOf(session.getAttribute("log"))));
	
	if(request.getParameter("action") != null) {
		if(request.getParameter("action").equals("fail")) {
			%>
			<script>alert("오류가 발생했습니다. 글을 복구했습니다.")</script>
			<%
			title = String.valueOf(session.getAttribute("title"));
			content = String.valueOf(session.getAttribute("content"));
			pw = String.valueOf(session.getAttribute("pw"));
			
			session.removeAttribute("title");
			session.removeAttribute("content");
			session.removeAttribute("pw");
		}
	}
	%>
    <form method="post" action="18_modifyArticlePro.jsp?articleNum=<%=articleNum %>">
        <table>
            <tr>
                <th>게시글 작성</th>
            </tr>
            <tr><td>게시글 제목</td><td><input type="text" required name="title" value=<%=title %>></td></tr>
            <tr><td>글 비밀번호</td><td><input type="password" required name="pw" value=<%=pw %>></td></tr>
            <tr><td colspan=2><textarea name="content"><%=content %></textarea></td></tr>
            <tr><td colspan=2><span>* 비밀번호는 글을 삭제하거나 수정할 때 꼭 필요합니다! </span></td></tr>
            <tr><td><input type="submit" value="글 작성하기"></td></tr>
        </table>
        <%
        if(user != null) {
        	%>
	        <input type="hidden" name="userName" value=<%=user.getUserName() %>>
        	<%
        }
        %>
    </form>
</body>
</html>