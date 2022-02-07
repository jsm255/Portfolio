<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.ReviewDTO"%>
<%@page import="controllers.ReviewDAO"%>
<%@page import="controllers.UserDAO"%>
<%@page import="models.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/myboardList.css">
<link rel="stylesheet" href="css/all.css" type="text/css">
<title>boardList</title>
</head>
<body>
	<%
	String reviewId = (String) session.getAttribute("log");
	UserDTO user = UserDAO.getInstance().getId(reviewId);
	
	
	ReviewDAO dao = ReviewDAO.getInstance();
	ArrayList<ReviewDTO> review = dao.getMyBoardList(reviewId);
//	System.out.println("reviewId: "+reviewId);

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
%>
	<div>
		<c:import url="header.jsp" />
		<main>
			<aside class="mypage">
				<p id="mypage">마이페이지</p>
				<ul class="myList">
					<li><a href="mypageMain.jsp">좋아요 표시한 국가</a></li>
					<li><a href="mypage.jsp">회원정보 수정</a></li>
					<li><a href="myboardList.jsp">내가 작성한 리뷰</a></li>
					<li><a href="deleteUser.jsp">회원 탈퇴</a></li>

				</ul>

			</aside>
			<div>
				<article class="review">
					<p>＊내가 작성한 리뷰</p>

					<table>
					<c:if test="<%=review.size() > 0 %>">
						<tr>
							<td class="c1">No</td>
							<td class="c1">Country</td>
							<td class="c1">Id</td>
							<td class="c1">Content</td>
							<td class="c1">Date</td>
						</tr>
					</c:if>
						
						<%
						String color = "c1";
                  for(int i=0; i<review.size(); i++){
                	  String date = sdf.format(review.get(i).getDate());
                	  if(i % 2 == 0) color = "c0";
                	  else color = "c1";
                  %>
						<tr>
							<td class=<%=color %>><%=review.get(i).getCode()%></td>
							<td class=<%=color %>><%=review.get(i).getCountryName() %></a></td>
							<td class=<%=color %>><%=review.get(i).getId() %></td>
							<td class=<%=color %>><a
								href="service?command=modifyReview&code=<%=review.get(i).getCode()%>"><%=review.get(i).getContent() %></td>
							<td class=<%=color %>><%=date%></td>
						</tr>
						<%} %>
					</table>
					<form action="service" method="post">
					<input type="hidden" name="command" value="myBoardList">
					</form>
				</article>
			</div>
		</main>
			<div class="footer-div" >
        <c:import url="footer.jsp"/>
        </div>
	</div>
</body>
</html>