package hu.miskolc.uni.iit.hydrominder.types.bean;

import java.sql.Timestamp;

/**
 * 
 * Concrete reminder timestamp holder.
 * 
 * @author gszekely
 *
 */
public class Reminder {
	
	private Timestamp timets;
	private String title;
	
	public Timestamp getTimets() {
		return timets;
	}
	public void setTimets(Timestamp timets) {
		this.timets = timets;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
