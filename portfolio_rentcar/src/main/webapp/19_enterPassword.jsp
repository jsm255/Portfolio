<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 비밀번호 체크</title>
</head>
<body>
<%
String action = request.getParameter("action");
int articleNum = Integer.parseInt(request.getParameter("articleNum"));

String next = "";
if(action.equals("remove")) next = String.format("16_removeArticlePro.jsp?&articleNum=%d", articleNum);
else if(action.equals("modify")) next = String.format("17_modifyArticle.jsp?&articleNum=%d", articleNum);
%>
<div>
	<form method="post" action=<%=next %>>
		<input type="password" required name="pw">
		<input type="submit" value="비밀번호 확인">
	</form>
</div>
</body>
</html>