<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기안 문서 선택</title>
<link href="../css/modal.css" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
	//문서 양식 목록 조회: 서버로부터 문서 양식 목록을 조회하는 AJAX 요청을 수행
// 	$(document).ready(function() {
		
// 		$.ajax({
// 			url : "/modal/appForm/list.sw",
// 			type : "get",
// 			success : function(sList) {
// 				$.each(sList, function(index, item) {
// 					$("#selectBox").append("<option value=" + item.formNo + ">" + item.formName + "</option>")
// 				}) //서버에서 가져온 양식 목록을 <select> 요소(#selectBox)에 옵션으로 추가
// 			},
// 			error : function() {
// 				alert("문서 양식 조회 실패");
// 			}
// 		});
		
		//문서 기안 버튼 클릭: 모달창 나오도록
		$("#app-btn").click(function() {
			$("#t-modal").fadeIn(); /*클릭시, 모달창이 나옴*/
		});
		
		//선택: confirm ID를 가진 요소를 클릭하면, 선택된 양식의 formNo 값을 가져와서 해당 양식을 작성할 페이지로 이동
		$("#confirm").click( function() {
			var formNo = $("#selectBox option:checked").val();
		    location.replace("/approval/docWriteView.sw?formNo=" + formNo);
		});
		//취소
		$("#cancel").click(function(){
			$("#t-modal").fadeOut(); /*클릭시, 모달창이 꺼짐*/
	    });
	});

</script>
</head>
<body>
	<!-- 문서 양식 선택 모달 -->
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

	
<body>
</html>