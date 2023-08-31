<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- link : style sheet -->
<link rel="stylesheet" href="css/loginForm.css">

<title>로그인</title>

	<%
		// 로그인 성공여부
		String mesg = (String) request.getAttribute("mesg");
		if (mesg != null) {
	%>
	<script type="text/javascript">
			alert("<%=mesg%>");
	</script>
	<%
		}
	%>
</head>
<body>
<div class="wrapper" style="align-content: center">
	<jsp:include page="member/loginForm.jsp" flush="false"></jsp:include><br>
</div>
</body>
</html>