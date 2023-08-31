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
			<h1 class="mt-3 mb-3">자유 게시판</h1>
			<div class="d-flex justify-content-end mb-3 gap-2">
				<form class="d-flex" role="search" method="get" action="">
			        <input name="q" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
			        <button class="btn btn-outline-success" type="submit">Search</button>
		      	</form>
				<c:url var="newCommunityUrl" value="/NewCommunityServlet" />
				<a href="${newCommunityUrl}">
					<button type="button" class="btn btn-outline-primary">작성</button>
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
		        	<c:forEach var="communityDetails" items="${communityDetailsList}">
		        		<c:url var="communityDetailsUrl" value="/CommunityDetailsServlet?comNo=${communityDetails.comNo}"/>
			        	<tr>
					        <th scope="row">
					        	<a href="${communityDetailsUrl}">${communityDetails.comNo}</a>
				        	</th>
				        	<td>
				        		<a href="${communityDetailsUrl}">${communityDetails.title}</a>
				        	</td>
				        	<td>${communityDetails.memberName}</td>
				        	<td><fmt:formatDate value="${communityDetails.createdAt}" pattern="M월d일"/></td>
				    	    <td>${communityDetails.views}</td>
				        </tr>
		        	</c:forEach>
			       </tbody>
			    </table>
		    </div>
		    <div class="d-flex justify-content-center">
		    	<nav aria-label="Page navigation example">
				  <ul class="pagination">
				    <li class="page-item">
				      <a class="page-link" href="#" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				    <li class="page-item"><a class="page-link" href="#">1</a></li>
				    <li class="page-item"><a class="page-link" href="#">2</a></li>
				    <li class="page-item"><a class="page-link" href="#">3</a></li>
				    <li class="page-item">
				      <a class="page-link" href="#" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				  </ul>
				</nav>
		    </div>
		</main>
	</div>
</div>
</body>
</html>
         