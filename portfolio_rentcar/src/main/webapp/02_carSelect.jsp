<%@page import="car.CarDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="car.CarDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
        div{
            display: grid;

            width: 100vw;
            height: 100vh;
            grid-template-columns: 30% 30% 30%;
            grid-template-rows: 30% 30% 30%;
			
			gap : 4px;

            grid-auto-columns: 30%;
            grid-auto-rows: 30%;
        }
        article{
        cursor: pointer;
        }
        img{
        width:100%;
        height:50%;
        object-fit: cover;
        }
</style>

<title>차 선택하기</title>
</head>
<body>
<div>
<%
CarDAO cDao = CarDAO.getInstance();
ArrayList<CarDTO> cars = cDao.getCars();

// 남은 차가 있는지 확인하고 없다면 눌렀을 때 알림창을 표시해줌
for(CarDTO rentCheck : cars) {
		if(rentCheck.getStock() == 0) {
			%>
			<article onclick="alert('모두 렌트되었습니다!')">
				<img src=<%= rentCheck.getImgPath()%>>
				<h4><%=rentCheck.getCarName() %></h4>
				<h5><%=cDao.printPrice(rentCheck) %></h5>
				<h5><span>잔여 대수&#9;: </span><%=rentCheck.getStock() %> 대</h5>
			</article>
			<%
		}
		else {
			%>
			<article onclick="location.href='08_rentPage.jsp?carCode=<%=rentCheck.getCarCode() %>'">
				<img src=<%= rentCheck.getImgPath()%>>
				<h4><%=rentCheck.getCarName() %></h4>
				<h5><%=cDao.printPrice(rentCheck) %></h5>
				<h5><span>잔여 대수&#9;: </span><%=rentCheck.getStock() %> 대</h5>
			</article>
			<%
		}
}
%>
</div>
</body>
</html>