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
import model.Connect;

/**
 * Servlet implementation class Disabled
 */
@WebServlet("/Disabled")
public class Disabled extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Disabled() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Connect connect = new Connect();
			String string = "users:type:1";
			
			if(connect.isConnect()){
				MyQuery query = new MyQuery(connect, string);
				PreparedStatement ps = query.preparedStatement(MyQuery.SELECT_AND);
				ResultSet result = ps.executeQuery();
				
				String profesores = "";
				
				if(result.isBeforeFirst()){
					while(result.next()){
						profesores += "<tr>"+
										"<td>"+result.getInt("id")+"</td>"+
										"<td>"+result.getString("first_name")+"</td>"+
										"<td>"+result.getString("last_name")+"</td>"+
									  "</tr>";
					}
				}
				request.getSession().setAttribute("profesores", profesores);
				response.setHeader("refresh", "0;URL=pages/disabled.jsp");
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
