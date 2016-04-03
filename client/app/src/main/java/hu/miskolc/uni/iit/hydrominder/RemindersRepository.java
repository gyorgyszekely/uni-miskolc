package hu.miskolc.uni.iit.hydrominder;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.Calendar;

/**
 * Created by Pozsgai Sándor on 2016. 04. 03..
 */
public class RemindersRepository {
    public static final String REMINDER_TIMES = "ReminderTimes";

    public static void SaveReminder(Activity activity, String title, Calendar time){
        SharedPreferences times = activity.getSharedPreferences(REMINDER_TIMES, 0);
        SharedPreferences.Editor editor = times.edit();
        editor.putLong(title, time.getTimeInMillis());
        editor.commit();
    }

    //TODO
    public static void GetReminders(){
        throw new UnsupportedOperationException();
    }

}
