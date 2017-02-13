<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="bootstrap.jsp" %>
<title>Elecciones Consejo Escolar</title>
<%
	String count = (String) session.getAttribute("count");
	if(count == null)
		count = "Seleccione escrutinio a consultar";
	
	String category = (String) session.getAttribute("category");
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
			<li><a href="Controller?submit=1">Listado</a></li>
			<li><a href="vote.jsp">Votar</a></li>
			<li class="active"><a href="#">Escrutinio</a></li>
		</ul>
		<article>
			<h2>Seleccione escrutinio a consultar</h2>
			<div class="btn-group col-md-offset-3" data-type="1">
				<button type="button" value="1" class="btn btn-success">Profesorado</button>
				<button type="button" value="2" class="btn btn-success">Alumnado</button>
				<button type="button" value="3" class="btn btn-success">Personal Adminstración Servicios</button>
				<button type="button" value="4" class="btn btn-success">Madres, Padres, Tutores</button>
			</div>
			<div class="clearfix"></div>
			<div id="votation" data-category="<%=category%>">
				<table class="table table-striped">
					<thead>
						<tr>
							<th class="col-sm-2">Candidato</th>
							<th class="col-sm-1">Nª de votos</th>
							<th class="col-sm-9">Porcentaje de votos</th>
						</tr>
					</thead>
					<tbody><%=count%></tbody>
				</table>
				<button class="btn btn-warning pull-right" type="button">Reiniciar votos</button>
			</div>
		</article>
	</section>
</body>
</html>