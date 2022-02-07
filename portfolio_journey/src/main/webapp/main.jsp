<%@page import="controllers.XMLParser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link rel="stylesheet" href="css/main.css" type="text/css">
<link rel="stylesheet" href="css/all.css" type="text/css">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.2.0/css/all.min.css" integrity="sha512-6c4nX2tn5KbzeBJo9Ywpa0Gkt+mzCzJBrE1RB6fmpcsoN+b/w/euwIMuQKNyUoU/nToKN3a8SgNOtPrbW12fug==" crossorigin="anonymous" />
<title>MAIN</title>
</head>
<body>

<%
if(XMLParser.first) {
		XMLParser.first = false;
		XMLParser.main();
	}

if(request.getParameter("error") != null) {
	%>
	<script>alert("존재하지 않는 국가 이름입니다.")</script>
	<%
}
%>
	<div class=wrap>
    <c:import url="header.jsp"/>
    <div class="image">
     <main>
        <h1 class="mainTitle">어디로 떠나실건가요?</h1>
        <form method="get" action="service">
<!--       <select size="1" class="country" name="country"> -->
<!--             <option  value="미국">미국</option> -->
<!--             <option  value="영국">영국</option> -->
<!--             <option  value="일본">일본</option> -->
<!--             <option  value="태국">태국</option> -->
<!--   
          <option  value="중국">중국</option> -->
<!--             <option  value="필리핀">필리핀</option> -->
<!--             <option  value="독일">독일</option> -->
<!--             <option  value="이탈리아">이탈리아</option> -->
<!--             <option  value="그리스">그리스</option> -->
<!--             <option  value="인도">인도</option> -->
<!--         </select> -->
		<input type="text" name="country" id="country" onchange="checkLetter()" required>
        <button type="submit" class="button">
            <span class="button_icon">
                <i class="fas fa-search" name="search_outline"></i>
            </span>
        </button>
       	<input type="hidden" name="command" value="viewCountry">
        </form>
    </main>
    <c:import url="footer.jsp"/>
</div>
</div>
	<script type="text/javascript" src="validation.js"></script>
</body>
</html>