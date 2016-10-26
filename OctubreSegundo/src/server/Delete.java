package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {

	String db = "//localhost:3306/MUSICA";
	String table = "Album";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Cookie[] cookies = request.getCookies();
		Cookie verify = null;

		for (int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("verify"))
				verify = cookies[i];
		}
		try {
			MyConnect connect = new MyConnect(db);
			String string = table+":id:"+verify.getValue();
			
			if(connect.isConnect()){
				MyQuery query = new MyQuery(connect, string);
				PreparedStatement ps = query.preparedStatement(MyQuery.DELETE);
				ps.executeUpdate();
				
				verify.setMaxAge(0);
				response.addCookie(verify);
				
				out.println("Registro borrado");
				response.setHeader("refresh", "2;URL=index.html");
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
