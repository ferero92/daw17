<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="connect.jsp" %>
<title>Insert title here</title>
<% 
	String profesores = (String) session.getAttribute("profesores");
	String partes = (String) session.getAttribute("partes");
%>
<script type="text/javascript">
	$(function () {
    	$('.datepicker').datepicker($.datepicker.regional["es"]);
	});
</script>
</head>
<body>
	<div class="container">
		<p class="col-sm-offset-9">| <a href="../index.jsp">Cerrar sesión</a></p>
		<div class="page-header">
			<h1>Registro de actividad</h1>
		</div>
		<form action="../Maintenance" method="post" class="form-inline">
			<div class="form-group">
				<label for="name">Profesor</label>
				<select name="id" class="form-control">
					<option value="-">--Selecciona empleado--</option>
					<%=profesores%>
				</select>
			</div>
			<div class="form-group">
				<label for="start">Fecha de inicio:</label>
				<input type="text" name="start" class="form-control datepicker">
			</div>
			<div class="form-group">
				<label for="end">Fecha fin:</label>
				<input type="text" name="end" class="form-control datepicker">
			</div>
			<input type="submit" value="Filtrar" class="btn btn-primary">
		</form>
		<table class="table table-condensed">
			<thead>
				<tr>
					<th>Nº parte</th>
					<th>Profesor</th>
					<th>Descripcion</th>
					<th>Fecha Incidencia</th>
					<th>Fecha Reparación</th>
					<th>Estado</th>
				</tr>
			</thead>
			<tbody><%=partes%></tbody>
		</table>
	</div>
</body>
</html>