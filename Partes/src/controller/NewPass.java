package controller;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Connect;
import model.User;
import mylib.db.MyQuery;

/**
 * Servlet implementation class NewPass
 */
@WebServlet("/NewPass")
public class NewPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String password = request.getParameter("password");
		String id = request.getParameter("id");
		
		User user = (User) request.getSession().getAttribute("user");
		
		try {
			Connect connect = new Connect();
			String string = "users:password:"+password+":id:"+id;
			
			if(connect.isConnect()){
				MyQuery query = new MyQuery(connect, string);
				PreparedStatement ps = query.preparedStatement(MyQuery.MODIFY);
				ps.executeUpdate();
				
				String redirect = "0;URL=pages/";
				
				switch (user.getType()) {
				case 0:
					redirect += "admin.jsp";
					break;
				case 1:
					redirect += "report.jsp";
					break;
				case 2:
					redirect += "maintenance.jsp";
					break;
				}
				response.setHeader("refresh", redirect);
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
