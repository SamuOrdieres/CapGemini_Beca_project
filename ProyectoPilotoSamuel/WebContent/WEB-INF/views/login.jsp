<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin - Login</title>

    <!-- Bootstrap core CSS-->
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
	<link href="${pageContext.request.contextPath}/assets/css/sb-admin.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/app.css" rel="stylesheet">
    
  </head>

  <body class="bg-dark">

    <div class="container">
      <div class="card card-login mx-auto mt-5">
        <div class="card-header">Login</div>
        <div class="card-body">
        	<c:url var="loginUrl" value="/login" />
            <form id="loginForm" action="${loginUrl}" method="post">
            	<c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                       <p>Invalid username and password.</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                       <p>You have been logged out successfully.</p>
                    </div>
                </c:if>
            
            
            <div class="form-group">
              <div class="form-label-group">
                <input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
                <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                
              </div>
            </div>
            <div class="form-group">
              <div class="form-label-group">
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                
              </div>
            </div>
            <div class="form-group">
              <div class="checkbox">
                <label>
                  <input type="checkbox" id="rememberme" name="remember-me">
                  Remember Password
                </label>
              </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
<!--        <a class="btn btn-primary btn-block" href="index.html">Login</a> -->
			<div class="form-actions">
            	<input type="submit" class="btn btn-block btn-primary btn-default" value="Log in">
            </div>
          </form>
          <div class="text-center">
            <a class="d-block small mt-3" href="userregistration">Register an Account</a>
            <a class="d-block small" href="forgot-password.html">Forgot Password?</a>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

  </body>

</html>
