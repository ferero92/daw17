package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EsPrimo
 */
@WebServlet("/EsPrimo")
public class EsPrimo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EsPrimo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String string = new String();
		String input = request.getParameter("number");
		
		try {
			int n = Integer.parseInt(input);

			if (isPrime(n))
				string = "El número " + " es primo";

			else 
				string = "El número " + " no es primo";
		}
		catch (NumberFormatException e) {
			string = "Por favor introduce un número";
			response.setHeader("Refresh", "2;URL=index.html");
		}
		out.println(string);
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean isPrime(int n) {
		
		for(int i = 2; i < n; i++){
			if(n % i == 0)
				return false;
		}
		return true;
	}

}