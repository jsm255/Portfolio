<%@page import="controllers.UserDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

 <link rel="stylesheet" href="css/all.css" type="text/css">
<link rel="stylesheet" href="css/modifyReview.css" type="text/css">

<title>댓글 수정</title>
</head>
<body>
<c:import url="header.jsp"/>

<div id="contents">
<%
String content = "";
int score = 5;
if(request.getParameter("error") != null) {
	String error = request.getParameter("error");
	
	if(error.equals("pw")) {
		%>
		<script>alert("글의 비밀번호가 일치하지 않습니다.")</script>
		<%
		content = String.valueOf(session.getAttribute("content"));
		score = Integer.parseInt(String.valueOf(session.getAttribute("score")));
	}
}

ReviewDTO review = (ReviewDTO)session.getAttribute("review");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String date = sdf.format(review.getDate());
int code = Integer.parseInt(String.valueOf(session.getAttribute("code")));

String id = "Guest";
if(session.getAttribute("log") != null) 
	id = String.valueOf(session.getAttribute("log"));

UserDAO uDao = UserDAO.getInstance();
%>

<div id="before">
	<table>
		<tr><th> 수정 전 리뷰 </th></tr>
    	<tr><td>리뷰 국가 : <%=review.getCountryName() %></td>
    	<td>유저 이름 : <%=review.getId() %></td>
    	<td>평가 점수 : <%=review.getScore() %> 점</td></tr>
   		<tr><td colspan="3">유저 리뷰 : <%=review.getContent()%></td></tr>
    	<tr><td colspan="2">리뷰 날짜 : <%=date %></td></tr>
    </table>
</div>

<div id="after">
        	<form method="post" action="service">
        		<table>
        			<tr>
        				<th> 리뷰 수정하기 </th>
        			</tr>
        			<tr>
        				<td>
        					<input id="range" type="range" min=1 max=10 value=<%=score %> name="score"><span id="child">5점</span>
        				</td>
        			</tr>
        			<tr>
        				<td><textarea name="content" placeholder="내용"><%=content %></textarea></td>
        			</tr>
        			<tr>
        				<td>
        					<%
        						if(id.equals("Guest")) {
        							%>
        								<input name="beforePw" type="password" placeholder="이전 비밀번호" required>
        								<input name="afterPw" type="password" placeholder="새 비밀번호"></td>
        								<tr><td colspan=2><span>비밀번호를 바꾸려면 새 비밀번호를, 바꾸지 않으려면 공란으로 두세요.</span></td></tr>
        								<tr><td>
        							<%
        						}
        					%>
        					<input type="submit" value="댓글 적기">
        				</td>
        			</tr>
        		</table>
        		<input type="hidden" name="countryName" value=<%=review.getCountryName() %>>
        		<input type="hidden" name="id" value=<%=id %>>
        		<input type="hidden" name="code" value=<%=code %>>
        		<%
        		if(!id.equals("Guest")) {
        			%>
        			<input type="hidden" name="userCode" value=<%=uDao.getUserCodeById(id) %>>
        			<%
        		}
        		%>
        		<input type="hidden" name="command" value="modifyReviewSubmit">
        	</form>
</div>
</div>
<c:import url="footer.jsp"/>
<script>
$("#range").change(e => {
   	const getter = e.target.value;
   	console.log(getter);
   	const span = document.createElement("span");
   	$(span).attr("id", "child");
   	$(span).append(document.createTextNode(getter+"점"));
   	$("#child").replaceWith(span);
});
</script>
</body>
</html>