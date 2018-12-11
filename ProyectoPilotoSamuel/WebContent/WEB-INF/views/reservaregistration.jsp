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

<title>Nueva Reserva - SamuProject</title>

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
			<form:form method="POST" modelAttribute="reserva">
				<form:input type="hidden" path="id" id="id" />
				<div class="card-header">Registrar una reserva</div>
				<div class="card-body">
					<form>
					<div class="form-group">
							<div class="form-row">
								<div class="col-md">
									<div class="form-label-group">
										<form:select path="centroTuristico.id" id="centroTuristicoId" name="centroTuristicoId" class="form-control" placeholder="Centro Turistico" required="required" autofocus="autofocus">
										<label for="centroTuristicoId">Centro Turistico: </label>
										<form:errors path="centroTuristico.id" cssClass="error" />
										    <form:option value="-1">-- Selecciona el Centro Turistico --</form:option>
											<form:options items="${centrosTuristicos}" itemLabel="nombre" itemValue="id"/>
										</form:select>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md">
									<div class="form-label-group">
										<form:select path="cliente.id" id="clienteId" name="clienteId" class="form-control" placeholder="Cliente" required="required" autofocus="autofocus">
										<label for="clienteId">Cliente: </label>
										<form:errors path="cliente.id" cssClass="error" />
										    <form:option value="-1">-- Selecciona el Centro Turistico --</form:option>
											<form:options items="${clientes}" itemLabel="dni" itemValue="id"/>
										</form:select>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-label-group">
										<form:input path="fechaEntrada" id="fechaEntrada" class="form-control" required="required" autofocus="autofocus"/>
										<label for="fechaEntrada">Fecha de entrada: </label>
										<form:errors path="fechaEntrada" cssClass="error" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-label-group">
										<form:input path="fechaSalida" id="fechaSalida" class="form-control" required="required" autofocus="autofocus"/>
										<label for="fechaSalida">Fecha de salida: </label>
										<form:errors path="fechaSalida" cssClass="error" />
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
				<a class="d-block small mt-3" href="<c:url value='/allreservas' />">Listado de Reservas</a> 
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
