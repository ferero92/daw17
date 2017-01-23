package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mylib.db.MyQuery;
import mylib.mail.MyMail;
import model.Connect;
import model.User;
import model.From;

/**
 * Servlet implementation class Modify
 */
@WebServlet("/Modify")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		
		String id = request.getParameter("id");
		
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> parameters = new ArrayList<String>();
		
		if(user.getType() != 2){
			names.add("description");
			names.add("date");
			
			parameters.add(request.getParameter("description"));
			parameters.add(request.getParameter("date"));
		}
		
		if(user.getType() != 1){
			names.add("fixed");
			parameters.add(request.getParameter("fixed"));
		}
		
		try {
			Connect connect = new Connect();
			
			if(connect.isConnect()){
				for (int i = 0; i < parameters.size(); i++) {
					if(i == 2 && parameters.get(i).equals("-"))
						continue;
					if(names.get(i).equals("fixed") && !(parameters.get(i).equals("-"))){
						int n = 0;
						switch (user.getType()) {
						case 0:
							n = 2;
							break;
						case 2:
							n = 1;
							break;
						}
						String string = "report:state:"+n+":id:"+id;
						MyQuery query = new MyQuery(connect, string);
						PreparedStatement ps = query.preparedStatement(MyQuery.MODIFY);
						ps.executeUpdate();
					}
					String string = "report:"+names.get(i)+":"+parameters.get(i)+":id:"+id;
					MyQuery query = new MyQuery(connect, string);
					PreparedStatement ps = query.preparedStatement(MyQuery.MODIFY);
					ps.executeUpdate();
				}
				String redirect = "0;URL=";
				
				switch (user.getType()) {
				case 0:
					redirect += "Select";
					break;
				case 1:
					redirect += "History";
					
					MyQuery query = new MyQuery(connect, "users:type:2");
					PreparedStatement ps = query.preparedStatement(MyQuery.SELECT_AND);
					ResultSet result = ps.executeQuery();
					result.next();
					
					MyMail mail = new MyMail(
												new From(), 
												result.getString("email"), 
												"Aviso de incidencia - IES La Marisma", 
												"Se ha modificado el parte número "+ id +": \n" + 
												parameters.get(0) + " con fecha " + parameters.get(1)
											);
					mail.send();
					break;
				case 2:
					redirect += "Maintenance";
					
					MyQuery query2 = new MyQuery(connect, "report:id:"+id);
					PreparedStatement ps2 = query2.preparedStatement(MyQuery.SELECT_AND);
					ResultSet result2 = ps2.executeQuery();
					
					result2.next();
					
					String idUser = result2.getString("user");
					
					query2 = new MyQuery(connect, "users:id:"+idUser);
					ps2 = query2.preparedStatement(MyQuery.SELECT_AND);
					result2 = ps2.executeQuery();
					
					result2.next();
					
					MyMail mail2 = new MyMail(
												new From(),
												result2.getString("email"),
												"Incidencia pendiente de revisión - IES La Marisma",
												"El parte de incidencia número "+id+" espera su comprobación. Gracias."
											);
					mail2.send();
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
		
		request.getSession().setAttribute("error", error+"&admin.jsp");
		response.setHeader("refresh", "0;URL=pages/error.jsp");
	}

}
