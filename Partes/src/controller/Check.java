package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.Connect;
import model.From;
import mylib.db.MyQuery;
import mylib.mail.MyMail;

/**
 * Servlet implementation class Check
 */
@WebServlet("/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		
		String id = request.getParameter("id");
		
		try {
			Connect connect = new Connect();
			String string = "report:state:2:id:"+id;
			
			if(connect.isConnect()){
				MyQuery query = new MyQuery(connect, string);
				PreparedStatement ps = query.preparedStatement(MyQuery.MODIFY);
				ps.executeUpdate();
				
				query = new MyQuery(connect, "users:type:0");
				ps = query.preparedStatement(MyQuery.SELECT_AND);
				ResultSet result = ps.executeQuery();
				
				result.next();
				
				MyMail mail = new MyMail(
											new From(), 
											result.getString("email"), 
											"Incidencia solucionada con éxito", 
											"El profesor/a " + user.getName() +
												" que notificó la incidencia número " + id + 
												" ha verificado que está solucionada."
										);
				mail.send();
				
				response.setHeader("refresh", "0;URL=History");
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
