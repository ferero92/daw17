<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="connect.jsp" %>
<title>Insert title here</title>
<%
	String usuarios = (String) session.getAttribute("usuarios");
	String form = (String) session.getAttribute("form");
%>
</head>
<body>
	<div class="container">
		<p class="col-sm-offset-9"><a href="admin.jsp">Panel de control</a> | <a href="../index.jsp">Cerrar sesión</a></p>
		<div class="page-header">
			<h1>Modificar usuario</h1>
		</div>
		<label for="usuarios">Seleccione usuario:</label>
		<select id="usuarios"><%=usuarios%></select>
		<br>
		<form action="../ModifyThis" class="col-sm-8 col-sm-offset-2">
			<%=form%>
			<button type="submit" class="btn btn-success">Modificar</button>
		</form>
	</div>
</body>
</html>