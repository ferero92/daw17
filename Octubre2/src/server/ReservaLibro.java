package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReservaLibro
 */
@WebServlet("/ReservaLibro")
public class ReservaLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservaLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		
		String partner = request.getParameter("partner");
		String subject = request.getParameter("subject");
		String[] gender = request.getParameterValues("gender");
		String obs = request.getParameter("obs");
		
		out.println("<p>Nombre: " + name + "</p>");
		out.println("Socio? " + partner + "</p>");
		out.println("Materia: " + subject + "</p>");
		if(gender != null){
			String string = new String();
			for (int i = 0; i < gender.length; i++) {
				string = gender[i] + " ";				
			}
			out.println("<p>Genero: " + string + "</p>");
		}
		if(obs != null)
			out.println("Observaciones: " + obs + "</p>");

		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}