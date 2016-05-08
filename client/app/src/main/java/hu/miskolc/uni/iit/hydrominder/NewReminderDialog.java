package hu.miskolc.uni.iit.hydrominder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import hu.miskolc.uni.iit.hydrominder.Drink.Reminder;

/**
 * Created by sanyi on 2016. 04. 03..
 */
public class NewReminderDialog
{
    private Activity activity;
    private AlertDialog dialog;
    private final View view;
    EditText timeEditor;
    EditText titleEditor;
    private Calendar time;
    private String title;

    public NewReminderDialog(Activity activity) {
        this.activity = activity;
        this.view = activity.getLayoutInflater().inflate(R.layout.new_reminder, null);
        this.titleEditor = (EditText) view.findViewById(R.id.reminderTitleEditText);
        this.timeEditor = (EditText) view.findViewById(R.id.reminderTimeEditText);
    }

    /**
     * Beállított időpont mentése
     */
    class ReminderSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            time = Calendar.getInstance();
            time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            time.set(Calendar.MINUTE, minute);
            time.set(Calendar.SECOND, 0);
            timeEditor.setText(time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE));
        }
    }

    /**
     * Megnyitás
     */
    public void Open() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    /**
                     * OK hatására mentjük a megadott adatokkal az emlékeztetőt és beállítjuk a notification-t is
                     * @param dialog
                     * @param id
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Kötelező nevet és időpontot megadni
                        if(!titleEditor.getText().toString().matches("") && !timeEditor.getText().toString().matches("")) {
                            title = titleEditor.getText().toString();
                            RemindersRepository.SaveReminder(activity, title, time); //Reminder mentése
                            ReminderNotificationManager.CreateNotification(activity, new Reminder(title, time)); //Notification beállítása
                            //Megadott adatok kiírása egy toast üzenetben
                            Toast.makeText(activity.getBaseContext(), (title + ": " + time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE)),
                                    Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(activity.getBaseContext(), "Nincs beállítva név vagy időpont!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    /**
                     * Mégsem esetén kilépünk a dialog-ból
                     * @param dialog
                     * @param id
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.setTitle(R.string.new_reminder);

        timeEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY) + 1; //Aktuális idő + 1 óra
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(activity, new ReminderSetListener(), hour, minute, true);
                dialog.setMessage(activity.getString(R.string.new_reminder));
                dialog.show();
            }
        });
        dialog = builder.create();
        dialog.show();
    }
}
