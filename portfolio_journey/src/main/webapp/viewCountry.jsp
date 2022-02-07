<%@page import="controllers.LikeDAO"%>
<%@page import="models.BlogDTO"%>
<%@page import="controllers.BlogDAO"%>
<%@page import="controllers.UserDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.ReReviewDTO"%>
<%@page import="controllers.ReReviewDAO"%>
<%@page import="models.ReviewDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controllers.ReviewDAO"%>
<%@page import="models.CountryDTO"%>
<%@page import="controllers.CountryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link rel="stylesheet" href="css/all.css" type="text/css">
<link rel="stylesheet" href="css/viewCountry.css" type="text/css">
<%
// Guest 이름으로 된 댓글은 로그인을 했든 안했든 수정/삭제가 보임	=> 당연히 비밀번호를 씀
// 어떤 댓글을 눌렀는지에 대한 검증이 필요함 => 버튼에 id를 달아놓으면 되겠다
// 비밀번호 검증을 해야한다.

// 국가에 대한 정보를 좀 더 넣는게 낫겠다
// 더미데이터나 api를 사용해서 넣으면 더 좋겠음

// 22 01 13 한 일 : 답글 삭제 보완(완료), 답글 작성(완료), 답글 수정(완료)
// 22 01 14 할 일 : 국가 평점 평균 내서 보여주기 / css

// jsp페이지를 모바일 css 먹일 생각하면서 만들어야함

// 로그인 method post로 바꾸기


String countryName = "미국";
if(request.getParameter("countryName") != null)
	countryName = request.getParameter("countryName");

String id = "Guest";
if(session.getAttribute("log") != null) {
	id = String.valueOf(session.getAttribute("log"));
}

if(request.getParameter("action") != null) {
	if(request.getParameter("action").equals("like")) {
		%>
		<script>alert("해당 국가에 좋아요를 표시했습니다!")</script>
		<%
	}
	else if(request.getParameter("action").equals("hate")) {
		%>
		<script>alert("해당 국가에 좋아요를 취소했습니다!")</script>
		<%
	}
	else if(request.getParameter("action").equals("duplicate")) {
		%>
		<script>alert("중복된 요청입니다!!")</script>
		<%
	}
}

UserDAO uDao = UserDAO.getInstance();

CountryDAO cDao = CountryDAO.getInstance();
CountryDTO country = cDao.getCountry(countryName);
String flag = country.getFlag();

ArrayList<String> countryNames = cDao.getCountryNames();
%>
<title>정보</title>
</head>
<body>
	   <c:import url="header.jsp"/>
    <main>
        <aside class="country">
            <p id="country">국가 목록</p>
            <ul>
            <%
            for(int i = 0; i<countryNames.size(); i++) {
            	%>
            	<li><a href="viewCountry.jsp?countryName=<%=countryNames.get(i)%>"><%=countryNames.get(i)%></a></li>
            	<%
            }
            %>
            </ul>
        </aside>
        
        <div id="contents">
        	<div id="countryInfo">
        	<table>
        	<tr>
                <td id="flagTd" rowspan="4">
					<%
						if(flag.compareTo("") != 0) {
							%>
								<img id="flag" src=<%=flag %>>
							<%
						}
						else {
							%>
								<img id="flag" src="images/question.png">
							<%
						}
					%>
				<form action="service" method="get" id="btn">
					<%

					LikeDAO lDao = LikeDAO.getInstance();
					int code = lDao.findIdCountryName(id, countryName);
					if(code == -1) {
						%>
		        		 <button type = "submit" class="btn-like">💗</button>
		        		 <input type="hidden" name="command" value="like">
						<%
					}
					else {
						%>
		        		 <button type = "submit" class="btn-like done">💗</button>
		        		 <input type="hidden" name="command" value="hate">
		        		 
						<%
					}
					
					%>
				 
        		 <input type="hidden" name="countryName" value=<%=countryName %>>
        		</form>
        		<span><%=country.getLikecnt() %> 명이 좋아합니다!</span>
				</td>
            </tr>
            <tr>
                <td><span>국가 이름 : </span><%=country.getCountryName() %></td>
            </tr>
            <tr>
                <td><span>리뷰 평균 : </span><%=country.getScore() %> 점</td>
            </tr>
            <tr>
                <td><%=country.getContent() %></td>
            </tr>
        	</table>
        	
        </div>
        
        <div id="showShortBlogs">
        <%
        BlogDAO bDao = BlogDAO.getInstance();
        ArrayList<BlogDTO> blogs = bDao.getShortBlogs(countryName);
        if(blogs.size() == 0) {
        	%>
        	<p> 아직 이 국가에 대한 블로그 글이 없습니다!<br>하나 작성해보시는 건 어떠신가요?</p>
        	<%
        }
        else {
        	%>
        	<table id="blogReview">
        		<tr><th colspan=2> 가장 최근에 이 국가에 적힌 3건까지의 블로그 글을 보여드립니다. </th></tr>
        	<%
        		for(int i = 0; i<blogs.size(); i++) {
        			BlogDTO blog = blogs.get(i);
        			String shortContent = blog.getContent().length() > 40 ? blog.getContent().substring(0, 40) : blog.getContent();
        			%>
		    			<tr>
		    				<td colspan=2><a href="viewBlogDetail.jsp?code=<%=blog.getCode()%>"><%=blog.getTitle() %></a></td>
		    			</tr>
		    			<tr>
		    			<%
		    				if(blog.getImages().size() == 0) {
		    					%>
		    					<td></td>
		    					<%
		    				}
		    				else {
		    					%>
		    					<td><a href="viewBlogDetail.jsp?code=<%=blog.getCode()%>"><img src=<%=blog.getImages().get(0) %>></a></td>
		    					<%
		    				}
		    			%>
		    				<td><%=shortContent %></td>
		    			</tr>
		    			<tr>
		    				<td>작성자 : <%=blog.getId() %></td>
		    				<td>작성 날짜 : <%=blog.getDate() %></td>
		    			</tr>
        			<%
        		}
        	%>

        	</table>
        	<%
        }
        %>

        </div>
        
        <!-- 리뷰 쓰기 시스템을 넣을 것임 -->
        <div id="writeReview">
        	<form method="post" action="service">
        		<table>
        			<tr>
        				<th> <h2>리뷰 쓰기</h2> </th>
        			</tr>
        			<tr>
        				<td>
        					<input id="range" type="range" min=1 max=10 value=5 name="score"><span id="child">5점</span>
        				</td>
        			</tr>
        			<tr>
        				<td> <textarea name="content" placeholder="내용"></textarea> </td>
        			</tr>
        			<tr>
        				<td>
        					<%
        						if(id.equals("Guest")) {
        							%>
        								<input name="pw" type="password" placeholder="비밀번호" required>
        							<%
        						}
        					%>
        					<input type="submit" value="댓글 적기">
        				</td>
        			</tr>
        		</table>
        		<input type="hidden" name="countryName" value=<%=countryName %>>
        		<input type="hidden" name="id" value=<%=id %>>
        		<%
        		if(!id.equals("Guest")) {
        			%>
        			<input type="hidden" name="userCode" value=<%=uDao.getUserCodeById(id) %>>
        			<%
        		}
        		%>
        		<input type="hidden" name="command" value="writeReview">
        	</form>
        </div>
        <div id="review">	<!-- 10개까지 표시 + 작성 창 => 11줄 / 답글 -->
        	<table>
        	<%
        		ReviewDAO rDao = ReviewDAO.getInstance();
        		ReReviewDAO rrDao = ReReviewDAO.getInstance();
        		ArrayList<ReviewDTO> reviews = rDao.getReviews(countryName);
        			int currentPage = 1;
        			if(request.getParameter("currentPage") != null) {
        				currentPage = Integer.parseInt(request.getParameter("currentPage"));
        			}
        			int reviewStart = (currentPage-1)*10;
        			int reviewEnd = reviewStart+9 > reviews.size()-1 ? reviews.size()-1 : reviewStart+9;
        			int pageStart = (((currentPage-1)/10) * 10) + 1;
        			int pageEnd= (pageStart+9)*10 > reviews.size() ? ((reviews.size()-1)/10)+1 : pageStart+9;
        			int lastPage = ((reviews.size() - 1) / 10) + 1;
        			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        		
        			for(int i = reviewStart; i<=reviewEnd; i++) {
            			ReviewDTO temp = reviews.get(i);
            			String date = sdf.format(reviews.get(i).getDate());
            			%>
            			<tr><td>
            				<table id="reviewA">
            					<tr><td>리뷰 국가 : <%=temp.getCountryName() %></td>
            					<td>유저 이름 : <%=temp.getId() %></td>
            					<td>평가 점수 : <%=temp.getScore() %> 점</td></tr>
            					<tr><td colspan="3">유저 리뷰 : <%=temp.getContent()%></td></tr>
            					<tr><td colspan="2">리뷰 날짜 : <%=date %></td>
            					<td>
            					<button onclick="location.href='service?command=writeReReview&code=<%=temp.getCode()%>'">답글</button>
            					<%
            					if(temp.getId().equals("Guest") && temp.getId().equals(id)) {
            						%>
                					<button onclick="location.href='service?command=modifyReview&code=<%=temp.getCode()%>'">수정</button>
                					<button onclick="location.href='service?command=deleteReview&code=<%=temp.getCode()%>'">삭제</button>
                					<%
            					}
            					else if(temp.getUserCode() == uDao.getUserCodeById(id)) {
            						%>
            						<button onclick="location.href='service?command=modifyReview&code=<%=temp.getCode()%>'">수정</button>
                					<button onclick="location.href='service?command=deleteReview&code=<%=temp.getCode()%>'">삭제</button>
            						<%
            					}
            					
            					%>
            					</td></tr>
            					<%
            					if(temp.getAttachCnt() >= 1) {
            						%>
            						<tr><td><button id="<%=temp.getCode() %>" onclick='toggleTable(event)'>답글 <%=temp.getAttachCnt() %>개 보기</button></td></tr>
            						<tr><td colspan="3"><table id="<%=temp.getCode() %>_table" class="_table" style="display:none;">
            						<%
            						ArrayList<ReReviewDTO> rrviews = rrDao.getReReviews(temp.getCode());
            						for(ReReviewDTO rrtemp : rrviews) {
            							date = sdf.format(rrtemp.getDate());
	            					%>
	            					
	            					<tr><td><span>&#9;</span>유저 이름 : <%=rrtemp.getId() %></td></tr>
            						<tr><td><span>&#9;</span>유저 답글 : <%=rrtemp.getContent()%></td></tr>
            						<tr><td><span>&#9;</span>답글 날짜 : <%=date %></td></tr>
            						<tr><td><span>&#9;</span>
            
            						<%
            						if(rrtemp.getId().equals("Guest") && rrtemp.getId().equals(id)) {
            							%>
                						<button onclick="location.href='service?command=modifyReReview&code=<%=rrtemp.getCode()%>&countryName=<%=countryName%>'">수정</button>
                						<button onclick="location.href='service?command=deleteReReview&code=<%=rrtemp.getCode()%>&countryName=<%=countryName%>'">삭제</button>
                						<%
            						}
            						else if(rrtemp.getUserCode() == uDao.getUserCodeById(id)) {
            							%>
            							<button onclick="location.href='service?command=modifyReReview&code=<%=rrtemp.getCode()%>&countryName=<%=countryName%>'">수정</button>
                						<button onclick="location.href='service?command=deleteReReview&code=<%=rrtemp.getCode()%>&countryName=<%=countryName%>'">삭제</button>
            							<%
            						}
            					
            					%>
            					</td></tr>
	            					<%
            						}
            					%>
	            				</table>
            					</td></tr>
            					<%
            					}
            				%>
            				</table>
            				</td></tr>
            				<%
            			}
        			%>
        			<tr><td>
        			<%
        			if(pageStart != 1) {
        				int targetPage = currentPage-10;
        				if(targetPage < 1) targetPage = 1;
        				%>
        				<button onclick="location.href='viewCountry.jsp?countryName=<%=countryName %>&currentPage=<%= targetPage %>'">이전 10페이지</button>
        				<%
        			}
        			for(int i = pageStart; i<=pageEnd; i++) {
        				if(currentPage == i) {
        					%>
        					<span>[<%=i %>] </span>
        					<%
        				}
        				else {
        					%>
    						<a href="viewCountry.jsp?countryName=<%=countryName %>&currentPage=<%= i %>">[<%=i %>] </a>
    						<%
        				}
        			}
        			
        			if((pageStart+10) * 10 <= reviews.size()) {
        				int targetPage = currentPage+10;
        				if(targetPage >= lastPage) targetPage = lastPage;
        				%>
        				      <button onclick="location.href='viewCountry.jsp?countryName=<%=countryName %>&currentPage=<%= targetPage %>'">이후 10페이지</button>
        				<%
        			}
        			
        			%>
        			</td></tr>
        	
        	</table>
        </div>
        </div>
        
    </main>
<%--     <c:import url="footer.jsp"/> --%>
    

<script type="text/javascript" src="viewCountry.js"></script>
    
</body>
</html>