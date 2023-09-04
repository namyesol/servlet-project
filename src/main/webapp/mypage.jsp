<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="css/main_style.css">
</head>
<body>
	<div>
		<div>
			<jsp:include page="main.jsp" flush="true" /> <br>  <!-- 왼쪽 메뉴바 -->
		</div>
		<div>
			<jsp:include page="member/mypage.jsp" flush="true" /> <br>  <br>  <!-- 마이페이지 메뉴바 -->
		</div>
	</div>
</body>
</html>