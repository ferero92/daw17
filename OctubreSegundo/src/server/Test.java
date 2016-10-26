package server;

import java.sql.PreparedStatement;

public class Test {
	
	public static void main(String[] args) {
		
		try{
		
			MyConnect connect = new MyConnect("//localhost:3306/MUSICA");
		
			MyQuery query = new MyQuery(connect, "Album:grupo:David Bisbal:id:2");
			
			PreparedStatement ps = query.preparedStatement(MyQuery.MODIFY);
			ps.executeUpdate();
			
			System.out.println("modificado");
			/*
			ResultSet result = ps.executeQuery();
			
			if (result.isBeforeFirst()){
				 
				 System.out.println(
						 "<table>"+
							"<tr>"+
								"<td>Nº Etq</td>"+
								"<td>Grupo</td>"+
								"<td>Título</td>"+
								"<td>Género</td>"+
								"<td>Dispositivos</td>"+
								"<td>Observaciones</td>"+
							"</tr>\n"
				 );					 
				 while(result.next()){
					System.out.println("<tr>");
					for (int i = 1; i <= 6; i++) {
						 System.out.println("<td>"+result.getString(i)+"</td>");
					}
					System.out.println("</tr>\n");
				 }
				 System.out.println("</table>");
			 }
			 else					 
				 System.out.println("no hay ningún record");*/
		}
		catch (Exception e){
			System.out.println("algo salió horriblemente mal");
		}
	}

}
