package model;

public class Distribution {

	
	private String value;
	private Integer visit;
	

	
	public Integer getVisit() {
		return visit;
	}
	public void setVisit(Integer visit) {
		this.visit = visit;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

@Override
	public String toString() { 
        return this.value;
     } 



}
