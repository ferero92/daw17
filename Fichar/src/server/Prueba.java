package server;

public class Prueba {
	
	public static void main(String[] args) {
		
		User user = new User(1, "p3p3", "123", "sdf", 0);
		int state = user.getState();
		
		System.out.println(state);
	}
	
}
