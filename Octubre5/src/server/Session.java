package server;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Session")
public class Session extends HttpServlet {
	
	String[] users = {"admin", "usu1", "usu2"};
	
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
		
		String submit = request.getParameter("submit");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		if(submit.equals("Entrar")){
			
			for (int i = 0; i < users.length; i++) {
				session.setAttribute(users[i], users[i]);
			}
			Enumeration<?> enumeration = session.getAttributeNames(); 
			
			boolean user = false;
			
			while (enumeration.hasMoreElements()) {
				String seName = (String) enumeration.nextElement();
				String seValue = (String) session.getAttribute(seName);
				if(seName.equals(name) && seValue.equals(password))
					user = true;
			}
			if(!user)
				response.setHeader("Refresh", "0;URL=signup.html");
			else
				response.setHeader("Refresh", "0;URL=home.html");
		}
		else{
			if(submit.equals("Registrar")){
				session.setAttribute(name, password);
				
				response.setHeader("Refresh", "0;URL=index.html");
			}
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
