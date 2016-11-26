package hu.miskolc.uni.iit.hydrominder.types.bean;

import java.util.List;

/**
 * User defined profile holder class.
 * 
 * @author gszekely
 *
 */
public class Profile {
	
	private String name;
    private String description;
    private String friendlyName;
    private double drinkFrequency;
    private List<Reminder> reminders;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFriendlyName() {
		return friendlyName;
	}
	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}
	public double getDrinkFrequency() {
		return drinkFrequency;
	}
	public void setDrinkFrequency(double drinkFrequency) {
		this.drinkFrequency = drinkFrequency;
	}
	public List<Reminder> getReminders() {
		return reminders;
	}
	public void setReminders(List<Reminder> reminders) {
		this.reminders = reminders;
	}

}
