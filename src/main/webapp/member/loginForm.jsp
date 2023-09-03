<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

	// 쿠키에 사원번호, 비밀번호 저장 
	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value)
				+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
		
		console.log("setCookie")
	}

	// 쿠키 삭제 
	function deleteCookie(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1); // 어제날짜를 쿠키 소멸날짜로 설정
		document.cookie = cookieName + "= " + "; expires="
				+ expireDate.toGMTString();
		
		console.log("deleteCookie")

	}

	// 쿠키 가져오기 
	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if (start != -1) {
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if (end == -1)
				end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		
		console.log("getCookie")

		return unescape(cookieValue);
	}
	
	$(document).ready(function() {
	
		// 사원 번호 / 비밀번호 쿠키 저장
		var member_num = getCookie("member_num");
		$("#member_num").val(member_num);
		
		var password = getCookie("password");
		$("#password").val(password); 
		
		// 쿠키에 저장된 계정이 있을 경우 checked 속성값 = true
		if ($("#member_num").val().length != 0) {
			$("#saveCheck").attr("checked", "checked");
		}

		$("#saveCheck").on("change", saveCheck);
	})

	// 체크박스 함수 
	function saveCheck() {
		if ($(this).is(":checked")) { 			// 체크할 경우
			
			// 사원번호 저장 
			var member_num = $("#member_num").val();
			setCookie("#member_num", member_num, 365);
			// 비밀번호 저장 
			var password = $("#password").val();
            setCookie("password", password, 365);
            
			console.log("쿠키 저장");
		}else {									// 체크 해제할 경우 
			deleteCookie("member_num");			// 쿠키 삭제 
			
			console.log("쿠키 삭제");
		}
	}
</script>
<form action="LoginServlet" method="get">
				<h3>회사로고 이미지</h3>
				<div class="fieldset">
					<fieldset>
						<input type="text" name="member_num" placeholder="사원번호" style="text-align: center"><br>
						<input type="password" name="password" placeholder="비밀번호" style="text-align: center"><br>
						<button class="login_btn" onclick="submit">로그인</button><br>
						<input type="checkbox" id="saveCheck"/> 로그인 상태 유지 
					</fieldset>
				</div>
				<a href="passwordSearch.jsp"> 비밀번호 찾기 </a>
	</form>

	
	
	