<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
span{
white-space: pre;
}
form{
display: flex;

width: 100vw;
height: 80vh;
flex-direction: column;
align-items: center;
justify-content: space-evenly;
}
input{
width: 200px;
}
</style>

<title>회원 가입</title>
</head>
<body>
<%
String id = "";
String pw = "";
String userName = "";
int age = 20;

if(request.getParameter("action") != null) {	// 어떤 이유로 실패할 경우 입력 데이터를 복구
	if(request.getParameter("action").equals("duplicate")) {
		%>
		<script>alert("id가 중복되었습니다. 데이터를 복구했습니다.")</script>
		<%
	}

	else if(request.getParameter("action").equals("error")) {
		%>
		<script>alert("어떠한 문제가 발생했습니다. 데이터를 복구했습니다.")</script>
		<%
	}
	id = String.valueOf(session.getAttribute("id"));
	pw = String.valueOf(session.getAttribute("pw"));
	userName = String.valueOf(session.getAttribute("userName"));
	age = Integer.parseInt(String.valueOf(session.getAttribute("age")));
}
%>
    <div>
        <form method="post" action="07_joinPro.jsp">
            <div>
                <span>id : &#9;&#9;</span>
                <input type="text" name="id" required value=<%=id %>>
            </div>
            <div>
                <span>pw : &#9;&#9;</span>
                <input type="password" name="pw" required value=<%=pw %>>
            </div>
            <div>
                <span>name : &#9;</span>
                <input type="text" name="userName" required value=<%=userName %>>
            </div>
            <div>
                <span>age : &#9;&#9;</span>
                <input type="number" name="age" min="20" max="90" required value=<%=age %>>
            </div>
            <input type="submit" value="회원 가입">
        </form>
    </div>
</body>
</html>