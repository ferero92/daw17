<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="connect.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0">
<title>Añadir usuario</title>
<link rel="stylesheet" href="../bootstrap/bootstrap.min.css" media="screen" title="no title">
<script src="../bootstrap/jquery-3.1.1.min.js" charset="utf-8"></script>
<script src="../bootstrap/bootstrap.min.js" charset="utf-8"></script>
</head>
<body>
	<c:choose>
		<c:when test="${empty param.name}">
			<div class="container">
				<div class="page-header">
					<h1>Registro de usuario</h1>
				</div>
				<form action="insert.jsp" method="post" class="col-sm-offset-3 col-sm-6">
					<div class="form-group">
						<label for="name">Nombre:</label>
						<input type="text" name="name" value="" class="form-control" required>
					</div>
			    	<div class="form-group">
			    		<label for="email">Email:</label>
			    		<input type="text" name="email" value="" class="form-control" required>
			    	</div>
			    	<div class="form-group">
			    		<label for="password">Password:</label>
			    		<input type="password" name="password" value="" class="form-control" required>
			    	</div>
			    	<input type="submit" name="submit" value="Registrar" class="btn btn-primary">
			    </form>
		    </div>
		</c:when>
		<c:otherwise>
			<sql:update dataSource="${db}">
				INSERT INTO usuarios VALUES(null, "${param.name}", "${param.password}", "${param.email}", 0)
			</sql:update>
			<p>Usuario creado con éxito</p>
			<% response.setHeader("refresh", "1;admin.html"); %>
		</c:otherwise>
	</c:choose>
</body>
</html>