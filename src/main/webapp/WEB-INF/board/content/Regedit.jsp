<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="root" value="${pageContext.request.contextPath }" />
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${root}/js/ckeditor/ckeditor.js"></script>

</head>
<body>

<jsp:include page="${root}/WEB-INF/board/etc/header.jsp"></jsp:include>

<jsp:include page="${root}/WEB-INF/board/etc/Sidebar.jsp"></jsp:include>

<div class="col-12">
<div class="row">
<div class="col-2"></div>
<div class="col-9">
	<hr>
	<form action="regedit" method="post">
		<table border=1>
			<tr>
				<td>글제목</td>
				<td><input type="text" name="write_title">
			</tr>

			<tr>
				<td>글쓴이</td>
				<td>${userNm }</td>
			</tr>


			<tr>
				<td>내용</td>
				<td><textarea class="form-control" name="write_content"></textarea></td>
			</tr>

			<tr>
				<td colspan=2><input type="submit" value="입력" name="wr">
					<!-- 서브밋은 온클릭 안되나? --> <input type="button" value="취소"
					onclick="javascript:history.back(-1);"></td>
			</tr>

		</table>
	</form>
	</div>
	<div class="col-1">
	</div>
	</div>
	</div>
	<script type="text/javascript">
		CKEDITOR.replace('write_content', {
			height : 500
		});
	</script>

</body>
</html>