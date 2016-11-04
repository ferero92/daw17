package server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Send
 */
@WebServlet("/Send")
public class Send extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Send() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> array = Update.getMessages();
		String message = new String();
		
		if(array == null)
			array = new ArrayList<String>();
		
		Cookie[] cookies = request.getCookies();
		Cookie usuario = null;
		
		if(cookies != null)
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("usuario"))
					usuario = cookies[i];
			}
		if(usuario != null)
			message += usuario.getValue() + ": ";
		
		message += request.getParameter("message");
		
		array.add(message);
		Update.setMessages(array);
		
		response.setHeader("refresh", "0;URL=general.html");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
