<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기안 문서 선택</title>
<link href="../css/appModalForm.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"> </script>
<script>
	//문서 양식 목록 조회
	$.ajax({
		url : "/modal/appForm/list.sw",
		type : "get",
		success : function(sList) {
			$.each(sList, function(index, item) {
				$("#selectBox").append("<option value=" + item.formNo + ">" + item.formName + "</option>")
			})
		},
		error : function() {
			alert("문서 양식 조회 실패");
		}
	});
	
	//문서 기안 버튼 클릭: appSelModal요소가 화면에 나타나게 함
	$("#app-btn").click( function() {
 		$("#appSelModal").css('display', 'none');
 		//$("#appSelModal").css('display', 'none').hide().fadeIn();
	});
	//선택
	$("#confirm").click(function(){
	    var formNo = $("#selectBox option:checked").val();
	    location.replace("/approval/docWriteView.sw?formNo=" + formNo);
	});
	//취소
	$("#cancel").click(function(){
		$("#appSelModal").fadeOut();
    });
	

</script>
</head>
<body>
	<!-- 문서 양식 선택 모달 -->
	<div class="m-appSel-wrap" id="appSelModal">
		<div class="m-appSel">
				<div class="m-header">
						<span class="m-header-title" id="header">기안 문서 양식</span>
				</div>
				<div class="m-body">
					<div class="m-search">
							<select class="s-selForm" id="selectBox"></select>
					</div>
				</div>
				<div class="m-footer">
						<span class="m-btn confirm" id="confirm">선택</span>
						<span class="m-btn cancel" id="cancel">취소</span>
				</div>
		</div>
	</div>
	
<body>
</html>