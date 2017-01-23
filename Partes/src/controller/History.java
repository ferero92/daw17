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
 * Servlet implementation class History
 */
@WebServlet("/History")
public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public History() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		String string = "report r, state s:";
		
		try {
			Connect connect = new Connect();
			
			if(connect.isConnect()){
				string += "user:"+user.getId()+":r.state&s.id ORDER BY date DESC";
				MyQuery query = new MyQuery(connect, string);
				PreparedStatement ps = query.preparedStatement(MyQuery.SELECT_AND);
				ResultSet result = ps.executeQuery();
				
				String table = "";
				
				if(result.isBeforeFirst()){
					while(result.next()){
						table += "<tr>"+
									"<td class='id'>"+result.getString("id")+"</td>"+
									"<td>"+
										"<textarea class='description'>"+
											result.getString("description")+
										"</textarea>"+
									"</td>"+
									"<td>"+
										"<input type='text' class='datepicker' name='date' value='"+
											result.getString("date")+
										"'>"+
									"</td>"+
									"<td>";
						if(result.getString("fixed").equals("0000-00-00"))
							table += 	"-";
						else
							table += 	result.getString("fixed");
						table += 	"</td>"+
									"<td>"+result.getString("name")+"</td>"+
									"<td>"+
										"<a class='modify' href='#'>"+
											"<span class='glyphicon glyphicon-pencil'></span>"+
										"</a>"+
									"</td>";
						if(result.getString("state").equals("1")){
							table +="<td>"+
										"<a class='check' href='#'>"+
											"<span class='glyphicon glyphicon-ok'></span>"+
										"</a>"+
									"</td>";
						}
						table += "</tr>";
					}
				}
				else
					table = "Todavía no has notificado ningún parte";
				request.getSession().setAttribute("tbody", table);
				response.setHeader("refresh", "0;URL=pages/history.jsp");
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
