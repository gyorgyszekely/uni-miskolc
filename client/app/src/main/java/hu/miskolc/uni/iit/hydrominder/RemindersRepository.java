package hu.miskolc.uni.iit.hydrominder;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import hu.miskolc.uni.iit.hydrominder.Drink.Reminder;

/**
 * Emlékeztetők tárolása
 * Created by Pozsgai Sándor on 2016. 04. 03..
 */
public class RemindersRepository {
    public static final String REMINDER_TIMES = "ReminderTimes";

    /**
     * Emlékezetető mentése
     * @param activity
     * @param title
     * @param time
     */
    public static void SaveReminder(Activity activity, String title, Calendar time) {
        SharedPreferences times = activity.getSharedPreferences(REMINDER_TIMES, Context.MODE_APPEND);
        SharedPreferences.Editor editor = times.edit();
        editor.putLong(title, time.getTimeInMillis());
        editor.commit();
    }

    /**
     * Visszaadja az összes emlékeztetőt
     * @param activity
     * @return
     */
    public static ArrayList<Reminder> GetReminders(Activity activity) {
        SharedPreferences times = activity.getSharedPreferences(REMINDER_TIMES, Context.MODE_PRIVATE);
        Map<String, Long> reminders = (Map<String, Long>) times.getAll();
        ArrayList<Reminder> drinkTimes = new ArrayList<>();
        Calendar c;
        //Reminder típusú lista létrehozása
        for (Map.Entry<String, Long> entry: reminders.entrySet()) {
            c = Calendar.getInstance();
            c.setTimeInMillis(entry.getValue());
            drinkTimes.add(new Reminder(entry.getKey(), c));
        }
        return drinkTimes;
    }

    /**
     * Törli az összes tárolt emlékeztetőt
     * @param activity
     */
    public static void ClearReminders(Activity activity){
        SharedPreferences.Editor editor = activity.getSharedPreferences(REMINDER_TIMES, Context.MODE_PRIVATE).edit();
        editor.clear().commit();
    }
}