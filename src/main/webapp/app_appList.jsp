<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>결재문서함</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/appMenu.css">
	<link rel="stylesheet" type="text/css" href="css/appDraftList.css">
</head>
<body>
	
	<jsp:include page="appMenu.jsp" flush="true" /> <br>  <!-- 메뉴 + 결재메뉴바 -->
	<jsp:include page="approval/appList.jsp" flush="true" /> <br>  <!-- 결재 문서함 -->

</body>
</html>