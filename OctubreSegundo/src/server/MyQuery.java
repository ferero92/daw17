package server;

import java.sql.PreparedStatement;

public class MyQuery {
	
	public static final int INSERT = 1;
	public static final int SELECT_ALL = 2;
	public static final int SELECT_WHERE = 3;
	public static final int DELETE = 4;
	public static final int MODIFY = 5;
	
	private MyConnect connect;
	private String[] query;

	public MyQuery(MyConnect connect, String query){
		
		this.connect = connect;
		this.query = query.split(":");
	}
	
	public PreparedStatement preparedStatement(int type) throws Exception{
		
		switch (type) {
		case 1:
			return this.insert();
		case 2:
			return this.selectAll();
		case 3:
			return this.selectWhere();
		case 4:
			return this.delete();
		case 5:
			return this.modify();
		default:
			return null;
		}
	}
	
	private PreparedStatement insert() throws Exception{
		
		String string =  "INSERT INTO " + this.query[0] + " VALUES(null,?,?,?,?,?)";
		
		PreparedStatement ps = this.connect.getConnetion().prepareStatement(string);
		for (int i = 1; i < query.length; i++) {
			ps.setString(i, this.query[i]);
		}
		return ps;
	}
	
	private PreparedStatement selectAll() throws Exception{
		
		String string = "SELECT * FROM " + this.query[0] + " ORDER BY " + this.query[1];
		
		return this.connect.getConnetion().prepareStatement(string);
	}
	
	private PreparedStatement selectWhere() throws Exception{
		
		String string = "SELECT * FROM " + this.query[0] + " WHERE " + this.query[1] + " = ?";
		
		if(this.query.length > 3){
			for (int i = 3; i < query.length; i+=2) {
				string += " OR " + this.query[i] + " = ?";
			}
		}
		PreparedStatement ps = this.connect.getConnetion().prepareStatement(string);
		for (int i = 1, j = 2; j < query.length; i++, j+=2) {
			ps.setString(i, this.query[j]);
		}
		return ps;
	}
	
	private PreparedStatement delete() throws Exception{
		
		String string = "DELETE FROM " + this.query[0] + " WHERE " + this.query[1] + " = ?";
		
		PreparedStatement ps = this.connect.getConnetion().prepareStatement(string);
		ps.setString(1, this.query[2]);
		
		return ps;
	}
	
	private PreparedStatement modify() throws Exception{
		
		String string = "UPDATE " + this.query[0] + " SET " + this.query[1] + " = ? WHERE " + this.query[3] + " = ?";
		
		PreparedStatement ps = this.connect.getConnetion().prepareStatement(string);
		for (int i = 1, j = 2; j < query.length; i++, j+=2) {
			ps.setString(i, this.query[j]);
		}
		return ps;
	}
	
	public String[] getQuery(){
		return this.query;
	}
}
