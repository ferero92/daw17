<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="connect.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0">
<title>Hola <c:out value="${user.getName()}"></c:out></title>
<link rel="stylesheet" href="../bootstrap/bootstrap.min.css" media="screen" title="no title">
<script src="../bootstrap/jquery-3.1.1.min.js" charset="utf-8"></script>
<script src="../bootstrap/bootstrap.min.js" charset="utf-8"></script>
<script src="../script/code.js" charset="utf-8"></script>
</head>
<body onload="active(${user.getState()}), position()">
	<div class="container">
		<p class="col-sm-offset-9"><a href="change.jsp">Cambiar contraseña</a> | <a href="../index.jsp">Cerrar sesión</a></p>
		<ul class="nav nav-tabs">
			<li class="active"><a href="clockin.jsp">Fichar</a></li>
			<li><a href="historial.jsp">Historial</a></li>
		</ul>
		<br>
		<form class="col-lg-offset-1 col-lg-3" action="state.jsp" method="post">
			<button class="btn btn-lg btn-warning btn-block active" type="submit" value="1" name="action">Inicio jornada</button>
			<button class="btn btn-lg btn-warning btn-block disabled" type="submit" value="2" name="action">Inicio pausa</button>
			<button class="btn btn-lg btn-warning btn-block disabled" type="submit" value="1" name="action">Fin pausa</button>
			<button class="btn btn-lg btn-warning btn-block disabled" type="submit" value="0" name="action">Fin jornada</button>
			<input type="text" value="" name="position" style="visibility: hidden;">
		</form>
		<figure class="col-lg-offset-2 col-lg-5">
			<a id="map" href="" target="_blank">
				<img src="" style="height: 100%; width: 100%;">
			</a>
			<figcaption><small></small></figcaption>
		</figure>
	</div>
</body>
</html>