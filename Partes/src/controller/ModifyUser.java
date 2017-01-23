package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Connect;
import mylib.db.MyQuery;

/**
 * Servlet implementation class ModifyUser
 */
@WebServlet("/ModifyUser")
public class ModifyUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		try {
			Connect connect = new Connect();
			String[] atributes = {"usuarios", "form"};
			String[] statement = {"users", "users:id:"+id};
			
			if(connect.isConnect()){
				for (int i = 0; i < atributes.length; i++) {
					MyQuery query = new MyQuery(connect, statement[i]);
					PreparedStatement ps = query.preparedStatement(MyQuery.SELECT_AND);
					ResultSet result = ps.executeQuery();
					
					String content = "";
					
					switch (i) {
					case 0:
						content += "<option value='0'>--Seleccione usuario--</option>";
						while(result.next()){
							content += 	"<option value='"+result.getString("id")+"'>";
							
							switch (result.getInt("type")) {
							case 0:
								content += "Secretario: ";
								break;
							case 1:
								content += "Profesor: ";
								break;
							case 2:
								content += "Mantenimiento: ";
								break;
							}
							content += 	result.getString("first_name")+" "+result.getString("last_name")+"</option>";
						}
						break;
					case 1:
						result.next();
						
						content += 	"<div class='form-group hidden'>"+
										"<label for='nombre'>id: </label>"+
										"<input "+
											"type='text' "+
											"name='id'"+
											"class='form-control' "+
											"value='"+result.getString("id")+
										"'>"+
									"</div>"+
									"<div class='form-group'>"+
										"<label for='nombre'>Nombre: </label>"+
										"<input "+
											"type='text' "+
											"name='first_name'"+
											"class='form-control' "+
											"value='"+result.getString("first_name")+
										"'>"+
									"</div>"+
									"<div class='form-group'>"+
										"<label for='apellidos'>Apellidos: </label>"+
										"<input "+
											"type='text' "+
											"name='last_name'"+
											"class='form-control' "+
											"value='"+result.getString("last_name")+
										"'>"+
									"</div>"+
									"<div class='form-group'>"+
										"<label for='nombre'>Email: </label>"+
										"<input "+
											"type='email' "+
											"name='email'"+
											"class='form-control' "+
											"value='"+result.getString("email")+
										"'>"+
									"</div>"+
									"<div class='form-group'>"+
										"<label for='pwd'>Contraseña: </label>"+
										"<input "+
											"type='text' "+
											"name='password'"+
											"class='form-control' "+
											"value='"+result.getString("password")+
										"'>"+
									"</div>";
						break;
					}
					request.getSession().setAttribute(atributes[i], content);
				}
				response.setHeader("refresh", "0;URL=pages/modifyuser.jsp");
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
