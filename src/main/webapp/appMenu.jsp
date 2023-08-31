<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>전자결재 소메뉴</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/appMenu.css">
</head>
<body>
	
	<div>
		<div>
			<jsp:include page="main.jsp" flush="true" /> <br>  <!-- 왼쪽 메뉴바 -->
		</div>
		<div>
			<jsp:include page="common/appMenu.jsp" flush="true" /> <br>  <!-- 결재 메뉴바 -->
		</div>
	</div>

</body>
</html>