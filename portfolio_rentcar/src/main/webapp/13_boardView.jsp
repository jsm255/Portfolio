<%@page import="board.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
BoardDAO bDao = BoardDAO.getInstance();
// ArrayList<BoardDTO> board = bDao.getBoard();

BoardDTO article = bDao.getArticle(Integer.parseInt(request.getParameter("articleNum")));

int pageNum = 1;
if(session.getAttribute("pageNum") != null) 
	pageNum = Integer.parseInt(String.valueOf(session.getAttribute("pageNum")));
else if(request.getParameter("pageNum") != null) {
	session.setAttribute("pageNum", Integer.parseInt(request.getParameter("pageNum")));
	pageNum = Integer.parseInt(String.valueOf(session.getAttribute("pageNum")));
}
if(request.getParameter("action") != null) {
	String action = request.getParameter("action");
	if(action.equals("view")){
		article.setView(bDao.increaseView(article));
	}
	else if(action.equals("like")){
		article.setLike(bDao.increaseLike(article));
	}
	else if(action.equals("removeError")) {
		%>
		<script>alert("삭제 중에 오류가 발생했습니다.")</script>
		<%
	}
}
System.out.println(article.getView());
System.out.println(article.getLike());
%>

<title><%=article.getTitle() %> - 렌트카 게시판</title>
</head>
<body>
    <table>
        <tr>
            <th colspan=4>게시글 제목</th>
        </tr>
        <tr>
            <td><span>닉네임&#9;</span></td><td><%=article.getUserName()%></td>
            <td>작성 시간&#9;</td><td><%=article.getTime()%></td>
        </tr>
        <tr>
            <td>조회 수&#9;</td><td id="viewParent"><span id="view"><%=article.getView()%></span></td>
            <td>좋아요 수&#9;</td><td id="likeParent"><span id="like"><%=article.getLike()%></span></td>
        </tr>
        <tr>
            <td height=300px colspan=4><%=article.getContent()%></td>
        </tr>
        <tr>
        	<td><button onclick="location.href='13_boardView.jsp?articleNum=<%=article.getNum()%>&action=like'">좋아요!</button></td>
        	<td><button onclick="location.href='19_enterPassword.jsp?articleNum=<%=article.getNum() %>&action=modify'">글 수정</button></td>
        	<td><button onclick="location.href='19_enterPassword.jsp?articleNum=<%=article.getNum() %>&action=remove'">글 삭제</button></td>
        	<td><button onclick="location.href='12_boardList.jsp?pageNum=<%=pageNum%>'">목록으로</button></td>
        </tr>
        
    </table>
    
    <script>
//     	// JS JAVA 이용해서 JSON 으로 좋아요 실시간 반영 구현
//     	// java 함수로 구현해놨으니 js에서 java함수에 접근해야된다는 말
//     	let view = 0;
//     	let like = 0;
    	
//     	function increaseLike(num){
<%--     		const like = "<%= bDao.increaseLike(article) %>"; --%>
    		
//     		document.querySelector("#like").remove();
    		
//     		const span = document.createElement("span");
//     		span.setAttribute("id", "like");
//     		span.appendChild(document.createTextNode(like));
    		
//     		document.querySelector("#likeParent").appendChild(span);
//     	}
    </script>
</body>
</html>