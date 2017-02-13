<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="bootstrap.jsp" %>
<title>Elecciones Consejo Escolar</title>
</head>
<body>
	<header>
		<div class="container">
			<img src="images/logo_consejeriaeducacion.jpg" class="img-responsive col-sm-3">
			<h3 class="col-sm-offset-7">
				<strong>Elecciones al Consejo Escolar</strong>
				<br> 
				<small>I.E.S. La Marisma</small>
			</h3>
		</div>
	</header>
	<section class="container">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#">Añadir</a></li>
			<li><a href="Controller?submit=1">Listado</a></li>
			<li><a href="vote.jsp">Votar</a></li>
			<li><a href="count.jsp">Escrutinio</a></li>
		</ul>
		<article>
			<h2 class="text-center">Añadir candidato</h2>
			<form action="Controller" class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-4" for="first_name">Nombre:</label>
					<div class="col-sm-4">
						<input type="text" name="first_name" class="form-control" required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4" for="last_name">Apellidos:</label>
					<div class="col-sm-4">
						<input type="text" name="last_name" class="form-control" required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4" for="category">Categoría:</label>
					<div class="col-sm-4">
						<select name="category" class="form-control">
							<option value="1">Profesorado</option>
							<option value="2">Alumnado</option>
							<option value="3">Personal Administración y Servicios</option>
							<option value="4">Madres/Padres/Tutores</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4">
						<button type="submit" class="btn btn-success" name="submit" value="0">Añadir candidato</button>
					</div>
				</div>
			</form>
		</article>
	</section>
</body>
</html>