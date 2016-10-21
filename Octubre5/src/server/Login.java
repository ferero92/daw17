package server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	String[] users = {"admin", "usu1", "usu2"};
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		if(submit.equals("Entrar")){
			
			for (int i = 0; i < users.length; i++) {
				Cookie cookie = new Cookie(users[i], users[i]);
				response.addCookie(cookie);
			}
			Cookie[] cookies = request.getCookies();
			
			boolean user = false;
			
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals(name) && cookies[i].getValue().equals(password))
					user = true;
			}
			if(!user)
				response.setHeader("Refresh", "0;URL=signup.html");
			else
				response.setHeader("Refresh", "0;URL=home.html");
		}
		else{
			if(submit.equals("Registrar")){
				
				Cookie cookie = new Cookie(name, password);
				cookie.setMaxAge(50000);
				response.addCookie(cookie);
				
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
