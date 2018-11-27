<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="generator" content="Mobirise v4.8.7, mobirise.com">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1">
<link rel="shortcut icon" href="assets/images/logo2.png" type="image/x-icon">
<meta name="description" content="Web Page Generator Description">
<title>Listado empleados</title>


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


<!--     <style>
/*         tr:first-child{ */
/*             font-weight: bold; */
/*             background-color: #C6C9C4; */
/*         } */
    </style> -->

</head>


<body>
	<h2>Listado de empleados</h2>

				<div class="table-responsive p-4">
				<table class="table table-striped">
				<thead class="thead-dark">
					<tr class="table-heads ">
						<th class="">NAME</th>
						<th class="">DATE</th>
						<th class="">SALARY</th>
						<th class="">ID</th>
						<th class="">DELETE</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${empleados}" var="empleado">
						<tr>
							<td class="">${empleado.nombre}</td>
							<td class="">${empleado.fechaAlta}</td>
							<td class="">${empleado.salario}</td>
							<td class=""><a href="<c:url value='/edit-${empleado.dni}-empleado' />">${empleado.dni}</a></td>
							<td class=""><a href="<c:url value='/delete-${empleado.dni}-empleado' />">delete</a></td>
						</tr>
					</c:forEach>
			</table>
			<br /> <a href="<c:url value='/new' />">Añadir nuevo empleado</a>
			
</div>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

		
</body>
</html>