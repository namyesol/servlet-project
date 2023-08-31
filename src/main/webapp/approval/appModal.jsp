<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결재자 선택</title>
<link href="/resources/css/approval/appModal-style.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>
</head>

<body>
	<div class="m-appSel-wrap" id="appSelModal">
		<div class="m-appSel">
		
			<div class="m-header">
				<span class="m-header-title" id="header"></span>
			</div>
			
			<div class="m-body">
				<div class="m-search">
					<select class="s-select" id="s-condition" name="searchCondition">
						<option value="all">전체</option>
						<option value="division">부서</option>
						<option value="memberName">이름</option>
					</select>
					<div class="s-input">
						<input type="text" id="s-value" name="searchValue" class="s-text">
						<input type="button" id="btn-search" class="i-search" value="&#xf002;">
					</div>
				</div>
				
				<div class="m-list">
					<table id="m-list-table">
					</table>
				</div>
				<div class="m-select">
					<strong id="s-text"></strong><br>
					<p id="s-list">
				</div>
			</div>
			
			<div class="m-footer">
				<span class="m-btn confirm" id="confirm">확인</span>
				<span class="m-btn cancel" id="cancel">취소</span>
			</div>
		</div>
	</div>
</body>

<script>
	var varType; // 결재자/참조자 구분 넣을 변수 선언
	var appArr = new Array(); // 선택한 결재자 담을 배열 선언
	var refArr = new Array(); // 선택한 결재자 담을 배열 선언
	var appArrText = new Array(); // 화면에 보여줄 결재자 리스트 배열 선언
	var refArrText = new Array(); // 화면에 보여줄 참조자 리스트 배열 선언
	// 결재자/참조자 선택 모달
	function appBtn(type) {
		varType = type; // 변수에 type 넣어주기
		if(type == "app") {
			$("#header").html("결재자 선택");
			$("#s-text").html("결재자");
			$("#s-list").html(appArrText.join("<br>"));
		}else if(type == "ref"){
			$("#header").html("참조자 선택");
			$("#s-text").html("참조자");
			$("#s-list").html(refArrText.join("<br>"));
		}
		$("#appSelModal").css('display', 'flex').hide().fadeIn();
		$.ajax({
			url : "/modal/member/list.sw",
			type : "get",
			success : function(mList) {
				$("#s-value").val(""); // 검색 입력창 지우기
				appList(mList, type);
			},
			error : function() {
				alert("사원 목록 조회 실패");
			}
		})
	}
	$("#confirm").click(function(){
	    modalClose();
	    appSelView();
	});
	$("#cancel").click(function(){
	    modalClose();
    });
	function modalClose(){
	    $("#appSelModal").fadeOut();
	}
	
	// 검색 내용 입력 후 엔터 눌러도 검색되도록 처리
	$("#s-value").keyup(function (e) {
		e.preventDefault();
		var code = e.keyCode ? e.keyCode : e.which;
		if(code == 13) { // 엔터키면
			$("#btn-search").click();
		}
	})
	
	// 결재자/참조자 선택 사원 검색
	$("#btn-search").click(function() {
		var searchCondition = $("#s-condition").val();
		var searchValue = $("#s-value").val();
		$.ajax({
			url : "/modal/member/search.sw",
			type : "get",
			data : { "searchCondition" : searchCondition,  "searchValue" : searchValue },
			success : function(mList) {
				appList(mList, varType);
			},
			error : function() {
				alert("사원 목록 검색 실패");
			}
		})
	});
	
	// 사원 목록 불러오기
	function appList(mList, type) {
		$("#m-list-table").html(""); // 테이블 값 지우기
		var tr;
		$.each(mList, function(i) {
			tr += '<tr class="tr"><td style="display:none;">' + mList[i].memberNum
			+ '</td><td>' + mList[i].division
			+ '</td><td>' + mList[i].memberName
			+ '</td><td>' + mList[i].rank + '</td></tr>';
		});
		$("#m-list-table").append(tr);
		appSelect(type); // 결재자/참조자 선택
	}
	
	// 결재자/참조자 선택
	function appSelect(type) {
		$("#m-list-table tr").click(function(){
			var trArr = new Object(); // 한 행의 배열을 담을 객체 선언
			var tdArr = new Array(); // 배열 선언(사원번호, 부서, 이름, 직급)
			
			// 현재 클릭된 Row(<tr>)
			var tr = $(this);
			var td = tr.children();
						
			// 반복문을 이용해서 배열에 값을 담아 사용 가능
			td.each(function(i){
				tdArr.push(td.eq(i).text());
			});
			
			// td.eq(index)를 통해 값 가져와서 trArr 객체에 넣기
			trArr.memberNum = td.eq(0).text();
			trArr.division = td.eq(1).text();
			trArr.memberName = td.eq(2).text();
			trArr.rank = td.eq(3).text();
			
			// 객체에 데이터가 있는지 여부 판단
			if(type == "app") { // 결재자
				var checkedArrIdx = _.findIndex(appArr, { memberNum : trArr.memberNum }); // 동일한 값 인덱스 찾기
				appArrText = []; // 배열 비우기
				if(checkedArrIdx > -1) {
					_.remove(appArr, { memberNum : trArr.memberNum }); // 동일한 값 지우기
				}else {
					if(appArr.length < 3) { // 선택한 결재자 수가 3보다 작으면
						appArr.push(trArr); // 객체를 배열에 담기
					}else {
						alert("결재자는 3명까지만 선택할 수 있습니다.");
					}
				}
				appArr.forEach(function(el, index) {
					appArrText.push(el.division +" "+ el.memberName +" "+ el.rank);
				});
				$("#s-list").html(appArrText.join("<br>")); // 개행해서 s-list 영역에 출력
			}else if(type == "ref") { // 참조자
				var checkedArrIdx = _.findIndex(refArr, { memberNum : trArr.memberNum }); // 동일한 값 인덱스 찾기
				refArrText = []; // 배열 비우기
				if(checkedArrIdx > -1) {
					_.remove(refArr, { memberNum : trArr.memberNum }); // 동일한 값 지우기
				}else {
					refArr.push(trArr);
				}
				refArr.forEach(function(el, index) {
					refArrText.push(el.division +" "+ el.memberName +" "+ el.rank);
				});
				$("#s-list").html(refArrText.join("<br>")); // 개행해서 s-list 영역에 출력
			}
		});
	}
	
	// 선택한 결재자/참조자 문서 작성 페이지에 표시
	function appSelView() {
		if(varType == "app") {
			for(var i = 0; i < 3; i++) { // 전에 입력한 값이 있을 경우 대비 초기화
				$("#r-app" + i).text("");
				$("#name-app" + i).text("");
				$("#num-app" + i).val("");
			}
			var app = []; // 결재자 담을 배열 선언
			appArr.forEach(function(el, i){
				$("#r-app" + i).text(el.rank);
				$("#name-app" + i).text(el.memberName);
				app[i] = el.memberNum;
			});
			$("#num-app").val(app);
		}else if(varType == "ref"){
			$("#ref-list").text(refArrText.join(", "));
			var ref = []; // 참조자 담을 배열 선언
			refArr.forEach(function(el, i){
				ref[i] = el.memberNum;
			})
			$("#num-ref").val(ref);
		}
	}
</script>

</html>