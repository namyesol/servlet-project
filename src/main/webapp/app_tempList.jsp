<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>임시저장함</title>
</head>
<body>
	
	<jsp:include page="appMenu.jsp" flush="true" /> <br>  <!-- 메뉴 + 결재메뉴바 -->
	<jsp:include page="approval/tempList.jsp" flush="true" /> <br>  <!-- 임시 저장함 -->

</body>
</html>