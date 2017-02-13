<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="bootstrap.jsp" %>
<title>Elecciones Consejo Escolar</title>
<% 
	String candidates = (String) session.getAttribute("candidates");
	String category = (String) session.getAttribute("category");
	String curses = (String) session.getAttribute("curses");
%>
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
			<li><a href="index.jsp">Añadir</a></li>
			<li class="active"><a href="#">Listado</a></li>
			<li><a href="vote.jsp">Votar</a></li>
			<li><a href="count.jsp">Escrutinio</a></li>
		</ul>
		<article>
			<h2 class="text-center">Candidatos disponibles</h2>
			<form class="form-inline col-sm-offset-1">
				<div class="form-group">
					<label for="category">Categoría:</label>
					<select name="filter_category" class="form-control">
						<option value="-">--Seleccione categoría--</option>
						<option value="1">Profesorado</option>
						<option value="2">Alumnado</option>
						<option value="3">Personal Administración y Servicios</option>
						<option value="4">Madres, Padres, Tutores</option>
					</select>
				</div>
				<div class="form-group">
					<label for="curses">Curso:</label>
					<select name="filter_curse" class="form-control">
						<option value="-">--Seleccione curso--</option>
						<%=curses%>
					</select>
				</div>
				<button type="button" name="filter" class="btn btn-success">Filtrar</button>
				<button type="button" name="remove" class="btn btn-danger disabled">Borrar curso seleccionado</button>
			</form>
			<table class="table table-condensed">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Apellidos</th>
						<th>Categoría</th>
						<th>Curso</th>
					</tr>
				</thead>
				<tbody>
					<%=candidates%>
				</tbody>
			</table>
		</article>
	</section>
</body>
</html>