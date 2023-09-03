<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>임시 저장함</title>
	<link href="${pageContext.request.contextPath}/css/modal.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/appDraftList.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/appModalForm.css" rel="stylesheet">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"> </script>
	<script>
		$(function() {
			$("#app-btn").click(function() {
		 		$("#t-modal").fadeIn(); /*클릭시, 모달창이 나옴*/
			});
	
			$("#cancel").click(function(){
				$("#t-modal").fadeOut(); /*클릭시, 모달창이 꺼짐*/
		    });
		});
		
	</script>	
</head>

<body>

	<div class="s-container">
		<h2 id="h-title">임시 저장함</h2>
		<span style="display:inline-block; height:47px"></span>
		<button id="app-btn" class="app-btn">문서 기안</button>
		<table class="t-List">
			<tr>
				<th class="th-1">생성일</th>
				<th class="th-1">문서양식</th>
				<th class="th-3">제목</th>
				<th class="th-1">문서상태</th>
			</tr>
			
			<c:forEach items="${dList}" var="appDoc">
				<tr>
					<td>${appDoc.docDate }</td>
					<td>${appDoc.formName }</td>
					<c:url var="aDetail" value="/approval/detail.sw?type=${type }&docStatus=${appDoc.docStatus }">
						<c:param name="docNo" value="${appDoc.docNo }"></c:param>
					</c:url>
					<td><a href="${aDetail }">${appDoc.docTitle }</a></td>
					<td><span class="status-5">${appDoc.docStatus }</span></td>
				</tr>
			</c:forEach>
		</table>
		
		
		<jsp:include page="appPaging.jsp"></jsp:include> <!-- 페이징 -->
		
		
		<div class="l-search">
			<form action="/approval/${type}Search.sw" method="get">
				<select class="l-select" id="s-condition" name="searchCondition" style="text-align: left; width: 80px;">
					<option value="all">전체</option>
					<option value="docDate">생성일</option>
					<option value="formName">문서양식</option>
					<option value="docTitle">제목</option>
				</select>
				<div class="l-input">
					<input type="text" id="s-value" name="searchValue" class="l-text">
					<input type="submit" id="btn-search" class="i-search" value="&#xf002;">
				</div>
			</form>
		</div>
	</div>
	
	
	<!--문서 양식 선택 모달-->
	<div class="t-modal" id="t-modal"> <!-- modal 전체를 감싸는 검은 배경 -->
		<div class="modal-content" title="close on click"> <!-- 모달의 내용 -->
			<div class="modal-header" id="modal_header"> 기안 양식 선택 </div>
			<div class="modal-body" id="modal_body">
				<select class="modal-select" style ="height:20px; width:150px; ">
					<option>문서를 선택하세요</option>
					<option value="기안서">기안서</option>
					<option value="품의서">품의서</option>
					<option value="지출결의서">지출결의서</option>
					<option value="휴가신청서">휴가신청서</option>
				</select>
			</div>
			<div class="modal-footer">
				<button class="confirm-btn" id="confirm">선택</button> 
				<button class="cancel-btn" id="cancel">취소</button>
			</div>	
		</div>		
	</div>
</body>

</html>