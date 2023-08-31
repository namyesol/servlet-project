<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>자료함 소메뉴</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/appMenu.css">
</head>
<body>
	<jsp:include page="fileBoardMenu.jsp" flush="true" /> <br>  <!-- 메뉴 + 결재메뉴바 -->
	<%-- <jsp:include page="fileBoardMain?file_path=top" flush="true" /> <br>  <!-- top메뉴바 --> --%>
	<div class="s-list-item"><a href="FileBoard/FileBoardMain.jsp">top메뉴바</a></div>
</body>
</html>