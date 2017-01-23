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
import model.User;

/**
 * Servlet implementation class Session
 */
@WebServlet("/Session")
public class Session extends HttpServlet {
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
				
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(email.equals("") || password.equals(""))
			error(request, response, "RELLENE TODOS LOS CAMPOS");
		
		try {
			Connect connect = new Connect();
			String string = "users:email:"+email+":password:"+password;
			
			if(connect.isConnect()){
				MyQuery query = new MyQuery(connect, string);
				PreparedStatement ps = query.preparedStatement(MyQuery.SELECT_AND);
				ResultSet result = ps.executeQuery();
				
				if(result.isBeforeFirst()){
					result.next();
					
					User user = new User(result.getInt("id"), result.getString("first_name")+" "+result.getString("last_name"), result.getString("email"), result.getString("password"), result.getInt("type"));
					request.getSession().setAttribute("user", user);
					
					response.setHeader("refresh", "0;URL=Forward");
				}
				else
					error(request, response, "USUARIO NO REGISTRADO");
			}
			else
				error(request, response, "NO SE PUDO CONECTAR CON LA BASE DE DATOS");
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
		
		request.getSession().setAttribute("error", error+"&../index.jsp");
		response.setHeader("refresh", "0;URL=pages/error.jsp");
	}

}
