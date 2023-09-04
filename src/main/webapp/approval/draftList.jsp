<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath}/css/modal.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/appDraftList.css" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"> </script>
	<script>
	
		$(function() {
			//문서 양식 목록 조회: 서버로부터 문서 양식 목록을 조회하는 AJAX 요청을 수행
// 	 		$.ajax({
// 				url : "/modal/appForm/list.sw",
// 				type : "get",
// 				success : function(sList) {
// 					$.each(sList, function(index, item) {
// 						$("#selectBox").append("<option value=" + item.formNo + ">" + item.formName + "</option>")
// 					}) //서버에서 가져온 양식 목록을 <select> 요소(#selectBox)에 옵션으로 추가
// 				},
// 				error : function() {
// 					alert("문서 양식 조회 실패");
// 				}
// 			});

	 		///////////////문서 기안 모달창 처리
	 		//문서 기안
			$("#app-btn").click(function() {
		 		$("#t-modal").fadeIn(); /*클릭시, 모달창이 나옴*/
			});
	 		//선택
			//$("#confirm-btn").click(function() {
		 		//$("#modal-select"). /*문서 선택 -> 클릭: 해당 페이지로 이동*/
			//});
			//취소
			$("#cancel-btn").click(function(){
				$("#t-modal").fadeOut(); /*클릭시, 모달창이 꺼짐*/
		    });
		});
		
	</script>
</head>

<body>
	
	<div class="s-container">
		<h2 id="h-title">기안 문서함</h2>
		<span class="type ${docStatus eq '전체' ? 'active' : ''}"><a href="/approval/${type }ListView.sw">전체</a></span>
		<span class="type ${docStatus eq '대기' ? 'active' : ''}"><a href="/approval/${type }ListView.sw?docStatus=대기">대기</a></span>
		<span class="type ${docStatus eq '진행' ? 'active' : ''}"><a href="/approval/${type }ListView.sw?docStatus=진행">진행</a></span>
		<span class="type ${docStatus eq '완료' ? 'active' : ''}"><a href="/approval/${type }ListView.sw?docStatus=완료">완료</a></span>
		<span class="type ${docStatus eq '반려' ? 'active' : ''}"><a href="/approval/${type }ListView.sw?docStatus=반려">반려</a></span>
		
		<button class="app-btn" id="app-btn">문서 기안</button>
		
		<table class="t-List">
			<tr>
				<th class="th-1">기안일</th>
				<th class="th-1">문서양식</th>
				<th class="th-2">제목</th>
				<th class="th-1">문서번호</th>
				<th class="th-1">결재상태</th>
			</tr>
			
			<c:forEach items="${dList }" var="appDoc">
				<tr>
					<td>${appDoc.docDate }</td>
					<td>${appDoc.formName }</td>
					<c:url var="aDetail" value="/approval/detail.sw?type=${type }&docStatus=${appDoc.docStatus }">
						<c:param name="docNo" value="${appDoc.docNo }"></c:param>
					</c:url>
					<td><a href="${aDetail }">${appDoc.docTitle }</a></td>
					<td>${appDoc.docNo }</td>
					<c:if test="${appDoc.docStatus == '대기'}">
						<td><span class="status-1">${appDoc.docStatus }</span></td>
					</c:if>
					<c:if test="${appDoc.docStatus == '진행'}">
						<td><span class="status-2">${appDoc.docStatus }</span></td>
					</c:if>
					<c:if test="${appDoc.docStatus == '완료'}">
						<td><span class="status-3">${appDoc.docStatus }</span></td>
					</c:if>
					<c:if test="${appDoc.docStatus == '반려'}">
						<td><span class="status-4">${appDoc.docStatus }</span></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		
		<jsp:include page="appPaging.jsp"></jsp:include> <!-- 페이징 -->
		
		<div class="l-search">
			<form action="#" method="get">
					<select class="l-select" id="s-condition" name="searchCondition" style="text-align: left; width: 80px;">
						<option value="all">전체</option>
						<option value="docDate">기안일</option>
						<option value="formName">문서양식</option>
						<option value="docTitle">제목</option>
						<option value="docNo">문서번호</option>
					</select>
					
					<div class="l-input">
						<input type="text" id="s-value" name="searchValue" class="l-text">
						<input type="submit"  value="검색" id="btn-search" class="i-search">
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
				<button class="confirm-btn" id="confirm-btn">선택</button> 
				<button class="cancel-btn" id="cancel-btn">취소</button>
			</div>	
		</div>		
	</div>

</body>
</html>