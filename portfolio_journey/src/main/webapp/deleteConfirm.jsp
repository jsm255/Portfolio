<%@page import="controllers.UserDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.ReReviewDTO"%>
<%@page import="models.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

 <link rel="stylesheet" href="css/all.css" type="text/css">
<link rel="stylesheet" href="css/deleteConfirm.css" type="text/css">

<title>글 삭제 비밀번호 입력</title>
</head>
<body>

<c:import url="header.jsp"/>

<%
String delete = "";
if(session.getAttribute("review") != null) {
	delete = "review";
}
else if(session.getAttribute("rrview") != null) {
	delete = "rrview";
}

if(request.getParameter("error") != null) {
	String error = request.getParameter("error");
	if(error.equals("pw"))
		%><script>alert("비밀번호가 틀렸습니다.")</script><%
	else if(error.equals("confirm"))
		%><script>alert("'확인'을 입력해주세요.")</script><%
}

String id = "Guest";
if(session.getAttribute("log") != null) 
	id = String.valueOf(session.getAttribute("log"));

String countryName = request.getParameter("countryName");

UserDAO uDao = UserDAO.getInstance();

if(id.equals("Guest")) {
	if(delete.equals("review")) {
		ReviewDTO review = (ReviewDTO) session.getAttribute("review");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(review.getDate());
		%>
		<div id="review">
		<table>
			<tr><th> 지울 리뷰 </th></tr>
	    	<tr><td>리뷰 국가 : <%=review.getCountryName() %></td>
	    	<td>유저 이름 : <%=review.getId() %></td>
	    	<td>평가 점수 : <%=review.getScore() %> 점</td></tr>
	   		<tr><td colspan="3">유저 리뷰 : <%=review.getContent()%></td></tr>
	    	<tr><td colspan="2">리뷰 날짜 : <%=date %></td></tr>
	    </table>
	    <form method="post" action="service">
	    	<input type="password" name="pw" placeholder="글의 비밀번호" required>
	    	<input type="hidden" name="command" value="deleteReview">
	    	<input type="hidden" name="additional" value="confirmed">
	    	<input type="hidden" name="code" value=<%=review.getCode() %>>
	    	<input type="hidden" name="countryName" value=<%=countryName %>>
	    	<input type="submit" value="제출하기">
	    </form>
	</div>
	<%
	}
	else if(delete.equals("rrview")) {
		ReReviewDTO rrview = (ReReviewDTO) session.getAttribute("rrview");
		%>
		<div id="rrview">
		<table>
			<tr><th> 지울 답글 </th></tr>
	    	<tr><td>유저 이름 : <%=rrview.getId() %></td>
	   		<tr><td colspan="3">유저 리뷰 : <%=rrview.getContent()%></td></tr>
	    	<tr><td colspan="2">리뷰 날짜 : <%=rrview.getDate() %></td></tr>
	    </table>
	    <form method="post" action="service">
	    	<input type="password" name="pw" placeholder="글의 비밀번호" required>
	    	<input type="hidden" name="command" value="deleteReReview">
	    	<input type="hidden" name="additional" value="confirmed">
	    	<input type="hidden" name="code" value=<%=rrview.getCode() %>>
	    	<input type="hidden" name="countryName" value=<%=countryName %>>
	    	<input type="submit" value="제출하기">
	    </form>
	</div>
	<%
	}
}
else {
	if(delete.equals("review")) {
		ReviewDTO review = (ReviewDTO) session.getAttribute("review");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(review.getDate());
		%>
		<div id="review">
		<table>
			<tr><th> 지울 리뷰 </th></tr>
	    	<tr><td>리뷰 국가 : <%=review.getCountryName() %></td>
	    	<td>유저 이름 : <%=review.getId() %></td>
	    	<td>평가 점수 : <%=review.getScore() %> 점</td></tr>
	   		<tr><td colspan="3">유저 리뷰 : <%=review.getContent()%></td></tr>
	    	<tr><td colspan="2">리뷰 날짜 : <%=date %></td></tr>
	    </table>
	    <form method="post" action="service">
	    	<input type="text" name="confirm" placeholder="지우려면 '확인'을 입력하세요" required>
	    	<input type="hidden" name="command" value="deleteReview">
	    	<input type="hidden" name="additional" value="confirmed">
	    	<input type="hidden" name="code" value=<%=review.getCode() %>>
	    	<input type="hidden" name="countryName" value=<%=countryName %>>
	    	<%
        		if(!id.equals("Guest")) {
        			%>
        			<input type="hidden" name="userCode" value=<%=uDao.getUserCodeById(id) %>>
        			<%
        		}
        		%>
	    	<input type="submit" value="삭제하기">
	    </form>
	</div>
	<%
	}
	else if(delete.equals("rrview")) {
		ReReviewDTO rrview = (ReReviewDTO) session.getAttribute("rrview");
		%>
		<div id="rrview">
		<table>
			<tr><th> 지울 답글 </th></tr>
	    	<tr><td>유저 이름 : <%=rrview.getId() %></td>
	   		<tr><td colspan="3">유저 리뷰 : <%=rrview.getContent()%></td></tr>
	    	<tr><td colspan="2">리뷰 날짜 : <%=rrview.getDate() %></td></tr>
	    </table>
	    <form method="post" action="service">
	    	<input type="text" name="confirm" placeholder="지우려면 '확인'을 입력하세요" required>
	    	<input type="hidden" name="command" value="deleteReReview">
	    	<input type="hidden" name="additional" value="confirmed">
	    	<input type="hidden" name="code" value=<%=rrview.getCode() %>>
	    	<input type="hidden" name="countryName" value=<%=countryName %>>
	    	<%
        		if(!id.equals("Guest")) {
        			%>
        			<input type="hidden" name="userCode" value=<%=uDao.getUserCodeById(id) %>>
        			<%
        		}
        		%>
	    	<input type="submit" value="제출하기">
	    </form>
	</div>
	<%
	}
}
%>
<c:import url="footer.jsp"/>
</body>
</html>