<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="server.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
<head>
<% User user = (User)session.getAttribute("user");%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hola <%= user.getName() %></title>
<script src="code.js" charset="utf-8"></script>
</head>
<body onload="active(<%= user.getState() %>), position()">
	<form action="state.jsp" method="post">
		<button type="submit" value="1" name="action">Inicio jornada</button>
		<button type="submit" value="2" name="action" disabled>Inicio pausa</button>
		<button type="submit" value="1" name="action" disabled>Fin pausa</button>
		<button type="submit" value="0" name="action" disabled>Fin jornada</button>
		<input type="text" value="" name="position" style="visibility: hidden;">
	</form>
	<a href="change.jsp">Cambiar contraseña</a>
	<br>
	<img id="map" src="" style="height: 30%; width: 30%;">
</body>
</html>