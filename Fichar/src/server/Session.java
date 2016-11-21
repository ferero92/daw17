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

import javax.servlet.http.HttpSession;
import mylib.db.*;

/**
 * Servlet implementation class Session
 */
@WebServlet("/Session")
public class Session extends HttpServlet {
	
	String db = "//localhost:3306/fichar";
	String table = "usuarios";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Session() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			MyConnect connect = new MyConnect(db);
			String string = table + ":email:" + email + ":password:" + password;
			
			if(connect.isConnect()){
				MyQuery query = new MyQuery(connect, string);
				PreparedStatement ps = query.preparedStatement(MyQuery.SELECT_AND);
				ResultSet result = ps.executeQuery();
				
				if(result.isBeforeFirst()){
					result.next();
					
					User user = new User(result.getInt("id"), result.getString("name"), result.getString("password"), result.getString("email"), result.getInt("state"));
					
					session.setAttribute("user", user);
					String redirect = "0;";
					
					if(user.getName().equals("admin"))
						redirect += "admin.html";
					else
						redirect += "clockin.jsp";
					
					response.setHeader("refresh", redirect);
				}
				else{
					out.println("Usuario no registrado");
					response.setHeader("refresh", "1;index.html");
				}
			}
			else
				out.println("Error al conectar con la Base de Datos");
		} catch (Exception e) {
			out.println("Oops algo salió mal");
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
