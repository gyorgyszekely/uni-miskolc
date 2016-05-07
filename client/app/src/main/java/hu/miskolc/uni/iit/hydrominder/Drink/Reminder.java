package hu.miskolc.uni.iit.hydrominder.Drink;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This class is used to hold an actual datepoint of drinking.
 * Created by spozsgai on 2016.05.07..
 */
public class Reminder {

    private String reminderTitle;
    private Calendar time;

    public Reminder(String reminderTitle, Calendar time) {
        this.reminderTitle = reminderTitle;
        this.time = time;
    }

    public String getReminderTitle() {
        return reminderTitle;
    }

    public Calendar getTime() {
        return time;
    }

    public String getTimeString(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(this.time.getTime());
    }
}
