<%@page import="controllers.UserDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.ReReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

 <link rel="stylesheet" href="css/all.css" type="text/css">
 <link rel="stylesheet" href="css/modifyReReview.css" type="text/css">

<title>답글 수정</title>
</head>
<body>

<c:import url="header.jsp"/>

<div id="contents">
<%
String content = "";
if(request.getParameter("error") != null) {
	String error = request.getParameter("error");
	
	if(error.equals("pw")) {
		%>
		<script>alert("글의 비밀번호가 일치하지 않습니다.")</script>
		<%
		content = String.valueOf(session.getAttribute("content"));
	}
}

ReReviewDTO rrview = null;

if(session.getAttribute("rrview") instanceof ReReviewDTO)
	rrview = (ReReviewDTO)session.getAttribute("rrview");
int rrCode = Integer.parseInt(String.valueOf(session.getAttribute("rrCode")));
String countryName = "";
if(request.getParameter("countryName") != null)
	countryName = request.getParameter("countryName");
else if(session.getAttribute("countryName") != null)
	countryName = String.valueOf(session.getAttribute("countryName"));

String id = "Guest";
if(session.getAttribute("log") != null) 
	id = String.valueOf(session.getAttribute("log"));

UserDAO uDao = UserDAO.getInstance();

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String date = sdf.format(rrview.getDate());
%>

<div id="before">
	<table>
		<tr><th> 수정 전 답글 </th></tr>
    	<tr><td>유저 이름 : <%=rrview.getId() %></td></tr>
   		<tr><td colspan="3">유저 리뷰 : <%=rrview.getContent()%></td></tr>
    	<tr><td colspan="2">리뷰 날짜 : <%=date %></td></tr>
    </table>
</div>

<div id="after">
        	<form method="post" action="service">
        		<table>
        			<tr>
        				<th> 답글 수정하기 </th>
        			</tr>
        			<tr>
        				<td> <textarea name="content" placeholder="내용"><%=content %></textarea> </td>
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
        					<input type="submit" value="답글 적기">
        				</td>
        			</tr>
        		</table>
        		<input type="hidden" name="countryName" value=<%=countryName %>>
        		<input type="hidden" name="id" value=<%=id %>>
        		<input type="hidden" name="code" value=<%=rrview.getCode() %>>
        		<%
        		if(!id.equals("Guest")) {
        			%>
        			<input type="hidden" name="userCode" value=<%=uDao.getUserCodeById(id) %>>
        			<%
        		}
        		%>
        		<input type="hidden" name="additional" value="confirmed">
        		<input type="hidden" name="command" value="modifyReReview">
        	</form>
</div>
</div>
<c:import url="footer.jsp"/>
</body>
</html>