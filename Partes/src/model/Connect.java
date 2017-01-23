package model;

import mylib.db.MyConnect;

public class Connect extends MyConnect{
	
	public Connect(){
		
		super("//127.0.0.1:3306/PARTES");
	}
	
	public Connect(String db){
		
		super(db);
	}
}
