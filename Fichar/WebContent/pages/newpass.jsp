<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="connect.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<sql:update dataSource="${db}">
		UPDATE usuarios SET password = "${param.password}" WHERE id = "${param.id}"
	</sql:update>
	<c:choose>
		<c:when test="${param.password.equals('DISABLED')}">
			<p>Usuario deshabilitado</p>
			<% response.setHeader("refresh", "1;admin.html"); %>
		</c:when>
		<c:otherwise>
			<p>Contraseña cambiada</p>
			<% response.setHeader("refresh", "1;clockin.jsp"); %>
		</c:otherwise>
	</c:choose>
</body>
</html>