package model;

import java.text.SimpleDateFormat;

public class Curse {
	
	private String curse;
	
	public Curse(){
		
		SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
		String date = format.format(new java.util.Date());
		
		int month = Integer.parseInt(date.split("/")[0]);
		int year = Integer.parseInt(date.split("/")[1]);
		
		if(month < 9)
			this.curse = (year -1) + "-" + year;
		else
			this.curse = year + "-" + (year +1);
		
	}
	
	public String getCurse(){
		return this.curse;
	}
	
	public void setCurse(String curse){
		this.curse = curse;
	}
}
