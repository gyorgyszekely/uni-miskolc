package hu.miskolc.uni.iit.hydrominder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import hu.miskolc.uni.iit.hydrominder.Drink.Reminder;

/**
 * Az adott foablak az alkalmazasban
 * <p/>
 * Feladata, hogy informaciot biztositson a legfontosabb dolgokrol.
 * Ilyen dolog lehet peldaul a kovetkezo ivas idopontja,
 * az eddig bevitt folyadekmennyiseg az adott nap és
 * az adott folyadékbevitel mennyisége.
 */
public class MainActivity extends AppCompatActivity {

    private static boolean isInic = false;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private void RefreshNextReminderTime() {
        boolean IsNextReminderSet = false;
        ArrayList<Reminder> reminders = RemindersRepository.GetReminders(this);
        Calendar now = Calendar.getInstance();
        TextView nextReminder = (TextView) findViewById(R.id.NextReminderTime);
        if (reminders.size() > 0) {
            Calendar c = reminders.get(0).getTime();
            if (c.getTimeInMillis() > now.getTimeInMillis()) IsNextReminderSet = true;

            for (Reminder r : reminders) {
                if (r.getTime().getTimeInMillis() > now.getTimeInMillis() && r.getTime().getTimeInMillis() < c.getTimeInMillis()) {
                    c = r.getTime();
                    IsNextReminderSet = true;
                }
            }

            if (IsNextReminderSet) {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                nextReminder.setText(format.format(c.getTime()));
            }
        } else {
            nextReminder.setText(R.string.list_is_empty);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Új gombra kattintva új időpontot lehet felvenni
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NewReminderDialog(MainActivity.this).Open();
            }
        });

        RefreshNextReminderTime();
    }

    @Override
    public void onResume(){
        super.onResume();
        RefreshNextReminderTime();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /**
         * Menu activity-re valo ugras a settings megnyomasakor
         */
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        /**
         * Drinklistbe valo atlepes
         */
        if (id == R.id.action_drinklist) {
            Intent intent = new Intent(this, ReminderTimeList.class);
            startActivity(intent);
            return true;
        }

        /**
         * Foablakban valo atlepes
         */
        if (id == R.id.action_itemMain) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
