<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="connect.jsp" %>
<title>Añadir usuario</title>
</head>
<body>
	<div class="container">
		<p class="col-sm-offset-9"><a href="admin.jsp">Panel de control</a> | <a href="../index.jsp">Cerrar sesión</a></p>
		<div class="page-header">
			<h1>Registro de usuario</h1>
		</div>
		<form action="../Insert" method="post" class="col-sm-offset-3 col-sm-6">
			<div class="form-group">
				<label for="name">Nombre:</label>
				<input type="text" name="name" value="" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="name">Apellido:</label>
				<input type="text" name="last" value="" class="form-control" required>
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
</body>
</html>