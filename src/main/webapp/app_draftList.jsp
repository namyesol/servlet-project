<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>기안문서함</title>
</head>
<body>
	
	<jsp:include page="appMenu.jsp" flush="true" /> <br>  <!-- 메뉴 + 결재메뉴바 -->
	<jsp:include page="approval/draftList.jsp" flush="true" /> <br>  <!-- 기안 문서함 -->

</body>
</html>