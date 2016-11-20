<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ include file="connect.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<sql:update dataSource="${db}">
		INSERT INTO usuarios VALUES(null, "${param.name}", "123", "${param.email}", 0)
	</sql:update>
	<p>Usuario creado con éxito</p>
	<% response.setHeader("refresh", "1;admin.html"); %>
</body>
</html>