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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link rel="stylesheet" href="css/all.css" type="text/css">
<link rel="stylesheet" href="css/blogWrite.css" type="text/css">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<title>블로그 글 적기</title>
</head>
<body>

	<c:import url="header.jsp" />

	<!-- 블로그 글 적기가 넘겨 줄 파라미터
	1. countryName
	2. id
	3. title
	4. content
	5. score
	6. images
	7. userCode
 -->
	<form class="writeBlogForm" method="post" action="FileUploadServlet"
		enctype="multipart/form-data">
		<div id="modifyBlog">
			<input type="hidden" name="blogCode"
				value="${sessionScope.bSession.code }">
			<table>
				<tr class="blogText">
					<th class="blogText">블로그 글 수정하기</th>
				</tr>
				<tr>
					<td><input class="subTitle" type="text" name="title"
						value="${sessionScope.bSession.title}" required /></td>
				</tr>
				<tr>
					<td><input class="subTitle" type="text" name="countryName"
						id="countryName" value="${sessionScope.bSession.countryName}"
						required /></td>
				</tr>
				<tr>
					<td class="rate"><input class="rate" id="range" type="range"
						id="range" min=1 max=10 value=5 name="score" /><span id="child">5점</span>
					</td>
				</tr>
				<tr>
					<%-- <td> <textarea id="content" name="content">${sessionScope.bSession.content }</textarea> </td> --%>
					<td><textarea name="content" id="summernote">
					<%-- <textarea id="content" name="content">${sessionScope.bSession.content }</textarea> --%>
						<%-- <p id = "content" name="content">${sessionScope.bSession.content }</p> --%>
						${sessionScope.bSession.content }
						</textarea></td>
				</tr>
				
				
				<tr class="inputs-tr">
					<td><input id="image" type="file"
						accept="image/png, image/jpeg, image/jpg" name="image0"></td>
					<td><input id="image" type="file"
						accept="image/png, image/jpeg, image/jpg" name="image1"></td>
					<td><input id="image" type="file"
						accept="image/png, image/jpeg, image/jpg" name="image2"></td>
					<%-- <c:forEach var="images" items="${sessionScope.bSession.images}">
						<td><img width="200" height="200" src="${images}"></td>
						<td><input id="image" type="file"
							accept="image/png, image/jpeg, image/jpg" name="image<%=cnt%>">
						</td>
						<%
						cnt++;
						%>
					</c:forEach> --%>

				</tr>
				<%-- <tr>
        				
        				<td> <img width="200" height="200" src="${sessionScope.bSession.images.get(1) }"></td>
        				<td>
        					<input type="file" accept="image/png, image/jpeg, image/jpg" name="image2">
        				</td>
        			</tr>
        			<tr>
        				
        				<td> <img width="200" height="200" src="${sessionScope.bSession.images.get(2) }"></td>
        				<td>
        					<input type="file" accept="image/png, image/jpeg, image/jpg" name="image3">
        				</td>
        			</tr> --%>
				<tr>
					<td class="write-btn-area">
						<!-- <button onclick="uploadFile()">수정하기</button>--> <input
						class="write-btn" type="submit" value="수정" />
					</td>
				</tr>

			</table>
			<%-- <input type="hidden" name="id" value=<%=id %>>
        		<input type="hidden" name="userCode" value=<%=uDao.getUserCodeById(id) %>> --%>
		</div>
	</form>

	<script type="text/javascript" src="viewCountry.js"></script>
	<script>
		$(document).ready(function() {
			$('#summernote').summernote();

		});
		$('#summernote').summernote({
			placeholder : '내용',
			tabsize : 2,
			height : 600,
			width : 900
		});
	</script>
</body>
</html>