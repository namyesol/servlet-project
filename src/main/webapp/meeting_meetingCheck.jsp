<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>전자결재 소메뉴</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/appMenu.css">
	<link rel="stylesheet" type="text/css" href="css/appDraftList.css">
</head>
<body>
	
	<jsp:include page="meetingMenu.jsp" flush="true" /> <br>  <!-- 메뉴 + 회의실 예약 메뉴바 -->
	<jsp:include page="meeting/meetingRoomCheck.jsp" flush="true" /> <br>  <!-- 회의실 예약확인 -->

</body>
</html>