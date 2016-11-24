<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="connect.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="admin.html">Panel de control</a>
	<sql:query var="usuarios" dataSource="${db}">
		SELECT id, name FROM usuarios
	</sql:query>
	<sql:query var="fechas" dataSource="${db}">
		SELECT DISTINCT date FROM historial
	</sql:query>
	<form action="select.jsp" method="post">
		<p>Nombre de empleado:
			<select name="id">
				<option value="">--Selecciona empleado--</option>
				<c:forEach var="users" items="${usuarios.rows}" begin="1">
					<option value="${users.id}">${users.name}</option>
				</c:forEach>
			</select>
		</p>
		<p>Fecha inicio:
			<select name="start">
				<option value="">--Seleccione fecha--</option>
				<c:forEach var="dates" items="${fechas.rows}">
					<option value="${dates.date}">${dates.date}</option>
				</c:forEach>
			</select>
		</p>
		<p>Fecha fin:
			<select name="end">
				<option value="">--Seleccione fecha--</option>
				<c:forEach var="dates" items="${fechas.rows}">
					<option value="${dates.date}">${dates.date}</option>
				</c:forEach>
			</select>
		</p>
		<input type="submit" value="Filtrar">
	</form>
	<table>
		<tr>
			<td>Empleado</td>
			<td>Acción</td>
			<td>Hora</td>
			<td>Fecha</td>
			<td>Geolocalización</td>
		</tr>
		<c:choose>
			<c:when test="${empty param.id}">
				<sql:query var="result" dataSource="${db}">
					SELECT h.id, u.name AS 'empleado', e.name, h.date, h.time, h.latitude, h.longitude
					FROM historial h, usuarios u, estados e 
					WHERE h.state = e.id
					AND h.user = u.id 
					ORDER BY h.id
				</sql:query>
			</c:when>
			<c:otherwise>
				<sql:query var="total" dataSource="${db}">
					SELECT sec_to_time(SUM(time_to_sec(hours))) as 'total' 
					FROM jornada 
					WHERE date BETWEEN '${param.start}' AND '${param.end}'
					AND user = ${param.id}
				</sql:query>
				<c:forEach var="sum" items="${total.rows}">
					<p>Total horas: ${sum.total}</p>
				</c:forEach>
				<sql:query var="result" dataSource="${db}">
					SELECT h.id, u.name AS 'empleado', e.name, h.date, h.time, h.latitude, h.longitude
					FROM historial h, usuarios u, estados e 
					WHERE h.date BETWEEN '${param.start}' AND '${param.end}' 
					AND h.user = ${param.id} 
					AND h.state = e.id
					AND h.user = u.id 
					ORDER BY h.id
				</sql:query>
			</c:otherwise>
		</c:choose>
<%-- 			<c:if test="${not empty param.id }"> --%>
<%-- 				<sql:query var="total" dataSource="${db}"> --%>
<!-- 					SELECT sec_to_time(SUM(time_to_sec(hours))) as 'total'  -->
<!-- 					FROM jornada  -->
<%-- 					WHERE date BETWEEN '${param.start}' AND '${param.end}' --%>
<%-- 					AND user = ${param.id} --%>
<%-- 				</sql:query> --%>
<%-- 				<c:forEach var="sum" items="${total.rows}"> --%>
<%-- 					<p>Total horas: ${sum.total}</p> --%>
<%-- 				</c:forEach> --%>
<%-- 				<sql:query var="result" dataSource="${db}"> --%>
<!-- 					SELECT h.id, u.name AS 'empleado', e.name, h.date, h.time, h.latitude, h.longitude -->
<!-- 					FROM historial h, usuarios u, estados e  -->
<%-- 					WHERE h.date BETWEEN '${param.start}' AND '${param.end}'  --%>
<%-- 					AND h.user = ${param.id}  --%>
<!-- 					AND h.state = e.id -->
<!-- 					AND h.user = u.id  -->
<!-- 					ORDER BY h.id -->
<%-- 				</sql:query> --%>
<%-- 			</c:if> --%>
		<c:forEach var="row" items="${result.rows}">
			<tr>
				<td>${row.empleado}</td>
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
</body>
</html>