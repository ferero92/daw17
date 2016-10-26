package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Modify
 */
@WebServlet("/Modify")
public class Modify extends HttpServlet {

	String db = "//localhost:3306/MUSICA";
	String table = "Album";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify() {
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
			
			String id = request.getParameter("id");
			String campo = request.getParameter("campo");
			String value = request.getParameter("value");
			
			String string = table+":"+campo+":"+value+":id:"+id;
			
			if(connect.isConnect()){
				MyQuery query = new MyQuery(connect, string);
				
				PreparedStatement ps = query.preparedStatement(MyQuery.MODIFY);
				ps.executeUpdate();
				
				out.println(
						"<form action='SelectAll' method='post'>"+
							"<p>Registro modificado</p>"+
							"<input type='submit' name='submit' value='Ver todos los registros'>"+
						"</form>"
				);
			}
			else
				out.println("Error al conectar con la base de datos");
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
