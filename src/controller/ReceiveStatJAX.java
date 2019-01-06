package controller;

import java.util.ArrayList;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.RecordDao;
import dao.StatDao;
import model.Distribution;
import model.Record;

@Path("/getStat/{type}")
public class ReceiveStatJAX {
	
	final Gson gson = new GsonBuilder().create();

	
	@GET
	@Produces("application/json")
	public String getStat( @PathParam("type") String type )  {
		ArrayList<Distribution> obj = new ArrayList<Distribution>();
		
		switch(type) {
			case "browser":
				obj = StatDao.getInstance().allByBrowser();
				break;
			case "country":
				obj = StatDao.getInstance().allByCountry();
				break;	
			case "day":
				obj = StatDao.getInstance().allByDay();
				break;
			default:
				return "";
				
		}
		
		Gson gson = new Gson();
		String freqJson = gson.toJson(obj);
	    
		System.out.println(freqJson);
		return freqJson;
	}
	
/*	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public String receiveStat(String texte)  {
				
		System.out.println(texte);
			
		Record record = gson.fromJson(texte,Record.class);
		
		try( RecordDao recordDao = RecordDao.getInstance()){
			 RecordDao.getInstance().save(record);				
			}
		
		return record.getDate()+" "+record.getIp()+" "+record.getPays()+" "+record.getBrowser();
	}
*/
}
