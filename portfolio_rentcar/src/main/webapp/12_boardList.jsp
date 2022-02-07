<%@page import="board.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
table{
border: solid 1px black;
border-collapse : collapse;
text-align: center;
}
</style>

<title>게시판</title>
</head>
<body>
<%

	if(session.getAttribute("pageNum") != null) {
		session.removeAttribute("pageNum");
	}
	BoardDAO bDao = BoardDAO.getInstance();

// 	// 더미 대량생산
// 	for(int i = 0; i<100; i++) {
// 		BoardDTO temp = new BoardDTO("도배", "도배", "도배왕", "1234");
// 		bDao.writeArticle(temp);
// 	}

	ArrayList<BoardDTO> board = bDao.getBoard();
	
	final int ONEPAGE = 10;
	
	int lastPage = ((board.size()-1)/10)+1;
	
	int pageNum = 1;
	
	if(request.getParameter("pageNum") != null) {
		pageNum = Integer.parseInt(request.getParameter("pageNum"));
	}
	
	// 페이지 번호는 1~10 / 11~20 ...
	// 				0~9(0)	10~19(1)
	int pageStartNum = ((pageNum-1)/10)*10 + 1;
	int pageEndNum = (pageStartNum + 9) > lastPage ? lastPage : (pageStartNum + 9);
	// board.size() = 23
	// 23-1 = 22 / 10 = 2 + 1
	
	// 1 - 0~9 / 2 - 10~19 / 3 - 20~29
	
	// 시작인덱스는 페이지번호-1*10
	int startIndex = (pageNum-1) * 10;
	
	// 마지막인덱스는 시작인덱스로부터 9개까지 (10개) 또는 게시판의 글 수가 해당 숫자보다 작으면 게시판의 사이즈로 설정
	int endIndex = (startIndex+ONEPAGE-1) > board.size() ? board.size() : startIndex+ONEPAGE;
	
	%>
	    <h1>게시판</h1>
	    <table>
	        <tr>
	            <th>글 번호</th>
	            <th>글 제목</th>
	            <th>글 작성자</th>
	            <th>글 조회수</th>
	            <th>글 좋아요</th>
	            <th>글 작성 시간</th>
	        </tr>
	<%
	for(int i = startIndex; i<endIndex; i++){
		BoardDTO article = board.get(i);
		%>
		<tr>
			<td><%=article.getNum() %></td>
			<td><a href="13_boardView.jsp?articleNum=<%=article.getNum() %>&pageNum=<%=pageNum%>&action=view"><%=article.getTitle() %></a></td>
			<td><%=article.getUserName() %></td>
			<td><%=article.getView() %></td>
			<td><%=article.getLike() %></td>
			<td><%=article.getTime() %></td>
		</tr>
		<%
	}
	
%>
		<tr><td colspan=5>
		<%
		if(pageNum >= 11) {	// 현재 페이지가 11보다 크면 이전10페이지 버튼을 활성화시켜줌
			%>
			<button onclick="location.href='12_boardList.jsp?pageNum=<%=(pageStartNum-1) %>'">10페이지 이전</button><span> </span>
			<%
		}
	for(int i = pageStartNum; i<=pageEndNum; i++) {	
		if(i == pageNum) {	// 현재 페이지면 그냥 표시만 해줌
			%>
			<span>[<%=i %>] </span>
			<%
		}
		else {
			%>
			<a href="12_boardList.jsp?pageNum=<%=i %>">[<%=i %>]</a><span> </span>
			<%
		}
	}
	
	if(pageNum + 10 <= lastPage) {	// 현재 페이지+10이 페이지 마지막 번호보다 같거나 작으면 10페이지 이후 버튼을 활성화시켜줌
		%>
		<button onclick="location.href='12_boardList.jsp?pageNum=<%=pageStartNum+10 %>'">10페이지 이후</button>
		<%
	}
%>
        </td>
        
        <td>
        	<button onclick="location.href='14_writeArticle.jsp'">글 작성</button>
        </td>
        </tr>
    </table>
</body>
</html>