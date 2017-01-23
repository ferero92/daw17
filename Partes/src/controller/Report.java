package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mylib.db.MyQuery;
import mylib.mail.*;
import model.User;
import model.Connect;
import model.From;

/**
 * Servlet implementation class Report
 */
@WebServlet("/Report")
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Report() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String date = request.getParameter("date");
		String description = request.getParameter("description");
		
		if(date.equals("") || description.equals(""))
			error(request, response, "RELLENE TODOS LOS CAMPOS");
		
		User user = (User)request.getSession().getAttribute("user");
		String string = "report::"+description+":"+date+"::"+user.getId()+":0";
		
		try {
			Connect connect = new Connect();
			
			if(connect.isConnect()){
				MyQuery query = new MyQuery(connect, string);
				PreparedStatement ps = query.preparedStatement(MyQuery.INSERT);
				ps.executeUpdate();
				
				query = new MyQuery(connect, "users:type:2");
				ps = query.preparedStatement(MyQuery.SELECT_AND);
				ResultSet result = ps.executeQuery();
				
				result.next();
				
				MyMail mail = new MyMail(
											new From(), 
											result.getString("email"), 
											"Aviso de incidencia - IES La Marisma", 
											"El profesor/a " + user.getName() + 
												" ha notificado la siguiente incidencia: \n" + description + 
												" con fecha " + date
										);
				mail.send();
				
				response.setHeader("refresh", "0;URL=pages/report.jsp");
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
