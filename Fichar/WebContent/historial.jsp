<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="connect.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0">
<title>Hola <c:out value="${user.getName()}"></c:out></title>
<link rel="stylesheet" href="bootstrap/bootstrap.min.css" media="screen" title="no title">
<script src="bootstrap/jquery-3.1.1.min.js" charset="utf-8"></script>
<script src="bootstrap/bootstrap.min.js" charset="utf-8"></script>
<script src="code.js" charset="utf-8"></script>
</head>
<body onload="position()">
	<div class="container">
		<p class="col-sm-offset-9"><a href="change.jsp">Cambiar contraseña</a> | <a href="index.html">Cerrar sesión</a></p>
		<ul class="nav nav-tabs">
			<li><a href="clockin.jsp">Fichar</a></li>
			<li class="active"><a href="historial.jsp">Historial</a></li>
		</ul>
		<br>
		<form id="filter" action="historial.jsp" method="post" class="form-inline">
			<div class="form-group">
				<label for="start">Fecha inicio:</label>
				<input type="date" name="start" class="form-control">
			</div>
			<div class="form-group">
				<label for="end">Fecha fin:</label>
				<input type="date" name="end" class="form-control">
			</div>
			<input type="submit" value="Filtrar" class="btn btn-primary">
		</form>
		<c:choose>
			<c:when test="${empty param.start}">
				<sql:query var="total" dataSource="${db}">
					SELECT sec_to_time(SUM(time_to_sec(hours))) as 'total' 
					FROM jornada 
					WHERE user = ${user.getId()}
				</sql:query>
				
				<sql:query var="result" dataSource="${db}">
					SELECT h.id, e.name, h.date, h.time, h.latitude, h.longitude
					FROM historial h, estados e 
					WHERE e.id = h.state AND h.user = ${user.getId()} 
					ORDER BY h.id
				</sql:query>
			</c:when>
			<c:otherwise>
				<sql:query var="total" dataSource="${db}">
					SELECT sec_to_time(SUM(time_to_sec(hours))) as 'total' 
					FROM jornada 
					WHERE date BETWEEN '${param.start}' AND '${param.end}'
					AND user = ${user.getId()}
				</sql:query>
				<sql:query var="result" dataSource="${db}">
					SELECT h.id, e.name, h.date, h.time, h.latitude, h.longitude
					FROM historial h, estados e 
					WHERE h.date BETWEEN '${param.start}' AND '${param.end}' 
					AND h.user = ${user.getId()} 
					AND h.state = e.id 
					ORDER BY h.id
				</sql:query>
			</c:otherwise>
		</c:choose>
		<br>
		<c:forEach var="sum" items="${total.rows}">
			<button class="btn btn-warning col-lg-offset-9">Total horas: ${sum.total}</button>
		</c:forEach>
		<div class="panel-group col-lg-offset-1 col-lg-10" style="margin-top: 3%">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" href="#table">Pulsa para desplegar</a>
					</h4>
				</div>
				<div id="table" class="panel-collapse collapse">
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th>Acción</td>
									<th>Hora</td>
									<th>Fecha</td>
									<th>Geolocalización</td>
								</tr>
							</thead>
							<c:forEach var="row" items="${result.rows}">
								<tr>
									<td>${row.name}</td>
									<td>${row.time}</td>
									<td>${row.date}</td>
									<td>
										<c:choose>
											<c:when test="${row.latitude == 0}">
												No disponible
											</c:when>
											<c:otherwise>
												<a href="http://maps.google.com/maps/api/staticmap?center=${row.latitude},${row.longitude}&zoom=18&size=800x600&sensor=false&markers=${row.latitude},${row.longitude}&key=AIzaSyAtz3Oc21yeJ13PTp0pyLWsykVHjhMmVtI" target="_blank">
													Mostrar mapa
												</a>
											</c:otherwise>
										</c:choose>
						  	  		</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>