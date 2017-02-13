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
 * Servlet implementation class Votes
 */
@WebServlet("/Votes")
public class Votes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Votes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter("category");
		String candidates = request.getParameter("candidates");
		String clean = request.getParameter("clean");
		
		try {
			Connect connect = new Connect();
			
			if(connect.isConnect()){
				
				String statement;
				MyQuery query;
				PreparedStatement ps;
				ResultSet result;
				
				if(candidates != null){
					String[] array = candidates.split(",");
					for (int i = 0; i < array.length; i++) {
						ps = connect.getConnetion().prepareStatement(
									"UPDATE candidates SET votes = votes +1 WHERE id = "+array[i]
								);
						ps.executeUpdate();
					}
				}
				if(clean != null){
					statement = "candidates:votes:0:category:"+category;
					query = new MyQuery(connect, statement);
					ps = query.preparedStatement(MyQuery.MODIFY);
					ps.executeUpdate();
				}
				if(category != null){
					statement = "candidates:category:"+category;
					query = new MyQuery(connect, statement);
					ps = query.preparedStatement(MyQuery.SELECT_AND);
					result = ps.executeQuery();
					
					int total = resultVotation(connect, category);
					
					String votation = "";
					String count = "";
					
					if(result.isBeforeFirst()){
						votation += "<div class='alert alert-warning col-md-4 pull-right'></div>"+
									"<form class='col-md-4 col-md-offset-4'>"+
									"<div class='print'>";
						
						while(result.next()){
							votation += "<div class='form-group checkbox has-success'>\n"+
											"<input class='form-control' type='checkbox' data-id='"+result.getString("id")+"'"+
											" data-toggle='checkbox-x' data-three-state='false'"+
											" data-size='xl'>\n"+
											"<label>"+
												result.getString("first_name")+" "+result.getString("last_name")+
											"</label>\n"+
										"</div>\n";
							count += 	"<tr>"+
											"<td>"+result.getString("first_name")+" "+result.getString("last_name")+"</td>"+
											"<td>"+result.getString("votes")+"</td>"+
											"<td><div class='percentage'>"+
												percentage(result.getInt("votes"), total)+
											"%</div></td>"+
										"</tr>";
						}
						votation +=		"</div>"+
										"<button class='btn btn-success' type='button'"+
										" data-toggle='modal' data-target='#confirm'>Votar</button>"+
									"</form>";
					}
					else
						votation = "Aún no hay candidatos para esta categoría";
					
					request.getSession().setAttribute("votation", votation);
					request.getSession().setAttribute("count", count);
				}
				request.getSession().setAttribute("category", category);
				String redirect = "0;URL=";
				if(request.getParameter("type").equals("0"))
					redirect += "vote.jsp";
				else
					redirect += "count.jsp";
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
	
	protected int resultVotation(Connect connect, String category) throws Exception {
		
		PreparedStatement ps = connect.getConnetion().prepareStatement(
					"SELECT SUM(votes) as total FROM candidates WHERE category = "+category
				);
		ResultSet result = ps.executeQuery();
		result.next();
		
		return result.getInt("total");
	}
	
	protected double percentage(int votes, int total) {

		return Math.round(((double) votes * 100 / (double) total) * 100.0) / 100.0;
	}

}
