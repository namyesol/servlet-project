<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>임시 저장함</title>
	<link href="../css/appDraftList.css" rel="stylesheet">
</head>

<body>

	<div class="s-container">
		<h2 id="h-title">임시 저장함</h2>
		<span style="display:inline-block; height:47px"></span>
		<button id="app-btn">문서 기안</button>
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
	
	<jsp:include page="modalForm.jsp"></jsp:include> <!-- 문서 양식 선택 -->
</body>

</html>