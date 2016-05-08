package hu.miskolc.uni.iit.hydrominder.Drink;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Emlékeztető adatait tároló osztály
 * Created by spozsgai on 2016.05.07..
 */
public class Reminder {

    /**
     * Emlékeztető neve
     */
    private String reminderTitle;

    /**
     * Emlékeztető ideje
     */
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

    /**
     * Az emlékezetető beállított idejét adja vissza
     * @return String, HH:mm formátumban
     */
    public String getTimeString(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(this.time.getTime());
    }
}
