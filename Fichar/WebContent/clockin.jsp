<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="mylib.db.*, java.sql.PreparedStatement, java.sql.ResultSet, server.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% User user = (User)session.getAttribute("user"); int state = 0;%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hola <%= user.getName() %></title>
<script src="code.js" charset="utf-8"></script>
	<%
		try{
			MyConnect connect = new MyConnect("//localhost:3306/fichar");
			String string = "usuarios:id:" + user.getId();
			
			if(connect.isConnect()){
				MyQuery query = new MyQuery(connect, string);
				PreparedStatement ps = query.preparedStatement(MyQuery.SELECT_WHERE);
				ResultSet result = ps.executeQuery();
				
				if (result.isBeforeFirst()){
					result.next();
					
					state = result.getInt("state");
				 }
			}
			else
				out.println("Error al conectar con la base de datos");
		}catch(Exception e){
			out.println("Oops algo salió mal");
		}
	%>
</head>
<body onload="active(<%= state %>)">
	<form action="State">
		<button type="submit" value="1" name="action" >Inicio jornada</button>
		<button type="submit" value="2" name="action" disabled>Inicio pausa</button>
		<button type="submit" value="1" name="action" disabled>Fin pausa</button>
		<button type="submit" value="0" name="action" disabled>Fin jornada</button>
	</form>
</body>
</html>