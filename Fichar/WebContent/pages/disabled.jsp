<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="connect.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0">
<title>Deshabilitar</title>
<link rel="stylesheet" href="../bootstrap/bootstrap.min.css" media="screen" title="no title">
<script src="../bootstrap/jquery-3.1.1.min.js" charset="utf-8"></script>
<script src="../bootstrap/bootstrap.min.js" charset="utf-8"></script>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h2>Dar de baja un usuario</h2>
		</div>
		<form action="newpass.jsp" method="post" class="col-sm-offset-2 col-sm-4">
			<div class="form-group">
				<label for="id">Id del usuario a deshabilitar:</label>
				<input type="text" name="id" value="" class="form-control">
			</div>
    		<button type="submit" name="password" value="DISABLED" class="btn btn-danger">Deshabilitar</button>
  		</form>
  		<sql:query var="result" dataSource="${db}">
  			SELECT *
  			FROM usuarios
  		</sql:query>
  		<div class="col-sm-offset-1 col-sm-2">
  		<table class="table table-condensed">
			<tr>
  				<th>id</th>
  				<th>Nombre</th>
  				<th>Email</th>
  			</tr>
	  		<c:forEach var="row" items="${result.rows}" begin="1">
	  			<tr>
	  				<td>${row.id}</td>
	  				<td>${row.name}</td>
	  				<td>${row.email}</td>
	  			</tr>
	  		</c:forEach>
	  	</table>
	  	</div>
	</div>
</body>
</html>