package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Distribution;


public class StatDao extends DaoConnection {
	
	private static StatDao SINGLETON = null;
	
	
	private StatDao() {
		
	}
	
	public static StatDao getInstance() {
		if (SINGLETON==null) 
			SINGLETON = new StatDao();
			
		if (SINGLETON.c==null) {
			SINGLETON.openConnection();	
		}
		return SINGLETON;
	}
	
	

	
	
	public ArrayList<Distribution> allByCountry(){
		ArrayList<Distribution> result = new ArrayList<Distribution>();
		
		try {
			
		PreparedStatement p;
		p = this.c.prepareStatement("SELECT Pays as Country, COUNT(*) as visit from textes where 1 group by Country");
		
		ResultSet rs = p.executeQuery();	
			while(rs.next()) {
				Distribution obj = new Distribution();
				obj.setValue(rs.getString(1));
				if (obj.getValue()=="") obj.setValue("Unknown Country");
				obj.setVisit(Integer.parseInt(rs.getString(2)));
				result.add(obj);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Distribution> allByBrowser(){
		ArrayList<Distribution> result = new ArrayList<Distribution>();
		
		try {
			
		PreparedStatement p;
		p = this.c.prepareStatement("SELECT browser,COUNT(*) as visit from textes where 1 group by browser");
		
		ResultSet rs = p.executeQuery();	
			while(rs.next()) {
				Distribution obj = new Distribution();
				obj.setValue(rs.getString(1));
				if (obj.getValue()=="") obj.setValue("Unknown Browser");
				obj.setVisit(Integer.parseInt(rs.getString(2)));
				result.add(obj);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public  ArrayList<Distribution> allByDay(){
		ArrayList<Distribution> result = new ArrayList<Distribution>();
		try {
			
		PreparedStatement p;
		p = this.c.prepareStatement("SELECT dayname(date) as day,count(*) as visit FROM `textes` WHERE 1 group by dayofweek(date)");
		
		ResultSet rs = p.executeQuery();	
			while(rs.next()) {
				Distribution obj = new Distribution();
				obj.setValue(rs.getString(1));
				obj.setVisit(Integer.parseInt(rs.getString(2)));
				result.add(obj);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
