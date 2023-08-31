<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

	// 경고창
<%
	String mesg = (String) request.getAttribute("mesg");
	
	if (mesg != null) {
%>
		alert(<%= mesg %>);
<%
	}
%>

	$(document).ready(function(){
		// submit 시 경고창
		$("form").on("submit", function (event){
			var member_num = $("#member_num").val().trim();
			var member_name = $("#member_name").val().trim();
			var ssn1 = $("#ssn1").val().trim();
			var ssn2 = $("#ssn2").val().trim();
			var email1 = $("#email1").val().trim();
			var email2 = $("#email2").val().trim();
			
			if (member_num.length != 4) {
				alert("사원번호 네 자리를 올바르게 입력하세요");
				$("#member_num").focus();
				event.preventDefault();
			} else if (member_name.length < 2) {
				alert("이름을 올바르게 입력하세요");
				$("#member_name").focus();
				event.preventDefault();
			} else if (ssn1.length < 6) {
				alert("주민등록번호를 올바르게 입력하세요");
				$("#ssn1").focus();
				event.preventDefault();
			} else if (ssn2.length < 7) {
				alert("주민등록번호를 올바르게 입력하세요");
				$("#ssn2").focus();
				event.preventDefault();
			} else if (email1.length == 0) {
				alert("이메일을 올바르게 입력하세요");
				$("#email1").focus();
				event.preventDefault();
			} else if (email2.length == 0) {
				alert("이메일을 올바르게 입력하세요");
				$("#email2").focus();
				event.preventDefault();
			} 
		})
	})
</script>

<!-- 
비밀번호 찾기
1. 사원번호 입력
2. 이름 입력
3. 주민번호
-> mail로 보내기 
 -->

<form action="PasswordSearchServlet" method="post">

	<table>
		<tr>
			<th>사원번호</th>
			<td><input type="text" name="member_num" id="member_num"
				placeholder="사원번호"></td>
		</tr>

		<tr>
			<th>이름</th>
			<td><input type="text" name="member_name" id="member_name"
				placeholder="이름"></td>
		</tr>

		<tr>
			<th>주민등록번호</th>
			<td><input type="text" name="ssn1" id="ssn1"> - <input
				type="text" name="ssn2" id="ssn2"></td>
		</tr>

		<tr>
			<th>이메일</th>
			<td><input type="text" name="email1" id="email1"> @ <input
				type="text" name="email2" id="email2"></td>
		</tr>

		<tr>
			<td colspan="2">입력하신 이메일로 비밀번호가 전송됩니다.</td>
		</tr>

		<tr>
			<td colspan="2">
				<input type="submit" value="비밀번호 찾기" name="password">
			</td>
		</tr>
	</table>
</form>