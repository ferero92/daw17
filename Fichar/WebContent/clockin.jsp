<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="server.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="connect.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hola <c:out value="${user.getName()}"></c:out></title>
<link rel="stylesheet" href="style.css" media="screen" title="no title">
<script src="code.js" charset="utf-8"></script>
</head>
<body onload="active(${user.getState()}), position()">
	<p><a href="change.jsp">Cambiar contraseña</a> <a href="index.html">Cerrar sesión</a></p>
	<p><span>Fichar</span> <span>Historial</span></p>
	<div id="fichar" class="center">
		<form action="state.jsp" method="post">
			<button type="submit" value="1" name="action">Inicio jornada</button>
			<button type="submit" value="2" name="action" disabled>Inicio pausa</button>
			<button type="submit" value="1" name="action" disabled>Fin pausa</button>
			<button type="submit" value="0" name="action" disabled>Fin jornada</button>
			<input type="text" value="" name="position" style="visibility: hidden;">
		</form>
		<img id="map" src="" style="height: 30%; width: 30%;">
	</div>
	<div id="historial">
		<sql:query var="dates" dataSource="${db}">
			SELECT DISTINCT date FROM historial WHERE user = ${user.getId()}
		</sql:query>
		<form id="filter" action="clockin.jsp" method="post">
			<p>Fecha inicio:
				<select name="start">
					<option value="">--Seleccione fecha--</option>
					<c:forEach var="row" items="${dates.rows}">
						<option value="${row.date}">${row.date}</option>
					</c:forEach>
				</select>
			</p>
			<p>Fecha fin:
				<select name="end">
					<option value="">--Seleccione fecha--</option>
					<c:forEach var="row" items="${dates.rows}">
						<option value="${row.date}">${row.date}</option>
					</c:forEach>
				</select>
			</p>
			<input type="submit" value="Filtrar">
		</form>
		<table>
			<tr>
				<td>Acción</td>
				<td>Hora</td>
				<td>Fecha</td>
				<td>Geolocalización</td>
			</tr>
			<c:choose>
				<c:when test="${empty param.start}">
					<c:out value="historial total"></c:out>
					<sql:query var="total" dataSource="${db}">
						SELECT sec_to_time(SUM(time_to_sec(hours))) as 'total' 
						FROM jornada 
						WHERE user = ${user.getId()}
					</sql:query>
					<c:forEach var="sum" items="${total.rows}">
						<p>Total horas: ${sum.total}</p>
					</c:forEach>
					<sql:query var="result" dataSource="${db}">
						SELECT h.id, e.name, h.date, h.time, h.latitude, h.longitude
						FROM historial h, estados e 
						WHERE e.id = h.state AND h.user = ${user.getId()} 
						ORDER BY h.id
					</sql:query>
				</c:when>
				<c:otherwise>
					<c:out value="historial filtrado"></c:out>
					<sql:query var="total" dataSource="${db}">
						SELECT sec_to_time(SUM(time_to_sec(hours))) as 'total' 
						FROM jornada 
						WHERE date BETWEEN '${param.start}' AND '${param.end}'
						AND user = ${user.getId()}
					</sql:query>
					<c:forEach var="sum" items="${total.rows}">
						<p>Total horas: ${sum.total}</p>
					</c:forEach>
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
</body>
</html>