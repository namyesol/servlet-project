<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
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
      		<article class="mt-3 mb-3 pb-3">
      			<h2 class="h2 mb-3">${communityDetails.title}</h2>
      			<div class="d-flex gap-2">
      				<p>글번호: ${communityDetails.comNum}
	      			<p>작성자: ${communityDetails.memberName}</p>
					<p>작성일: <fmt:formatDate value="${communityDetails.createdAt}" pattern="M월d일 hh:mm"/></p>
					<p>조회수: ${communityDetails.views}</p>
      			</div>
      			<p class="text-break">${communityDetails.content}</p>
      		</article>
    		<div class="btn-group me-2 w-100">
				<c:url var="editCommunityUrl" value="/EditCommunityServlet?comNum=${communityDetails.comNum}" />
				<a href="${editCommunityUrl}">
					<button type="button" class="btn btn-outline-primary">수정</button>
				</a>
				<c:url var="deleteCommunityUrl" value="/DeleteCommunityServlet?comNum=${communityDetails.comNum}"/>
				<form action="${deleteCommunityUrl}" method="post">
					<button type="submit" class="btn btn-outline-danger">삭제</button>
				</form>
				<c:url var="communityListUrl" value="/CommunityListServlet"/>
				<a href="${communityListUrl}">
					<button type="button" class="btn btn-outline-dark">뒤로가기</button>
				</a>
			</div>
			<section class="mt-3 mb-3">
      			<c:url var="newReplyActionUrl" value="/NewReplyServlet?comNum=${communityDetails.comNum}"/>
				<form action="${newReplyActionUrl}" method="post">
					<div class="mb-3">
						<label for="content" class="form-label">댓글</label>
						<textarea name="content" class="form-control" rows="3"></textarea>
					</div>
					<div>
						<button type="submit" class="btn btn-outline-primary">작성</button>
					</div>
				</form>
			</section>
			<section>
            	<c:forEach var="replyDetails" items="${replyDetailsList}">
            	<article class="ms-${replyDetails.level} mb-3 pb-3 border-bottom" data-reply-num="${replyDetails.replyNum}" data-article-type="content">
	        		<div class="d-flex gap-2 align-items-center">
		      			<p class="fw-bold">${replyDetails.memberName}</p>
						<p class="text-secondary"><fmt:formatDate value="${replyDetails.createdAt}" pattern="MM/dd h:m"/></p>
						<p class="text-secondary">L: ${replyDetails.level}</p>
	      			</div>
	      			<p class="text-break">${replyDetails.content}</p>
	      			<div class="btn-group gap-2">
	      				<button class="reply-button btn text-secondary p-0 m-0">댓글+</button>
      					<button class="toggle-button btn text-secondary p-0 m-0">편집</button>
	      				<c:url var="deleteReplyUrl" value="/DeleteReplyServlet?replyNum=${replyDetails.replyNum}&comNum=${communityDetails.comNum}"/>
		      			<form action="${deleteReplyUrl}" method="post">
	      					<button class="btn text-secondary p-0 m-0">삭제</button>
	      				</form>
	      			</div>
            	</article>
            	<article class="ms-${replyDetails.level} mr-6 mt-3 mb-3 pb-3 border-bottom d-none" data-reply-num="${replyDetails.replyNum}" data-article-type="edit">
            		<c:url var="editReplyUrl" value="/EditReplyServlet?replyNum=${replyDetails.replyNum}&comNum=${communityDetails.comNum}"/>
            		<form action="${editReplyUrl}" method="post">
	        		<div class="d-flex gap-2 align-items-center">
		      			<p class="fw-bold">${replyDetails.memberName}</p>
						<p class="text-secondary"><fmt:formatDate value="${replyDetails.createdAt}" pattern="MM/dd h:m"/></p>
	      			</div>
					<textarea name="content" class="form-control" rows="3">${replyDetails.content}</textarea>
	      			<div class="btn-group gap-2">
      					<button type="submit" class="btn text-secondary p-0 m-0">수정</button>
      					<button class="toggle-button btn text-secondary p-0 m-0">취소</button>
	      			</div>
	      			</form>
            	</article>
            	<article class="ms-${replyDetails.level} mt-3 mb-3 pb-3 border-bottom d-none" data-reply-num="${replyDetails.replyNum}" data-article-type="reply">
            		<c:url var="newReplyActionUrl" value="/NewReplyServlet?comNum=${communityDetails.comNum}&parentReplyNum=${replyDetails.replyNum}"/>
            		<form action="${newReplyActionUrl}" method="post">
	        		<div class="d-flex gap-2 align-items-center">
		      			<p class="fw-bold">${login.member_name}</p>
	      			</div>
					<textarea name="content" class="form-control" rows="3"></textarea>
	      			<div class="btn-group gap-2">
      					<button type="submit" class="btn text-secondary p-0 m-0">대댓달기</button>
      					<button class="reply-button btn text-secondary p-0 m-0">취소</button>
	      			</div>
	      			</form>
            	</article>
            	</c:forEach>
			</section>	
      	</main>
   	</div>
</div>
<script>
	const toggle = (event) => {
		if (event.target.tagName.toLowerCase() === 'button') {
			console.log(event.target);
			console.log(event.currentTarget);
			if (event.target.classList.contains('toggle-button')) {
				event.preventDefault();
				let articles = document.getElementsByTagName('article');
				for (let article of articles) {
					if (article.dataset.replyNum === event.currentTarget.dataset.replyNum) {
						article.classList.toggle('d-none');
					}
				}
			}
		}
	}
	
	const reply = (event) => {
		if (event.target.tagName.toLowerCase() === 'button') {
			if (event.target.classList.contains('reply-button')) {
				event.preventDefault();
				let articles = document.getElementsByTagName('article');
				for (let article of articles) {
					if (article.dataset.replyNum === event.currentTarget.dataset.replyNum &&
							article.dataset.articleType === "reply") {
						article.classList.toggle('d-none');
					}
				}
			}
		}
	}
	
	let articles = document.getElementsByTagName('article');
	
	for (let article of articles) {
		if (article.dataset.replyNum) {
			article.addEventListener('click', toggle);
			article.addEventListener('click', reply);
			
		}
	}
</script>
</body>
</html>