<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="connect.jsp" %>
<title>Insert title here</title>
<script type="text/javascript">
	$(function () {
    	$('#datepicker').datepicker($.datepicker.regional["es"]);
	});
</script>
</head>
<body>
	<div class="container">
		<p class="col-sm-offset-9"><a href="change.jsp">Cambiar contraseña</a> | <a href="../index.jsp">Cerrar sesión</a></p>
		<ul class="nav nav-tabs">
			<li class="active"><a href="report.jsp">Notificar</a></li>
			<li><a href="../History">Historial</a></li>
		</ul>
		<h1>Notificar incidencia</h1>
		<form class="form-horizontal" action="../Report">
			<div class="form-group">
				<label class="control-label col-sm-2" for="date">Fecha:</label>
				<div class="col-sm-3">
					<input id="datepicker" type="text" class="form-control" name="date" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="description">Incidencia:</label>
				<div class="col-sm-7">
					<textarea class="form-control" name="description" rows="5" required></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-5">
					<button type="submit" class="btn btn-success">Notificar</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>