<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<% session.invalidate(); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap/bootstrap.min.css" media="screen" title="no title">
<script src="bootstrap/jquery-3.1.1.min.js" charset="utf-8"></script>
<script src="bootstrap/bootstrap.min.js" charset="utf-8"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h2>Iniciar Sesión</h2>
		</div>
		<form action="Session" method="post" class="form-horizontal" style="margin-top: 5%;">
			<div class="form-group">
				<label class="control-label col-sm-offset-1 col-sm-3">Email:</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" name="email" placeholder="enter email">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-offset-1 col-sm-3">Password:</label>
				<div class="col-sm-5">
					<input class="form-control" type="password" name="password" placeholder="enter password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-5">
					<input class="btn btn-warning" type="submit" value="Iniciar sesión">
				</div>
			</div>			
		</form>
	</div>
</body>
</html>