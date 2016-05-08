package hu.miskolc.uni.iit.hydrominder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import hu.miskolc.uni.iit.hydrominder.Drink.Reminder;

/**
 * Az emlékeztetőkhöz tartozó notification-ök kezelésére használt osztály
 * Created by Pozsgai Sándor on 2016. 05. 07..
 */
public class ReminderNotificationManager {

    //Aktív emlékeztetők listája
    private static ArrayList<PendingIntent> activeReminders = new ArrayList<>();

    /**
     * Új notification létrehozása és beállítása
     * @param context
     * @param reminder
     */
    public static void CreateNotification(Context context, Reminder reminder) {
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)System.currentTimeMillis(), alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        //24 órás ismétlődés beállítása
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, reminder.getTime().getTimeInMillis(), 24*60*60*100, pendingIntent);
        //Hozzáadjuk az aktív emlékeztetők listájához
        activeReminders.add(pendingIntent);
    }

    /**
     * Összes notification megszakítása
     * @param context
     */
    public static void CancelReminders(Context context){
        for (PendingIntent pi : activeReminders) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
            alarmManager.cancel(pi);
        }
    }

}
