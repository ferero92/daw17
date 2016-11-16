<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ include file="connect.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Insert title here</title>
</head>
<body>
	<!--sql:update dataSource="${db}">
		INSERT INTO Album VALUE(null, "Guns N' Roses", "Welcome to the jungle", "rock", "CD", "")
	</sql:update-->
	
	<sql:update dataSource="${db}">
		INSERT INTO Album VALUE(null, "${param.grupo}", "${param.titulo}", "${param.genero}", "${param.dispositivo}", "${param.obs}")
	</sql:update>
	<p>Álbum añadido con éxito</p>
	<c:redirect url="index.html"></c:redirect>
</body>
</html>