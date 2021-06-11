<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
<c:set var="root" value="${pageContext.request.contextPath}" />
<link href="${root}/css/bootstrap/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${root}/css/board/dashboard.css" rel="stylesheet">

<!-- bootstrap js -->
<script src="${root}/js/bootstrap/bootstrap.bundle.min.js" defer></script>
<script
	src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js"
	integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"
	integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha"
	crossorigin="anonymous" defer></script>
<script src="${root}/js/board/dashboard.js" defer></script>
</head>
<body>

<jsp:include page="/WEB-INF/board/etc/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/board/etc/Sidebar.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

				<h2>게시판</h2>
				<div class="table-responsive">
					<table class="table table-striped table-sm">
						<thead>
							<tr>
								<th>id</th>
								<th>ptitle</th>
								<th>writeid</th>
								<th>path</th>
								<th>regdate</th>
								<th>useFlag</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="li" items="${list}">

								<tr>
									<td>${li.id}</td>
									<td>${li.ptitle}</td>
									<td>${li.writeid}</td>
									<td>
									<img src="${root}${li.path}" width="150px" height="110px">
									</td>
									<td><fmt:formatDate pattern="yyyy/MM/dd/hh/mm/ss"
											value="${li.regdate}"></fmt:formatDate></td>
									<td>${li.useFlag}</td>
								</tr>


							</c:forEach>



						</tbody>
					</table>


					<div class="col-12">
						<div class="row">
							<div class="col-5">

								<!-- 검색  -->
								<form action="" method="get">
									<div>
										<select name="f">
											<option ${(param.f=="title")?"selected":"" } value="title">제목</option>
											<option ${(param.f=="writeid")?"selected":"" }
												value="writeid">글쓴이</option>
										</select> <input type="text" name="q"> <span><input
											type="submit" value="검색"></span>
									</div>
								</form>

							</div>
							<!-- 변수선언 -->
							<div class="col-6">
								<c:set var="page" value="${empty param.p?1:param.p}"></c:set>
								<c:set var="startNum" value="${page-(page-1)%5}"></c:set>
								<c:set var="lastNum"
									value="${fn:substringBefore(Math.ceil(count/10),'.')}"></c:set>

								<!-- 현재 페이지 -->
								<div>
									<div>
										<span> ${page} </span> / ${lastNum} pages
									</div>

								</div>

								<!-- 페이징처리 시작 -->
								<nav aria-label="Page navigation example">
									<ul class="pagination">


										<!-- 이전 페이지 -->

										<li class="page-item"><c:if test="${startNum > 1 }">
												<a class="page-link"
													href="?p=${startNum-1}&f=${param.f}&q=${param.q}">Prev</a>
											</c:if> <c:if test="${startNum <= 1 }">
												<a class="page-link" href="#" onclick="alert('첫 페이지입니다.');">Prev</a>
											</c:if></li>

										<!-- 숫자 페이지 -->
										<c:forEach var="i" begin="0" end="4">
											<li class="page-item"><c:if
													test="${param.p==(startNum+i)}">
													<c:set var="style" value="font-weight:bold; color:red;" />
												</c:if> <c:if test="${param.p!=(startNum+i)}">
													<c:set var="style" value="" />
												</c:if> <c:if test="${(startNum+i) <=lastNum }">
													<a style="${style}" class="page-link"
														href="?p=${startNum+i}&f=${param.f}&q=${param.q}">${startNum+i}</a>
												</c:if></li>
										</c:forEach>
										<!-- 다음 페이지 -->

										<li class="page-item"><c:if
												test="${startNum+5 <= lastNum }">
												<a class="page-link"
													href="?p=${startNum+5}&f=${param.f}&q=${param.q}">Next</a>
											</c:if> <c:if test="${startNum+5 >lastNum }">
												<a class="page-link" href="#"
													onclick="alert('마지막 페이지입니다.');">Next</a>
											</c:if></li>

										<!-- ----------------- -->
							</div>

							<div class="col-1">
								<!-- 글쓰기 버튼 -->

								<button class="btn btn-primary btn-lg" type="button"
									onclick="location.href='regedit'">write</button>
							</div>

						</div>

					</div>
				</div>

			</main>


		</div>


	</div>





</body>
</html>