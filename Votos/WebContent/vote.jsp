<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="bootstrap.jsp" %>
<title>Elecciones Consejo Escolar</title>
<%
	String votation = (String) session.getAttribute("votation");
	if(votation == null)
		votation = "Seleccione votación a realizar";
	
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
			<li class="active"><a href="#">Votar</a></li>
			<li><a href="count.jsp">Escrutinio</a></li>
		</ul>
		<article>
			<h2>Seleccione votación a realizar</h2>
			<div class="btn-group col-md-offset-3" data-type="0">
				<button type="button" value="1" class="btn btn-success">Profesorado</button>
				<button type="button" value="2" class="btn btn-success">Alumnado</button>
				<button type="button" value="3" class="btn btn-success">Personal Adminstración Servicios</button>
				<button type="button" value="4" class="btn btn-success">Madres, Padres, Tutores</button>
			</div>
			<div id="votation" data-category="<%=category%>">
				<%=votation%>
			</div>
			<div id="box"></div>
			<div id="confirm" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Candidatos seleccionados</h4>
						</div>
						<div class="modal-body">
							
						</div>
						<div class="modal-footer">
							<button class="btn btn-success">Votar</button>
						</div>
					</div>
				</div>
			</div>
		</article>
	</section>
</body>
</html>