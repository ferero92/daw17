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
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	
	String db = "//localhost:3306/MUSICA";
	String table = "Album";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
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
			
			String grupo = request.getParameter("grupo");
			String titulo = request.getParameter("titulo");
			String genero = request.getParameter("genero");
			String dispositivo = request.getParameter("dispositivo");
			String obs = request.getParameter("obs");
			
			String string = table+":"+grupo+":"+titulo+":"+genero+":"+dispositivo+":"+obs;
			
			if (connect.isConnect()) {
				MyQuery query = new MyQuery(connect, string);
				
				PreparedStatement ps = query.preparedStatement(MyQuery.INSERT);
				ps.executeUpdate();
				
				out.println("Registro insertado");
				response.setHeader("refresh", "2;URL=index.html");
			}
			else 
				out.println("error al acceder a la base de datos");
			
		} catch (Exception e) {
			out.println("oops, algo salió mal");
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
