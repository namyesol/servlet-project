<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회의실 예약 소메뉴</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/appMenu.css">
</head>
<body>
	
	<div>
		<div>
			<jsp:include page="main.jsp" flush="true" /> <br>  <!-- 왼쪽 메뉴바 -->
		</div>
		<div>
			<jsp:include page="common/meetingMenu.jsp" flush="true" /> <br>  <!-- 회의실 메뉴바 -->
		</div>
	</div>

</body>
</html>