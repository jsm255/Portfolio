<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/all.css" type="text/css">
<title>HEADER</title>
</head>
<body>
<header>
        <div class="top">
            <ul class="top-menu">
                <%
            	if(session.getAttribute("log") == null){
            %>
				<li class="join"><a href="join.jsp">
						<p>회원가입</p>
				</a></li>
				<li class="join"><a href="login.jsp">
						<p>로그인</p>
				</a></li>
				<%
           		 }else{
           	%>
				<li class="join"><a href="mypageMain.jsp">
						<p>마이페이지</p>
				</a></li>
				<li class="join"><a href="service?command=logout">
						<p>로그아웃</p>
				</a></li>
				<% }
      		%>
            </ul>
        </div>
        <div>
        <h1 id="title"><a href="main.jsp" id="main">Travel Community</a></h1>
    	</div>
    </header>
    <nav>
        
           <a href="viewCountry.jsp"> <h3>국가 정보</h3></a>
            <a href="blogPage.jsp"><h3>블로그 게시판</h3></a>
<!--             <li><a href="">공지사항</a></li> -->
        
    </nav>
</body>
</html>