package controller;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mylib.db.MyQuery;
import model.Connect;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
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
		
		String name = request.getParameter("name");
		String last = request.getParameter("last");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			Connect connect = new Connect();
			String string = "users::"+name+":"+last+":"+email+":"+password+":1";
			
			if(connect.isConnect()){
				MyQuery query = new MyQuery(connect, string);
				PreparedStatement ps = query.preparedStatement(MyQuery.INSERT);
				ps.executeUpdate();
				
				response.setHeader("refresh", "0;URL=pages/admin.jsp");
			}
			else
				error(request, response, "NO SE PUDO CONECTAR A LA BASE DE DATOS");
		} catch (Exception e) {
			error(request, response, "ERROR GENÉRICO");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void error(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException{
		
		request.getSession().setAttribute("error", error+"&report.jsp");
		response.setHeader("refresh", "0;URL=pages/error.jsp");
	}

}
