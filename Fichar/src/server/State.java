package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mylib.db.*;

/**
 * Servlet implementation class State
 */
@WebServlet("/State")
public class State extends HttpServlet {
	
	String db = "//localhost:3306/fichar";
	String table = "usuarios";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public State() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
		User user = (User)request.getSession().getAttribute("user");

		try{
			MyConnect connect = new MyConnect(db);
			String string = table + ":state:" + action + ":id:" + user.getId();
			
			if(connect.isConnect()){
				MyQuery query = new MyQuery(connect, string);
				PreparedStatement ps = query.preparedStatement(MyQuery.MODIFY);
				ps.executeUpdate();
				
				user.setState(Integer.parseInt(action));
				request.getSession().setAttribute("user", user);
				
				response.setHeader("refresh", "0;URL=clockin.jsp");
			}
			else
				out.println("Error al conectar a la Base de Datos");
		}catch(Exception e){
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
