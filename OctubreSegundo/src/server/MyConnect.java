package server;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnect{
	
	private Connection connection;
	private boolean isConnect;
	
	public MyConnect(String db){
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection("jdbc:mysql:"+db, "root", "");
			this.isConnect = true;
			
		}catch (Exception e) {
			this.isConnect = false;
		}
	}
	
	public Connection getConnetion(){
		return this.connection;
	}
	
	public boolean isConnect(){
		return this.isConnect;
	}

}
