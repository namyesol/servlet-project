<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="com.dto.FileBoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%List<FileBoardDTO> list = (List<FileBoardDTO>) request.getAttribute("fileContentList");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>fileBoard Main출력</h1>
	0. 목록 DB불러와서 뿌려주기 1. 테이블 list만들기 2. 클릭시 게시물 번호 넘기기
	<hr>
	<table border=1>

		<tr>
			<th>글번호</th>
			<!-- <th>작성자</th> -->
			<th>작성일</th>
			<th>제목</th>
			<th>조회수</th>
		</tr>
      <c:forEach var="file" items="${fileContentList}" varStatus="loop">
        <tr>
                <td>${file.file_board_no}</td>
            	<td>${file.file_board_date}</td>
               <td><a href="FileBoardPost?file_board_no=${file.file_board_no}">${file.file_board_title}</a></td>
               <td>${file_board_view}</td>
               
		</tr>
		</c:forEach>
	</table>
</body>

</html>