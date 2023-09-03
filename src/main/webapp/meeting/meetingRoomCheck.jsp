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
<link rel="stylesheet" type="text/css" href="css/meeting_modal.css">
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
       		<td><button class="show-modal" data-index="${loop.index}"
       		data-date="${room.meeting_date}" data-time="${room.meeting_time}" data-num="${room.meeting_num}">
       		상세보기</button></td>
		</tr>
   		</c:forEach>
</table>
</div>
	<div id="myModal" class="modal">
        <div class="modal-content">
            <!-- 모달 내용을 여기에 추가 -->
            <div class="contentTop">
                    <p></p><!-- 회의실 번호 -->
                    <ul class="contentul">
                        <li>날짜 : </li>
                        <li>시간 : </li>
                    </ul>
            </div>
            <div class="contentCenter">
                <div class="centerL"><img id="roomImg" src="" alt=""></div>
                <div class="centerR">
                    <p>&#x1F4D6; Option</p>

                    <ul class="roomInfo">
                        <li></li><!-- 인원 -->
                        <li>&#x2713; 빔프로젝트 </li>
                        <li>&#x2713; 화이트보드</li>
                        <li>&#x2713; 24인치 모니터 </li>
                    </ul>
                </div>
            </div>
            <div class="contentBottom">
                <div class="modal-buttons">
                    <button class="confirm-button">확인</button>
                    <button class="delete-button">예약 취소</button>
            </div>
            
            <span class="close">&times;</span>
            </div>
        </div>
    </div>
</body>
    <!-- JQuery를 사용하여 모달 열기/닫기 -->
    <script>
    $(document).ready(function() {
        var modal = $("#myModal");
        var modalContent = $(".modal-content");
        
        // "상세보기" 버튼 클릭 시 모달 표시
        $(".show-modal").click(function() {
            var index = $(this).data("index");
            var date = $(this).data("date");
            var time = $(this).data("time");
            var num = $(this).data("num");
            
            // 모달에 데이터 채우기
            $(".contentul li:nth-child(1)").text("날짜: " + date);
            $(".contentul li:nth-child(2)").text("시간: " + time);
            $(".contentTop p").html("&#x1F3E2; "+num+" &#x1F3E2;");
            
            //모달에 이미지, 인원수 넣기
            if (num==("1 회의실")) {
            $("#roomImg").attr("src","image/room1.jpg");
            $(".roomInfo li:nth-child(1)").html("&#x2713; 인원: 3~5");
			}else if (num==("2 회의실")) {
            $("#roomImg").attr("src","image/room2.jpg");
            $(".roomInfo li:nth-child(1)").html("&#x2713; 인원: 5~7");
			}else {
            $("#roomImg").attr("src","image/room3.jpg");
            $(".roomInfo li:nth-child(1)").html("&#x2713; 인원: 10~20");
			};
			
            
            // 모달 표시
            modal.css("display", "block");
        });

        // 모달 닫기 버튼 클릭 시 모달 숨기기
        $(".close, .confirm-button").click(function() {
            modal.css("display", "none");
        });

        // 모달 외부 클릭 시 모달 숨기기
        $(window).click(function(event) {
            if (event.target == modal[0]) {
                modal.css("display", "none");
            }
        });
    });


    </script>
</html>















