<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<% String tbody = (String) session.getAttribute("tbody"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="connect.jsp" %>
<title>Insert title here</title>
<script type="text/javascript">
	$(function () {
    	$('.datepicker').datepicker($.datepicker.regional["es"]);
	});
</script>
</head>
<body>
	<div class="container">
		<p class="col-sm-offset-9"><a href="change.jsp">Cambiar contraseña</a> | <a href="../index.jsp">Cerrar sesión</a></p>
		<ul class="nav nav-tabs">
			<li><a href="report.jsp">Notificar</a></li>
			<li class="active"><a href="History">Historial</a></li>
		</ul>
		<h2 style="margin:2% auto;">Incidencias reportadas</h2>
		<table class="table table-striped col-sm-8">
			<thead>
				<tr>
					<th>id</th>
					<th>Descripción</th>
					<th>Fecha Incidencia</th>
					<th>Fecha reparación</th>
					<th>Estado</th>
				</tr>
			</thead>
			<tbody><%=tbody%></tbody>
		</table>
	</div>
</body>
</html>