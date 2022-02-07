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
<link rel="stylesheet" href="css/main.css" type="text/css">
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
	<%
	if (session.getAttribute("log") == null) {
		response.sendRedirect("login.jsp?error=needLogin");
	}

	String title = "";
	String content = "";
	int score = 5;
	String countryName = "";

	if (request.getAttribute("error") != null) {
		if (request.getAttribute("error").equals("fail")) {
			title = String.valueOf(session.getAttribute("title"));
			content = String.valueOf(session.getAttribute("content"));
			score = Integer.parseInt(String.valueOf(session.getAttribute("score")));
			countryName = String.valueOf(session.getAttribute("countryName"));
	%>s
	<script>
		alert("글 작성에 오류가 발생했습니다. 파일은 다시 등록해주세요.")
	</script>
	<%
	}
	}

	String id = String.valueOf(session.getAttribute("log"));

	UserDAO uDao = UserDAO.getInstance();
	%>
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

	<form method="post" action="writeBlog" enctype="multipart/form-data">
		<div class="writeBlogForm" id="writeBlog">
			<table>
				<tr class="blogText">
					<th class="blogText">블로그 글 쓰기</th>
				</tr>
				<tr>
					<td class="subTitle"><input class="subTitle" type="text"
						name="title" placeholder="제목" required value=<%=title%>></td>
				</tr>
				<tr>
					<td class="subTitle"><input class="subTitle" type="text"
						name="countryName" placeholder="국가 이름을 입력하세요" required
						value=<%=countryName%>></td>
				</tr>
				<tr>
					<td class="rate"><input class="rate" id="range" type="range"
						min=1 max=10 value=<%=score%> name="score"><span
						class="rate" id="child">5점</span></td>
				</tr>
				<tr>
					<td name="content"><textarea name="content" id="summernote">
							<%=content%>
						</textarea></td>
				</tr>
				<tr>
					<td><input class="fileUpload" type="file"
						accept="image/png, image/jpeg, image/jpg" name="image1"></td>
				</tr>
				<tr>
					<td><input class="fileUpload" type="file"
						accept="image/png, image/jpeg, image/jpg" name="image2"></td>
				</tr>
				<tr>
					<td><input class="fileUpload" type="file"
						accept="image/png, image/jpeg, image/jpg" name="image3"></td>
				</tr>
				<tr>
					<td class="write-btn-area"><input class="write-btn"
						type="submit" value="글 쓰기"></td>
				</tr>

			</table>
			<input type="hidden" name="id" value=<%=id%>> <input
				type="hidden" name="userCode" value=<%=uDao.getUserCodeById(id)%>>
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