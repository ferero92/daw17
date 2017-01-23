<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% model.User user = (model.User)session.getAttribute("user");%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta name="viewport" content="width=device-width; initial-scale=1.0">
<%@ include file="connect.jsp" %>
<title>Cambiar contraseña</title>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>Cambiar contraseña</h1>
		</div>
		<form action="../NewPass" method="post" class="col-sm-offset-4 col-sm-4">
    		<div class="form-group">
    			<label for="actual">Contraseña actual:</label>
    			<input type="password" name="old" onblur="verify(<%= user.getPassword() %>)" class="form-control" required>
    			<span style="visibility: hidden;" class="label label-danger">Contraseña incorrecta</span>
    		</div>
    		<div class="form-group">
    			<label for="new">Nueva contraseña:</label>
    			<input type="password" name="password" class="form-control" required>
    		</div>
    		<div class="form-group">
    			<label for="confirm">Confirmar contraseña:</label>
    			<input type="password" name="confirm" onblur="confirmar()" class="form-control" required>
    			<span style="visibility: hidden;" class="label label-danger">Error al confirmar la contraseña</span>
    		</div>
    		<button class="btn btn-warning" type="submit" name="id" value="${user.getId()}">Cambiar contraseña</button>
  		</form>
	</div>
</body>
</html>