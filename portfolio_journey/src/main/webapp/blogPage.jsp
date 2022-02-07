<%@page import="models.BlogDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controllers.BlogDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.CountryDTO"%>
<%@page import="controllers.CountryDAO"%>
<%@page import="controllers.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src = "js/blog.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link rel="stylesheet" href="css/all.css" type="text/css">
<link rel="stylesheet" href="css/blogPage.css" type="text/css">

<title>블로그 글 보기</title>
</head>

<body>
<%
String id = "Guest";
if(session.getAttribute("log") != null) {
	id = String.valueOf(session.getAttribute("log"));
}

UserDAO uDao = UserDAO.getInstance();
%>

	<c:import url="header.jsp" />
	<main>
	        <aside class="blog">
            <ul>
                <li></li>
            </ul>
        </aside>
        
        <div id="viewBlogs">
		        <div id="blogs">
		        <button onclick="location.href='writeBlog.jsp'">블로그 글쓰기</button>
        	<table>
        	<%
        	
        		BlogDAO bDao = BlogDAO.getInstance();
        		ArrayList<BlogDTO> blogs = bDao.getBlogs();
        			int currentPage = 1;
        			if(request.getParameter("currentPage") != null) {
        				currentPage = Integer.parseInt(request.getParameter("currentPage"));
        			}
        			int blogStart = (currentPage-1)*5;
        			int blogEnd = blogStart+4 > blogs.size()-1 ? blogs.size()-1 : blogStart+4;
        			int pageStart = (((currentPage-1)/10) * 10) + 1;
        			int pageEnd= (pageStart+9)*5 > blogs.size() ? ((blogs.size()-1)/5)+1 : pageStart+9;
        			int lastPage = ((blogs.size() - 1) / 5) + 1;
        			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        			
        			for(int i = blogStart; i<=blogEnd; i++) {
            			BlogDTO temp = blogs.get(i);
            			String date = sdf.format(blogs.get(i).getDate());
            			%>
            			<tr><td>
            				<table class="separateBlog">
            					<tr><td colspan=3><a id="title" href="viewBlogDetail.jsp?code=<%=temp.getCode()%>">제목 : <%=temp.getTitle() %></a></td></tr>
            					<tr><td>리뷰 국가 : <%=temp.getCountryName() %></td>
            					<td>유저 이름 : <%=temp.getId() %></td>
            					<td>평가 점수 : <%=temp.getScore() %> 점</td></tr>
            					<tr>
            					<%
            					ArrayList<String> images = temp.getImages();
            					if(images.size() != 0) {
            						for(int j = 0; j<images.size(); j++) {
            							%>
            							<td>
            							<img src=<%=images.get(j) %>>
            							</td>
            							<%
            						}
            					}
            					%>
            					</tr>
            					<tr><td id="content" colspan="3"><%=temp.getContent()%></td></tr>
            					<tr><td colspan="2">리뷰 날짜 : <%=date %></td>
            					<td>
            					<%
            					if(temp.getUserCode() == uDao.getUserCodeById(id)) {
            						%>
            						<button onclick="location.href='service?command=modifyblog&code=<%=temp.getCode()%>'">블로그수정</button>
                					<%-- <button onclick="location.href='service?command=deleteblog&code=<%=temp.getCode()%>'">삭제</button> --%>
                					
                					<input type = "hidden" name = "blogCode" value = "<%= temp.getCode()%>">
                					<button onclick="deleteBlog(<%= temp.getCode()%>)">삭제</button>
                					
            						<%
            					}
            					%>
            					</td></tr>

            				</table>
            				</td></tr>
            				<%
            			}
        			%>
        			<tr><td id="page">
        			<%
        			if(pageStart != 1) {
        				int targetPage = currentPage-10;
        				if(targetPage < 1) targetPage = 1;
        				%>
        				<button onclick="location.href='blogPage.jsp?currentPage=<%= targetPage %>'">이전 10페이지</button>
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
    						<a href="blogPage.jsp?currentPage=<%= i %>">[<%=i %>] </a>
    						<%
        				}
        			}
        			
        			if((pageStart+10) * 5 <= blogs.size()) {
        				int targetPage = currentPage+10;
        				if(targetPage >= lastPage) targetPage = lastPage;
        				%>
        				      <button onclick="location.href='blogPage.jsp?currentPage=<%= targetPage %>'">이후 10페이지</button>
        				<%
        			}
        			
        			%>
        			</td></tr>
        	
        	</table>
        </div>
	</div>
        
	</main>
	

</body>
</html>