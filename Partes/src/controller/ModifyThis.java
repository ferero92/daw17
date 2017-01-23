package controller;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Connect;
import mylib.db.MyQuery;

/**
 * Servlet implementation class ModifyThis
 */
@WebServlet("/ModifyThis")
public class ModifyThis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyThis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		String[] parameters={
								"first_name",
								"last_name",
								"email",
								"password"
							};
		
		String[] values = 	{
								request.getParameter("first_name"),
								request.getParameter("last_name"),
								request.getParameter("email"),
								request.getParameter("password")
							};
		try {
			Connect connect = new Connect();
			
			if(connect.isConnect()){
				for (int i = 0; i < parameters.length; i++) {
					MyQuery query = new MyQuery(connect, "users:"+parameters[i]+":"+values[i]+":id:"+id);
					PreparedStatement ps = query.preparedStatement(MyQuery.MODIFY);
					ps.executeUpdate();
				}
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
		
		request.getSession().setAttribute("error", error+"&admin.jsp");
		response.setHeader("refresh", "0;URL=pages/error.jsp");
	}

}
