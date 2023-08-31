<%@page import="com.dto.MeetingRoomDTO"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL 사용   LIB폴더에 .JAR 포함  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<MeetingRoomDTO> list = (List<MeetingRoomDTO>)session.getAttribute("list");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/meetingRoomCheck.css">
<link rel="stylesheet" type="text/css" href="css/modal.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="table-container">
	<table>
		<tr class="title">
		<th>예약 날짜</th>
		<th>이용 시간</th>
		<th>회의실</th>
		<th></th>
		</tr>
    
    	<c:forEach var="room" items="${list}" varStatus="loop">
		<tr class="contents">
       	 	<td>${room.meeting_date}</td>
        	<td>${room.meeting_time}</td>
       		<td>${room.meeting_num}</td>
       		<td><button data-target="modal${loop.index}">상세보기</button></td>
		</tr>
   		</c:forEach>
</table>
</div>
<div id="myModal" class="modal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <p>여기에 상세 내용을 표시합니다.</p>
  </div>
</div>
</body>
<script>

$(document).ready(function() {
    // "상세보기" 버튼 클릭 시 모달 열기
    $(".openModalBtn").click(function() {
        var targetModalId = $(this).data("target");
        $("#" + targetModalId).show();
    });

    // 모달 안의 닫기 버튼 클릭 시 모달 닫기
    $(".close").click(function() {
        $(this).closest(".modal").hide();
    });
});

</script>
</html>















