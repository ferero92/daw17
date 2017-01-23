<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="connect.jsp" %>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<p class="col-sm-offset-10">| <a href="../index.jsp">Cerrar sesión</a></p>
		<div class="page-header">
			<h1>Panel del administrador</h1>
		</div>
  		<ul>
    		<li><a href="insert.jsp">Crear nuevo usuario</a></li>
    		<li><a href="../Select">Consultar actividad</a></li>
    		<li><a href="../ModifyUser?id=1">Modificar usuario</a></li>
    		<li><a href="../Disabled">Dar de baja usuario</a></li>
  		</ul>
	</div>
</body>
</html>