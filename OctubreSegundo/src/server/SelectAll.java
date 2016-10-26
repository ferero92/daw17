package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectAll
 */
@WebServlet("/SelectAll")
public class SelectAll extends HttpServlet {
	
	String db = "//localhost:3306/MUSICA";
	String table = "Album";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			MyConnect connect = new MyConnect(db);
			String string = table+":grupo";
			
			if(connect.isConnect()){
				MyQuery query = new MyQuery(connect, string);
				PreparedStatement ps = query.preparedStatement(MyQuery.SELECT_ALL);
				ResultSet result = ps.executeQuery();
				
				 if (result.isBeforeFirst()){
					 
					 out.println(
							 "<table>"+
								"<tr>"+
									"<td>Nº Etq</td>"+
									"<td>Grupo</td>"+
									"<td>Título</td>"+
									"<td>Género</td>"+
									"<td>Dispositivos</td>"+
									"<td>Observaciones</td>"+
								"<tr>"
					 );					 
					 while(result.next()){
						out.println("<tr>");
						for (int i = 1; i <= 6; i++) {
							 out.println("<td>"+result.getString(i)+"</td>");
						}
						out.println("</tr>");
					 }
					 out.println("</table>"+
							 "<a href='index.html'>Inicio</a>"
					 );
				 }
				 else					 
					 out.println("no hay ningún record");				
			}
			else
				out.println("Error al conectar con la Base de Datos");
		} catch (Exception e) {
			out.println("oops algo salió mal");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
