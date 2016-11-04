package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	
	private static ArrayList<String> messages;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		boolean check = false;
		
		if(cookies != null){
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("usuario"))
					check = true;
			}
		}
		if(!check)
			response.setHeader("refresh", "0;login.html");
		
		else{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			String string = new String();
			
			if(messages != null){
				if(messages.size() > 15)
					messages.remove(0);
				
				for (int i = 0; i < messages.size(); i++) {
					string += messages.get(i) + "<br/>";
				}
			}
			out.println(string);
			response.setHeader("refresh", "1;Update");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static ArrayList<String> getMessages(){
		return messages;
	}
	
	public static void setMessages(ArrayList<String> array){
		messages = array;
	}

}
