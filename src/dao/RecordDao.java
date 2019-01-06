package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Record;
import model.Texte;

public class RecordDao extends DaoConnection {
	
	private static RecordDao SINGLETON = null;
	
	
	private RecordDao() {
		
	}
	
	public static RecordDao getInstance() {
		if (SINGLETON==null) 
			SINGLETON = new RecordDao();
			
		if (SINGLETON.c==null) {
			SINGLETON.openConnection();	
		}
		return SINGLETON;
	}
	
	public List<Texte> all(){
		List<Texte> result = new ArrayList<Texte>();
		try {
			
		PreparedStatement p;
		p = this.c.prepareStatement("SELECT * FROM textes");
		
		ResultSet rs = p.executeQuery();
		
			while(rs.next()) {
				Texte t = new Texte();
				t.setId(rs.getString(1));
				t.setText(rs.getString(2));
				result.add(t);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public void save(Record record) {
		SimpleDateFormat formater = null;
		try {
			PreparedStatement p = this.c.prepareStatement("INSERT INTO textes (id,date,ip,pays,browser) VALUES (?,?,?,?,?)");
			p.setString(1,""); 
			Date now = new Date();
			formater = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			
			p.setString(2,formater.format(now)); 	//date
			p.setString(3,record.getIp());			//ip
			p.setString(4,record.getPays());		//pays
			p.setString(5,record.getBrowser());		//browser
			
		
			p.executeUpdate();
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	

	
	
	
	
	
	

}
