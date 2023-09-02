<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid">
	<div class="row">
	    <div class="sidebar col-md-3 col-lg-2 p-0">
      		<jsp:include page="/common/mainMenu.jsp" flush="true" /> <br> 
      	</div>

		<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
			<h1 class="mt-3 mb-3">공지사항</h1>
			<div class="d-flex justify-content-end mb-3">
				<c:url var="newNoticeUrl" value="/NewNoticeServlet" />
				<a href="${newNoticeUrl}">
					<button type="button" class="btn btn-primary">작성</button>
				</a>
			</div>
		    <div class="table-responsive">
			    <table class="table">
			       <thead>
			           <tr>
			               <th scope="col">#</th>
			               <th scope="col">제목</th>
			               <th scope="col">작성자</th>
			               <th scope="col">작성일</th>
				           <th scope="col">조회수</th>
			           </tr>
			       </thead>
			       <tbody>               
		        	<c:forEach var="notice" items="${pageResponse.items}">
		        		<c:url var="NoticeDetailsUrl" value="/NoticeDetailsServlet?noticeNum=${notice.noticeNum}"/>
			        	<tr>
					        <th scope="row">
					        	<a href="${NoticeDetailsUrl}">${notice.noticeNum}</a>
				        	</th>
				        	<td>
				        		<a href="${NoticeDetailsUrl}">${notice.title}</a>
				        	</td>
				        	<td>${notice.memberName}</td>
				        	<td><fmt:formatDate value="${notice.createdAt}" pattern="M월d일"/></td>
				    	    <td>${notice.views}</td>
				        </tr>
		        	</c:forEach>
			       </tbody>
			    </table>
		    </div>
			<div class="d-flex justify-content-center">
		    	<nav aria-label="Page navigation example">
				  <ul class="pagination">
				  	<c:url var="noticeListUrl" value="/NoticeListServlet"/>
				  	<c:choose>
				  	<c:when test="${pageResponse.hasPrevious}">
					    <li class="page-item">
					      <a class="page-link" href="${noticeListUrl}?page=${pageResponse.start-1}&size=${pageResponse.size}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				    </c:when>
				    <c:otherwise>
				    	<li class="page-item disabled">
					      <a class="page-link" href="#" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				    </c:otherwise>
			    	</c:choose>
			    	<c:forEach begin="${pageResponse.start}" end="${pageResponse.end}" step="1" varStatus="status">
				    <c:choose>
					    <c:when test="${pageResponse.page eq status.current}">
					    <li class="page-item active">
					    	<a class="page-link" href="${noticeListUrl}?page=${status.current}&size=${pageResponse.size}">${status.current}</a>
				    	</li>
					    </c:when>
					    <c:otherwise>
   						<li class="page-item">
					    	<a class="page-link" href="${noticeListUrl}?page=${status.current}&size=${pageResponse.size}">${status.current}</a>
				    	</li>
					    </c:otherwise>
				    </c:choose>					   
				    </c:forEach>
				    
   				  	<c:choose>
				  	<c:when test="${pageResponse.hasNext}">
					    <li class="page-item">
					      <a class="page-link" href="${noticeListUrl}?page=${pageResponse.end+1}&size=${pageResponse.size}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				    </c:when>
				    <c:otherwise>
					    <li class="page-item disabled">
					      <a class="page-link" href="#" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				    </c:otherwise>
			    	</c:choose>
				  </ul>
				</nav>
		    </div>
		</main>
	</div>
</div>
</body>
</html>