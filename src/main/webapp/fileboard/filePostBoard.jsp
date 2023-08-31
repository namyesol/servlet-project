<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="com.dto.FileBoardDTO"%>
<%@page import="java.util.List"%>
<%
		FileBoardDTO dto = (FileBoardDTO) request.getAttribute("FileBoardPost");
			int file_board_no = dto.getFile_board_no();
			String member_num = dto.getMember_num();
			String file_board_date = dto.getFile_board_date();
			String file_board_title = dto.getFile_board_title();
			String file_board_content = dto.getFile_board_content();
			int file_board_view = dto.getFile_board_view();
			String file_name = dto.getFile_name();
			String file_rename = dto.getFile_rename();
			String file_path = dto.getFile_path();
		%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>�Խ��� �۾���</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>�Խ��� �� ��� ���� ����</h2>
		<form action="write.jsp" method="post">
			<div class="form-group">	<!-- ���� -->
				<label for="title">����</label>
				<!-- placeholder �Ӽ� �Է��� �����Ͱ� ���� ��� ������� ��Ÿ����.���������� �Է��� 100�ڱ����� ���� -->
				<!-- required �Ӽ��� �����ϸ� �ʼ��Է� �����̵ȴ�. -->
				<!-- pattern �Ӽ��� �̿��� ����ǥ�������� �������� ��ȿ�� �˻縦 �� �� �ִ�. -->
				<input type="text" class="form-control" id="title"
					placeholder="���� �Է�(4-100)" name="title" maxlength="100"
					required="required" pattern=".{4,100}" value="<%= file_board_title %>">
			</div>
			<div class="form-group"> <!-- ���� -->
				<label for="content">����</label>
				<!--  �������� �����͸� �Է��ϰ� �ϰ��� �Ҷ� textarea �±׸� ����Ѵ�. -->
				<!--  textarea �ȿ� �ִ� ��� ���ڴ� �״�� ��Ÿ����. ���鹮��, tag, enter -->
				<textarea class="form-control" rows="5" id="content" name="content"
					placeholder="���� �ۼ�"><%= file_board_content %></textarea>
			</div>
			<div class="form-group"> <!-- �ۼ��� -->
				<label for="writer">�ۼ���</label> <input type="text"
					class="form-control" id="writer" placeholder="�ۼ���(2��-10��)"
					name="writer" value="<%= member_num %>">
			</div>
			<button type="submit" class="btn btn-default">���</button>
			<button type="submit" class="btn btn-default">����</button>
			<button type="submit" class="btn btn-default">����</button>
		</form>
	</div>
</body>

</html>