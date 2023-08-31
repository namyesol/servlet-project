<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/main.css"> <!-- 수정? -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>상세페이지</title>
</head>
<body>
<div class="container-fluid">
	<div class="row">
	    <div class="sidebar col-md-3 col-lg-2 p-0">
      		<jsp:include page="/common/mainMenu.jsp" flush="true" /> <br> 
      	</div>
      	
      	<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      		<article class="mt-3 mb-3 pb-3">
      			<h2 class="h2 mb-3">${notice.title}</h2>
      			<div class="d-flex gap-2">
      				<p>글번호: ${notice.noticeNo}
	      			<p>작성자: ${notice.memberName}</p>
					<p>작성일: <fmt:formatDate value="${notice.createdAt}" pattern="Y년M월d일 hh:mm"/></p>
					<p>조회수: ${notice.views}</p>
      			</div>
      			<p class="text-break">${notice.content}</p>
      		</article>
    		<div class="btn-group me-2 w-100">
				<c:url var="editNoticeUrl" value="/EditNoticeServlet?noticeNo=${notice.noticeNo}" />
				<a href="${editNoticeUrl}">
					<button type="button" class="btn btn-outline-primary">수정</button>
				</a>
				<c:url var="deleteNoticeUrl" value="/DeleteNoticeServlet?noticeNo=${notice.noticeNo}"/>
				<form action="${deleteNoticeUrl}" method="post">
					<button type="submit" class="btn btn-outline-danger">삭제</button>
				</form>
				<c:url var="noticeListUrl" value="/NoticeListServlet"/>
				<a href="${noticeListUrl}">
					<button type="button" class="btn btn-outline-dark">뒤로가기</button>
				</a>
			</div>
      	</main>
   	</div>
</div>
</body>
</html>