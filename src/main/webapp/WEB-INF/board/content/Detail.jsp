<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


		<table border="1">



			<tr>
				<td>${nt.board}</td>
				<td>${nt.id}</td>
				<td>${nt.title}</td>
				<td>${nt.content}</td>
				<td>${nt.writeid}</td>
				<td>${nt.regdate}</td>
				<td>${nt.hit}</td>
			</tr>

		

  


		</table>
		
		<div>
		<a href="modify?id=${nt.id}"><input type="button" value="수정하기"></a> 
		<input type="submit" value="삭제하기">
		<input type="button" value = "목록으로" onclick="location.href='/board/content/list?p=${p}&q=${q}&f=${f}'">
		</div>





</body>
</html>