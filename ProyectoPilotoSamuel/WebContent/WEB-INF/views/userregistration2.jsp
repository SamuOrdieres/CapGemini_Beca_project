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

<title>New User - SamuProject</title>

<!-- Bootstrap core CSS-->
<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template-->
<link href="${pageContext.request.contextPath}/assets/css/sb-admin.css"	rel="stylesheet">

</head>

<body class="bg-dark">

	<div class="container">
		<div class="card card-register mx-auto mt-5">
		
		<form:form method="POST" modelAttribute="user" class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>
				<div class="card-header">Register a user</div>
				<div class="card-body">
				<form>
           		<div class="form-group">
					<div class="form-row">
						<div class="col-md-6">
							<div class="form-label-group">
                    			
                        		<form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                        		<label for="firstName">First Name</label>
                        		<div class="has-error">
                            		<form:errors path="firstName" class="help-inline"/>
                        		</div>
                    		</div>
                		</div>
           				<div class="col-md-6">
							<div class="form-label-group">
                      		  <form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
                      		  <label for="lastName">Last Name</label>
                       		  <div class="has-error">
                      	  	   <form:errors path="lastName" class="help-inline"/>
                       		  </div>
                    		</div>
                		</div>
            		</div>
            	</div>
     
            	<div class="form-group">
					<div class="form-row">
						<div class="col-md-6">
							<div class="form-label-group">
                        		<c:choose>
                          		<c:when test="${edit}">
                                	<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" disabled="true"/>
                                	<label for="ssoId">SSO ID</label>
                            	</c:when>
                            	<c:otherwise>
                                	<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" />
                                	<label for="ssoId">SSO ID</label>
                                		<div class="has-error">
                                    		<form:errors path="ssoId" class="help-inline"/>
                                		</div>
                            	</c:otherwise>
                        		</c:choose>
                        		<label for="ssoId">SSO ID</label>
                    		</div>
                		</div>
                		<div class="col-md-6">
							<div class="form-label-group">
                        		<form:input type="password" path="password" id="password" class="form-control input-sm" />
                        		<label for="password">Password</label>
                        		<div class="has-error">
                            		<form:errors path="password" class="help-inline"/>
                        		</div>
                    		</div>
                		</div>
            		</div>
            	</div>
     
            <div class="form-group">
					<div class="form-row">
						<div class="col-md">
							<div class="form-label-group">
                        		<form:input type="text" path="email" id="email" class="form-control input-sm" />
                        		<label for="email">Email</label>
                        		<div class="has-error">
                            		<form:errors path="email" class="help-inline"/>
                        		</div>
                    		</div>
                		</div>
            		</div>
            	</div>
     
            <div class="form-group">
					<div class="form-row">
						<div class="col-md">
							<div class="form-label-group">
							
							
                        		<form:select path="userProfiles" class="form-control">
                        		<label for="userProfiles">Roles</label>
                        		<div class="has-error">
                            		<form:errors path="userProfiles" class="help-inline"/>
                        		</div>
                        		<form:options items="${roles}" itemLabel="type" itemValue="id"/>
                        		</form:select>
                        		
                    		</div>
                		</div>
            		</div>
            	</div>
     
            
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update" class="btn btn-primary btn-block"/> or <a href="<c:url value='/listusers' />">Cancel</a>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Register" class="btn btn-primary btn-block"/> or <a href="<c:url value='/listusers' />">Cancel</a>
                        </c:otherwise>
                    </c:choose>
                    </form>
                </div>
        </form:form>
	
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
