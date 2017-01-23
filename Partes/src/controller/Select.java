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
 * Servlet implementation class Select
 */
@WebServlet("/Select")
public class Select extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Select() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		try {
			Connect connect = new Connect();
			String[] array = {"users:type:1", ""};
			String[] atributes = {"profesores", "partes"};
						
			if(connect.isConnect()){
				
				if(id == "-" || start == "" || end == null)
					array[1] = "report r, state s, users u:''::r.state&s.id AND r.user = u.id ORDER BY date DESC";
				else
					array[1] = "report r, state s, users u:user:"+id+":date BETWEEN '"+start+"' AND '"+end+"' AND r.state&s.id AND r.user = u.id ORDER BY date DESC";
				
				for (int i = 0; i < array.length; i++) {
					MyQuery query = new MyQuery(connect, array[i]);
					PreparedStatement ps = query.preparedStatement(MyQuery.SELECT_AND);
					ResultSet result = ps.executeQuery();
					
					String content = "";
					
					if(result.isBeforeFirst()){
						while(result.next()){
							if(i == 0){
								content += 	"<option value='"+result.getInt("id")+"'>"+
												result.getString("first_name")+" "+result.getString("last_name")+
											"</option>";
							}
							else{
								content +=	"<tr>"+
												"<td class='id'>"+result.getString("r.id")+"</td>"+
												"<td>"+
													result.getString("first_name")+" "+result.getString("last_name")+
												"</td>"+
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
												"<td>"+
													"<input type='text' class='datepicker' name='fixed' value='";
								if(result.getString("fixed").equals("0000-00-00"))
									content += 	"-";
								else
									content += 	result.getString("fixed");
								
									content +=		"'>"+
												"</td>"+
												"<td>"+result.getString("name")+"</td>"+
												"<td>"+
													"<a class='modify' href='#'>"+
														"<span class='glyphicon glyphicon-pencil'></span>"+
													"</a>"+
												"</td>"+
												"<td>"+
													"<a class='remove' href='#'>"+
														"<span class='glyphicon glyphicon-remove'></span>"+
													"</a>"+
												"</td>"+
											"</tr>";
							}
						}						
					}
					request.getSession().setAttribute(atributes[i], content);
				}
				response.setHeader("refresh", "0;URL=pages/select.jsp");
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
