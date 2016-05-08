package hu.miskolc.uni.iit.hydrominder;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

/**
 * Alarm kezelő osztály
 * Created by Pozsgai Sándor on 2016. 05. 07..
 */
public class AlarmReceiver extends BroadcastReceiver {
    //TODO: vhogy át kellene venni a reminder title-t
    private String reminderTitle = "Igyál!";

    /**
     * Alarm konfigurálása
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        //Notification ikon, title, szöveg
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_info_black_24dp)
                        .setContentTitle(context.getResources().getString(R.string.app_name))
                        .setContentText(reminderTitle);

        //A notification-re kattintva ezt hozza be
        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        //Nem lesz egyszerre több notification, a meglévőt update-eli
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        //Rezgés minta 100ms szünet, 1000ms rezgés, 250ms szünet...
        long[] vibratePattern = {100, 1000, 250, 250, 250, 250};
        mBuilder.setVibrate(vibratePattern);
        //Notification hangjelzése
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify((int)System.currentTimeMillis(), mBuilder.build());
    }
}
