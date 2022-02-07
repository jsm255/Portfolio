<%@page import="car.CarDTO"%>
<%@page import="car.CarDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과 화면</title>
</head>
<body>



<%
String carCode = request.getParameter("carCode");
int month = Integer.parseInt(request.getParameter("month"));

CarDAO cDao = CarDAO.getInstance();
CarDTO chosen = cDao.getCar(carCode);


// 거래 끝났으니 만약 세션 값이 있다면 제거
if(session.getAttribute("carCode") != null) {
	session.removeAttribute("carCode");
	session.removeAttribute("month");
}
%>
    <div>
    	<h1>렌트 카 거래 내역입니다.</h1>
    	<h4>이미 완료된 거래 내역입니다.</h4>
	    <form method="post" action="01_mainPage.jsp">
	        <table>
	            <tr>
	                <td><img src=<%=chosen.getImgPath() %>></td>
	            </tr>
	            <tr>
	                <td><span>이름 : &#9;</span><%=chosen.getCarName() %></td>
	            </tr>
	            <tr>
	                <td><span>기간 : &#9;</span><%=month %> 개월</td>
	            </tr>
	            <tr>
	                <td id="price"><span>가격 : &#9;</span><%=chosen.getPrice()*month %> 원</td>
	            </tr>
	            <tr>
	            	<td><input type="submit" value="확인"></td>
	            </tr>
	        </table>
        </form>
    </div>

</body>
</html>