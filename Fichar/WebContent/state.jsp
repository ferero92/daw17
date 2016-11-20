<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="server.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="connect.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="code.js" charset="utf-8"></script>
</head>
<body>
	<c:set var="user" value="<%= (User)session.getAttribute(\"user\") %>"></c:set>
	<c:set var="now" value="<%= new java.util.Date() %>"></c:set>
	<fmt:formatDate var="time" type="time" value="${now}"/>
	<fmt:formatDate var="date" type="date" value="${now}"/>
	<c:forTokens items="${param.position}" delims="," begin="0" end="0" var="position">
		<c:set var="latitude" value="${position}" scope="application"></c:set>
	</c:forTokens>
	<c:forTokens items="${param.position}" delims="," begin="1" end="1" var="position">
		<c:set var="longitude" value="${position}" scope="application"></c:set>
	</c:forTokens>
	<c:forTokens items="${param.position}" delims="," begin="2" end="2" var="position">
		<c:set var="accuracy" value="${position}" scope="application"></c:set>
	</c:forTokens>
	<sql:update dataSource="${db}">
		UPDATE usuarios SET state = ${param.action} WHERE id = ${user.getId()}
	</sql:update>
	<jsp:setProperty property="state" name="user" value="${param.action}"/>
</body>
</html>