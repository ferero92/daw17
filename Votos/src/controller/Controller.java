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
import model.Curse;
import mylib.db.MyQuery;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int submit = Integer.parseInt(request.getParameter("submit"));
		String curse = new Curse().getCurse();
		String filter = request.getParameter("filter");
		
		try {
			Connect connect = new Connect();
			
			if(connect.isConnect()){
				
				String statement = "candidates c, categories t";
				
				if(filter != null){
					int n = Integer.parseInt(filter);
					
					switch (n) {
					case 1:
						statement += ":c.category:"+request.getParameter("filter_category");
						break;
					case 2:
						statement += ":c.curse:"+request.getParameter("filter_curse");
						break;
					case 3:
						statement += ":c.category:"+request.getParameter("filter_category")+
									 ":c.curse:"+request.getParameter("filter_curse");
						break;
					}
					statement += ":c.category&t.id ORDER BY curse DESC";
				}
				else
					statement += " WHERE c.category = t.id ORDER BY curse DESC";
				
				MyQuery query = new MyQuery(connect, statement);
				PreparedStatement ps = query.preparedStatement(MyQuery.SELECT_AND);
				ResultSet result = ps.executeQuery();
				
				String candidates = "";
				
				if(result.isBeforeFirst()){
					while(result.next()){
						candidates += 	"<tr data-id='"+result.getInt("id")+"'>"+
											"<td class='col-sm-2'>"+
												"<input class='form-control' type='text' name='first_name' value='"+
													result.getString("first_name")+
												"'>"+
											"</td>"+
											"<td class='col-sm-2'>"+
												"<input class='form-control' type='text' name='last_name' value='"+
													result.getString("last_name")+
												"'>"+
											"</td>"+
											"<td class='col-sm-3'>"+
												"<select class='form-control' name='category' data-category='"+(result.getInt("category")-1)+"'>"+
													"<option value='1'>Profesorado</option>"+
													"<option value='2'>Alumnado</option>"+
													"<option value='3'>Personal Administración y Servicios</option>"+
													"<option value='4'>Madres, Padres y Tutores</option>"+
												"</select>"+
											"</td>"+
											"<td class='col-sm-1'>"+result.getString("curse")+"</td>"+
											"<td class='col-sm-1'>"+
												"<a href='#'>"+
													"<span class='glyphicon glyphicon-pencil'></span>"+
												"</a>"+
											"</td"+
											"<td class='col-sm-1'>"+
												"<a href='#'>"+
													"<span class='glyphicon glyphicon-remove'></span>"+
												"</a>"+
											"</td>"+
										"</tr>";
					}
				}
				else
					candidates = "Aún no ha añadido ningún candidato";
				
				request.getSession().setAttribute("candidates", candidates);
				
				ps = connect.getConnetion().prepareStatement("SELECT DISTINCT curse FROM candidates");
				result = ps.executeQuery();
				
				String curses = "";
				
				if(result.isBeforeFirst()){
					while(result.next()){
						curses += 	"<option value='"+result.getString("curse")+"'>"+
										result.getString("curse")+
								  	"</option>";
					}
				}
				request.getSession().setAttribute("curses", curses);
				
				String redirect = "0;URL=";
				
				switch (submit) {
				case 0:
					String first_name = request.getParameter("first_name");
					String last_name = request.getParameter("last_name");
					String category = request.getParameter("category");
					
					statement = "candidates::"+first_name+":"+last_name+":0:"+category+":"+curse;
					query = new MyQuery(connect, statement);
					ps = query.preparedStatement(MyQuery.INSERT);
					ps.executeUpdate();
					
					redirect += "index.jsp";
					break;
				case 1:
					redirect += "select.jsp";
					break;
				case 2:
					String id = request.getParameter("id");
					
					String[] parameters = {
							"first_name",
							"last_name",
							"category"
					};
					String[] values = {
							request.getParameter("first_name"),
							request.getParameter("last_name"),
							request.getParameter("category")
					};
					
					for (int i = 0; i < parameters.length; i++) {
						statement = "candidates:"+parameters[i]+":"+values[i]+":id:"+id;
						query = new MyQuery(connect, statement);
						ps = query.preparedStatement(MyQuery.MODIFY);
						ps.executeUpdate();
					}
					redirect += "Controller?submit=1";
					break;
				case 3:
					String delete = request.getParameter("id");
					
					statement = "candidates:id:"+delete;
					query = new MyQuery(connect, statement);
					PreparedStatement ps2 = query.preparedStatement(MyQuery.DELETE);
					ps2.executeUpdate();
					
					redirect += "Controller?submit=1";
					break;
				case 4:
					statement = "candidates:curse:"+request.getParameter("remove_curse");
					query = new MyQuery(connect, statement);
					ps2 = query.preparedStatement(MyQuery.DELETE);
					ps2.executeUpdate();
					
					redirect += "Controller?submit=1";
					break;
				default:
					error(request, response, "Todo bien, todo correcto");
					break;
				}
				response.setHeader("refresh", redirect);
			}
			else
				error(request, response, "ERROR AL CONECTAR A LA BASE DE DATOS");
		} catch (Exception e) {
			error(request, response, e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void error(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException {
		
		request.getSession().setAttribute("error", error);
		response.setHeader("refresh", "0;URL=error.jsp");
	}

}
