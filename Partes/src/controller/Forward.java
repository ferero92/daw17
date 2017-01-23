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
 * Servlet implementation class Forward
 */
@WebServlet("/Forward")
public class Forward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Connect connect = new Connect();
			String string = "report WHERE state = 0 AND TIMESTAMPDIFF(DAY, date, CURDATE()) > 7";
			
			if(connect.isConnect()){
				MyQuery query = new MyQuery(connect, string);
				PreparedStatement ps = query.preparedStatement(MyQuery.SELECT_AND);
				ResultSet result = ps.executeQuery();
				
				if(result.isBeforeFirst()){
					String content = "Las siguientes incidencias están aún pendientes: \n";
					
					while(result.next()){
						content += "Parte número "+result.getString("id")+": "+result.getString("description")+"\n";
					}
					
					query = new MyQuery(connect, "users:type:2");
					ps = query.preparedStatement(MyQuery.SELECT_AND);
					result = ps.executeQuery();
					
					result.next();
					
					MyMail mail = new MyMail(
												new From(), 
												result.getString("email"), 
												"Recordatorio de incidencia", 
												content
											);
					mail.send();
				}
				User user = (User) request.getSession().getAttribute("user");
				String redirect = "0;URL=";
				
				switch (user.getType()) {
				case 0:
					redirect += "pages/admin.jsp";
					break;
				case 1:
					redirect += "pages/report.jsp";
					break;
				case 2:
					redirect += "Maintenance";
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
		
		request.getSession().setAttribute("error", error+"&history.jsp");
		response.setHeader("refresh", "0;URL=pages/error.jsp");
	}

}
