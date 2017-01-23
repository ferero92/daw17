<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String string = (String)session.getAttribute("error");
	String error = string.split("&")[0];
	String redirect = string.split("&")[1];
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>El servidor lanzó el siguiente error: <%=error%></h1>
	<% response.setHeader("refresh", "3;URL="+redirect); %>
</body>
</html>