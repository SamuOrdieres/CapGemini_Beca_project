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

<title>Nuevo Centro Turistico - SamuProject</title>

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
			<form:form method="POST" modelAttribute="centroTuristico">
				<form:input type="hidden" path="id" id="id" />
				<div class="card-header">Registrar un Centro Turistico</div>
				<div class="card-body">
					<form>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-label-group">
										<form:input path="nombre" id="nombre" class="form-control" placeholder="Nombre del centro turistico" required="required"	autofocus="autofocus" />
										<label for="nombre">Nombre: </label>
										<form:errors path="nombre" cssClass="error" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-label-group">
										<form:input path="habitaciones" id="habitaciones" class="form-control" placeholder="Nombre" required="required" autofocus="autofocus" type="number"/>
										<label for="habitaciones">Numero de habitaciones: </label>
										<form:errors path="habitaciones" cssClass="error" />
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
						<a class="d-block small mt-3" href="login.html">Login Page</a> <a
							class="d-block small" href="forgot-password.html">Forgot
							Password?</a>
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
