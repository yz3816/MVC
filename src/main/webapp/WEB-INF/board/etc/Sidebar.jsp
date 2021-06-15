<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<div class="container-fluid">
		<div class="row">
	<nav id="sidebarMenu"
				class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
				<div class="position-sticky pt-3">
					<ul class="nav flex-column">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/board/content/list"> <span data-feather="home"></span>
								contentlist
						</a></li>
					<c:if test="${sessionScope.userRank=='A'}">
						<li class="nav-item"><a class="nav-link" href="/board/content/soccerlist"> <span
								data-feather="file"></span> Soccer
						</a></li>
						<li class="nav-item"><a class="nav-link" href="/board/content/baseballlist"> <span
								data-feather="shopping-cart"></span> Baseball
						</a></li>	
						
						<li class="nav-item"><a class="nav-link" href="/board/content/volleyballlist"> <span
								data-feather="users"></span> Volleyball
						</a></li>
						<li class="nav-item"><a class="nav-link" href="/board/content/humorlist"> <span
								data-feather="bar-chart-2"></span> Humor
						</a></li>
					
					</c:if>
					<c:if test="${sessionScope.userRank!='A'}">
						
						<c:if test="${sessionScope.userRank=='U'}">
								
						<li class="nav-item"><a class="nav-link" href="/board/content/soccerlist"> <span
								data-feather="file"></span> Soccer
						</a></li>
						
						<li class="nav-item"><a class="nav-link" href="/board/content/volleyballlist"> <span
								data-feather="users"></span> Volleyball
						</a></li>
						<li class="nav-item"><a class="nav-link" href="/board/content/humorlist"> <span
								data-feather="bar-chart-2"></span> Humor
						</a></li>	
							
							
						</c:if>	
						<c:if test="${sessionScope.userRank!='U'}">
						
							<c:if test="${sessionScope.userRank=='G'}">
								<li class="nav-item"><a class="nav-link" href="/board/content/soccerlist"> <span
										data-feather="file"></span> Soccer
								</a></li>
								
								<li class="nav-item"><a class="nav-link" href="/board/content/volleyballlist"> <span
										data-feather="users"></span> Volleyball
								</a></li>						
							
							</c:if>	
											<c:if test="${sessionScope.userRank!='G'}">
											</c:if>	
							
						</c:if>	
							
						
					</c:if>
					
						
						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="layers"></span> Integrations
						</a></li>
					</ul>

					<h6
						class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
						<span>Saved reports</span> <a class="link-secondary" href="#"
							aria-label="Add a new report"> <span
							data-feather="plus-circle"></span>
						</a>
					</h6>
					<ul class="nav flex-column mb-2">
						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="file-text"></span> Current month
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="file-text"></span> Last quarter
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="file-text"></span> Social engagement
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="file-text"></span> Year-end sale
						</a></li>
					</ul>
				</div>
			</nav>
			</div>
			</div>

</body>
</html>