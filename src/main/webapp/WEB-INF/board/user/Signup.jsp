<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS -->
<link href="${root}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${root}/css/user/form-validation.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Sign up</title>

<script type="text/javascript" src="${root}/js/user/signup.js"></script>


</head>
<body>

	<main>
		<div class="py-5 text-center">
			<img class="d-block mx-auto mb-4"
				src="../assets/brand/bootstrap-logo.svg" alt="" width="72"
				height="57">
			<h2>회원가입</h2>
			<p class="lead">회원가입을 환영합니다.</p>
		</div>

		<div class="row g-5">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="col-md-12 col-lg-12">
					<h4 class="mb-3">회원 정보</h4>

					<form class="needs-validation" novalidate id="joinform"
						name="joinform" action="" method="post"
						onsubmit="return createFrom(this)">

						<div class="row g-3">

							<div class="col-12">
								<label for="ID" class="form-label">ID</label>
								<div class="input-group has-validation">
									<span class="input-group-text">@</span> <input type="text"
										class="form-control" name="id" id="id" placeholder="id" required>
									<button type="button" onclick="idCheck(joinform, '${root}')">아이디
										중복체크</button>
									<div class="invalid-feedback">Your ID is required.</div>
								</div>
							</div>

							<div class="col-12">
								<label for="Password" class="form-label">Password</label> <input
									type="password" class="form-control" name="password"
									id="password" placeholder="password" required>
								<div class="invalid-feedback">Please enter your shipping
									address.</div>
							</div>

							<div class="col-12">
								<label for="passwordcheck" class="form-label">Passwordcheck
									<span class="text-muted"></span>
								</label> <input type="password" class="form-control" id="passwordcheck"
									placeholder="Passwordcheck">
							</div>

							<div class="col-sm-6">
								<label for="name" class="form-label">name</label> <input
									type="text" class="form-control" name="name" id="name"
									placeholder="input name" value="" required>
								<div class="invalid-feedback">Valid name is required.</div>
							</div>

							<div class="col-12">
								<label for="email" class="form-label">Email <span
									class="text-muted"></span></label> <input type="email"
									class="form-control" name="email" id="email" placeholder="you@example.com">
								<div class="invalid-feedback">Please enter a valid email
									address for shipping updates.</div>
							</div>


						</div>

						<hr class="my-4">

						<!-- primary 파란색 , secondary 회색 , success 녹색, danger 빨간색, warning 노란색, info 하늘색 -->
						<button class="w-100 btn btn-danger btn-lg" type="submit">회원가입</button>




					</form>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>




	</main>

	<h1>${root}</h1>



</body>
</html>