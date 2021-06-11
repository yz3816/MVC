<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if(${ment}==0) {
		alert('문제 발생함');
	}
</script>
</head>



<body>



	<form action="" method="post">
		<table border="1">
			<tr>
				<td><input type="text" value="${title}" name="title"></td>
				<td><textarea name="content">${content}</textarea></td>
			</tr>
		</table>

	

	
		<table border=1>
			<tr>

				<td><input type="submit" value="완료"> <input
					type="submit" value="취소" onclick="javascript:history.back(-1);"></td>

			</tr>




		</table>
	</form>




</body>
</html>