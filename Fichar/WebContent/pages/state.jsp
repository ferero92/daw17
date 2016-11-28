<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="connect.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="code.js" charset="utf-8"></script>
</head>
<body>
	<c:set var="now" value="<%= new java.util.Date() %>"></c:set>
	<fmt:formatDate var="time" type="time" value="${now}"/>
	<fmt:formatDate var="date" pattern="yyyy-MM-dd" value="${now}"/>
	<c:forTokens items="${param.position}" delims="," begin="0" end="0" var="position">
		<c:set var="latitude" value="${position}" scope="application"></c:set>
	</c:forTokens>
	<c:forTokens items="${param.position}" delims="," begin="1" end="1" var="position">
		<c:set var="longitude" value="${position}" scope="application"></c:set>
	</c:forTokens>
	<sql:update dataSource="${db}">
		UPDATE usuarios SET state = ${param.action} WHERE id = ${user.getId()}
	</sql:update>
	<jsp:setProperty property="state" name="user" value="${param.action}"/>
	<c:if test="${user.getState() != 1}">
		<sql:query var="result" dataSource="${db}">
			SELECT time FROM historial WHERE date = '${date}' AND user = ${user.getId()} ORDER BY id DESC
		</sql:query>
		<c:forEach var="row" items="${result.rows}" begin="0" end="0">
			<sql:update dataSource="${db}">
				INSERT INTO jornada VALUES(null, ${user.getId()}, '${date}', TIMEDIFF('${time}', '${row.time}'))
			</sql:update>
		</c:forEach>
	</c:if>
	<sql:update dataSource="${db}">
		INSERT INTO historial VALUE(null, ${user.getId()}, '${time}', '${date}', '${latitude}', '${longitude}', ${user.getState()})
	</sql:update>
	<c:redirect url="clockin.jsp"></c:redirect>
</body>
</html>