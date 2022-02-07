<%@page import="car.CarDTO"%>
<%@page import="car.CarDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<style>
        span{white-space: pre;}
    </style>

<title>렌트 상세 페이지</title>
</head>
<body>
<%
String carCode = "";

int month = 1;

// 월을 입력했었다면 입력했었던 정보를 덮어씌움
if(session.getAttribute("month") != null) month = Integer.parseInt(String.valueOf(session.getAttribute("month")));

// 차도 골랐었을테니 얘도 덮어씌움
if(session.getAttribute("carCode") != null) {
	if(request.getParameter("carCode") != null) {
		if(String.valueOf(session.getAttribute("carCode")).equals(request.getParameter("carCode")) == false) {
			carCode = request.getParameter("carCode");
		}
	}
	else {
		carCode = String.valueOf(session.getAttribute("carCode"));
	}
}
// 그건 아니었으면 request를 통해 코드가 넘어왔을테니 그걸 덮어씌움
else {
	carCode = request.getParameter("carCode");
}

CarDAO cDao = CarDAO.getInstance();
CarDTO chosen = cDao.getCar(carCode);
%>

    <div>
	    <form method="post" action="09_rentPro.jsp?carCode=<%=carCode %>">
	        <table>
	            <tr>
	                <td><img src=<%=chosen.getImgPath() %>></td>
	            </tr>
	            <tr>
	                <td><span>이름 : &#9;</span><%=chosen.getCarName() %></td>
	            </tr>
	            <tr>
	                <td><span>기간 : &#9;</span><input id="month" name="month" type="number" min=1 max=60 required onchange="getPrice()" value=<%=month %>> 개월</td>
	            </tr>
	            <tr>
	                <td id="price"><span>가격 : &#9;</span></td>
	            </tr>
	            <tr>
	            	<td><input type="submit" value="확정하기"></td>
	            </tr>
	        </table>
        </form>
    </div>
    
    <script>
    let changed = false;
    getPrice();
    function getPrice(){
    	const month = document.querySelector("#month").value;
    	const price = (<%=chosen.getPrice() %>*month).toString();
    	let newPrice = "";
		if(price.length > 3) {
			for(let i = 0; i<price.length; i++) {
				newPrice += price.charAt(i);
				// 마지막 숫자가 아니고 뒤에 숫자가 3의 배수로 남았다면 ,를 찍어줌
				if((price.length-1 -i) % 3 == 0 && (price.length-1) != i) newPrice += ",";
				
			}
		}
		else {
			for(let i = 0; i<price.length; i++) {
				newPrice += price.charAt(i);
			}
		}
		newPrice += " 원";
		const h3 = document.createElement("h3");
		h3.setAttribute("id", "child");
		h3.appendChild(document.createTextNode(newPrice));
    	
    	
    	if(changed === false) {
    		changed = true;
    		document.querySelector("#price").appendChild(h3);
    	}
    	else {
    		document.querySelector("#child").remove();
    		document.querySelector("#price").appendChild(h3);
    	}
    
    }
    
    </script>
</body>
</html>