<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>New Client - SamuProject</title>

<!-- Bootstrap core CSS-->
<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template-->
<link href="${pageContext.request.contextPath}/assets/css/sb-admin.css"
	rel="stylesheet">

</head>

<body class="bg-dark">

	<div class="container">
		<div class="card card-register mx-auto mt-5">
			<form:form method="POST" modelAttribute="cliente">
				<form:input type="hidden" path="id" id="id" />
				<div class="card-header">Register a new Client</div>
				<div class="card-body">
					<form>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-label-group">
										<form:input path="dni" id="dni" class="form-control" placeholder="Id Nr." required="required"	autofocus="autofocus" />
										<label for="dni">ID Nr. </label>
										<form:errors path="dni" cssClass="error" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-label-group">
										<form:input path="nombre" id="nombre" class="form-control" placeholder="Name" required="required" autofocus="autofocus" />
										<label for="nombre">Name </label>
										<form:errors path="nombre" cssClass="error" />
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-label-group">
										<form:input path="primerApellido" id="primerApellido" class="form-control" placeholder="Surname" required="required" autofocus="autofocus" />
										<label for="nombre">Surname </label>
										<form:errors path="nombre" cssClass="error" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-label-group">
										<form:input path="segundoApellido" id="segundoApellido" class="form-control" placeholder="Second Surname" required="required" autofocus="autofocus" />
										<label for="segundoApellido">Second Surname </label>
										<form:errors path="segundoApellido" cssClass="error" />
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md">
									<div class="form-label-group">
										<form:input path="email.email" id="email" class="form-control" placeholder="Email" required="required" autofocus="autofocus"/>
										<label for="email">Email: </label>
										<form:errors path="email.email" cssClass="error" />
									</div>
								</div>
							</div>
						</div>
						<c:choose>
							<c:when test="${edit}">
								<input type="submit" value="Update"
									class="btn btn-primary btn-block" />
							</c:when>
							<c:otherwise>
								<input type="submit" value="Register"
								class="btn btn-primary btn-block" />
							</c:otherwise>
						</c:choose>
					</form>
					
				</div>
			</form:form>
			<div class="text-center">
				<a class="d-block small mt-3" href="<c:url value='/list' />">Back</a>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script
		src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath}/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

</body>

</html>
