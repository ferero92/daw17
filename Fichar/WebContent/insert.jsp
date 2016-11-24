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
	<c:choose>
		<c:when test="${empty param.name}">
			<h1>Registro de usuario</h1>
			<form action="insert.jsp" method="post">
		    	<p>Nombre: <input type="text" name="name" value="" required></p>
		    	<p>Correo: <input type="text" name="email" value="" required></p>
		    	<input type="submit" name="submit" value="Registrar">
		    </form>
		</c:when>
		<c:otherwise>
			<sql:update dataSource="${db}">
				INSERT INTO usuarios VALUES(null, "${param.name}", "123", "${param.email}", 0)
			</sql:update>
			<p>Usuario creado con éxito</p>
			<% response.setHeader("refresh", "1;admin.html"); %>
		</c:otherwise>
	</c:choose>
</body>
</html>