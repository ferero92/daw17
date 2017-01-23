<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="connect.jsp" %>
<title>Insert title here</title>
<% String profesores = (String) session.getAttribute("profesores"); %>
</head>
<body>
	<div class="container">
		<p class="col-sm-offset-9"><a href="admin.jsp">Panel de control</a> | <a href="../index.jsp">Cerrar sesión</a></p>
		<div class="page-header">
			<h2>Dar de baja un usuario</h2>
		</div>
		<form action="../NewPass" method="post" class="col-sm-offset-2 col-sm-4">
			<div class="form-group">
				<label for="id">Id del usuario a deshabilitar:</label>
				<input type="text" name="id" value="" class="form-control">
			</div>
    		<button type="submit" name="password" value="DISABLED" class="btn btn-danger">Deshabilitar</button>
  		</form>
  		<div class="col-sm-offset-1 col-sm-2">
	  		<table class="table table-condensed">
		  		<thead>
					<tr>
		  				<th>id</th>
		  				<th>Nombre</th>
		  				<th>Email</th>
		  			</tr>
		  		</thead>
		  		<tbody>
		  			<%=profesores%>
		  		</tbody>
		  	</table>
	  	</div>
	</div>
</body>
</html>