<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="server.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% User user = (User)session.getAttribute("user");%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="code.js" charset="utf-8"></script>
</head>
<body>
	<h1>Cambiar contraseña</h1>
  <form action="newpass.jsp" method="post">
    <p>Contraseña actual:<input type="password" name="old" onblur="verify(<%= user.getPassword() %>)" required><span style="visibility: hidden;">Contraseña incorrecta</span></p>
    <p>Nueva contraseña:<input type="password" name="password" required></p>
    <p>Confirmar contraseña:<input type="password" name="confirm" onblur="confirmar()" required><span style="visibility: hidden;">Error al confirmar la contraseña</span></p>
    <button type="submit" name="id" value="<%= user.getId() %>">Cambiar contraseña</button>
  </form>
</body>
</html>