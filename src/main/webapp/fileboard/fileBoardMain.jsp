<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="com.dto.FileBoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%List<FileBoardDTO> list = (List<FileBoardDTO>) request.getAttribute("fileContentList");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>fileBoard Main���</h1>
	0. ��� DB�ҷ��ͼ� �ѷ��ֱ� 1. ���̺� list����� 2. Ŭ���� �Խù� ��ȣ �ѱ��
	<hr>
	<table border=1>

		<tr>
			<th>�۹�ȣ</th>
			<!-- <th>�ۼ���</th> -->
			<th>�ۼ���</th>
			<th>����</th>
			<th>��ȸ��</th>
		</tr>
      <c:forEach var="file" items="${fileContentList}" varStatus="loop">
        <tr>
                <td>${file.file_board_no}</td>
            	<td>${file.file_board_date}</td>
               <td><a href="FileBoardPost?file_board_no=${file.file_board_no}">${file.file_board_title}</a></td>
               <td>${file_board_view}</td>
               
		</tr>
		</c:forEach>
	</table>
</body>

</html>