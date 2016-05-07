package hu.miskolc.uni.iit.hydrominder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hu.miskolc.uni.iit.hydrominder.Drink.Reminder;

class ReminderAdapter extends ArrayAdapter<Reminder> {
    private Activity activity;
    private ArrayList<Reminder> reminders;
    private static LayoutInflater inflater = null;

    public ReminderAdapter(Activity activity, int textViewResourceId, ArrayList<Reminder> _reminders) {
        super(activity, textViewResourceId, _reminders);
        this.activity = activity;
        this.reminders = _reminders;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return reminders.size();
    }

    public static class ViewHolder {
        public TextView reminderTitle;
        public TextView reminderTime;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        if (convertView == null) {
            vi = inflater.inflate(R.layout.reminder_list_item, null);
            holder = new ViewHolder();

            holder.reminderTitle = (TextView) vi.findViewById(R.id.reminderTitle);
            holder.reminderTime = (TextView) vi.findViewById(R.id.reminderTime);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }
        holder.reminderTitle.setText(reminders.get(position).getReminderTitle());
        holder.reminderTime.setText(reminders.get(position).getTimeString());
        return vi;
    }
}

/**
 * Ez az adott activity, ami kiirja, hogy a felhasznalo milyen idopontokat valasztott
 * az ivasra
 */

public class ReminderTimeList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list_view);

        ArrayList<Reminder> reminders = RemindersRepository.GetReminders(ReminderTimeList.this);
        //String[] items = {"Első", "Második", "Harmadik"};

        ListView listView = (ListView) findViewById(R.id.remindersList);
        TextView emptyText = (TextView)findViewById(R.id.emptyReminderList);
        listView.setEmptyView(emptyText);
        listView.setAdapter(new ReminderAdapter(ReminderTimeList.this, 0, reminders));
    }


    /**
     * menu importalasa, mivel alapbol nem volt
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drinktimelist, menu);
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
            Intent intent = new Intent();
            intent.setClassName("hu.miskolc.uni.iit.hydrominder", "hu.miskolc.uni.iit.hydrominder.SettingsActivity");
            startActivity(intent);
            return true;
        }

        /**
         * Drinklistbe valo atlepes
         */
        if (id == R.id.action_drinklist) {
            Intent intent = new Intent();
            intent.setClassName("hu.miskolc.uni.iit.hydrominder", "hu.miskolc.uni.iit.hydrominder.ReminderTimeList");
            startActivity(intent);
            return true;
        }

        /**
         * Foablakban valo atlepes
         */
        if (id == R.id.action_itemMain) {
            Intent intent = new Intent();
            intent.setClassName("hu.miskolc.uni.iit.hydrominder", "hu.miskolc.uni.iit.hydrominder.MainActivity");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*@Override
    protected void onStart() {
        super.onStart();

        try {
            FileInputStream fis = openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.userData = (UserData) ois.readObject();
        } catch (Exception e) {
            this.userData = InnerData.getUserData();
        }

        if (this.userData == null) {
            this.userStat.setText("Nem talalhato adat!");
        } else {
            List<Reminder> list = this.userData.getDrinks();
            for (int i = 0; i < 10; i++) {
                if (i < list.size()) {
                    switch (i) {
                        case 0:
                            this.drink0.setText(list.get(i).getNormalDateTime());
                            break;
                        case 1:
                            this.drink1.setText(list.get(i).getNormalDateTime());
                            break;
                        case 2:
                            this.drink2.setText(list.get(i).getNormalDateTime());
                            break;
                        case 3:
                            this.drink3.setText(list.get(i).getNormalDateTime());
                            break;
                        case 4:
                            this.drink4.setText(list.get(i).getNormalDateTime());
                            break;
                        case 5:
                            this.drink5.setText(list.get(i).getNormalDateTime());
                            break;
                        case 6:
                            this.drink6.setText(list.get(i).getNormalDateTime());
                            break;
                        case 7:
                            this.drink7.setText(list.get(i).getNormalDateTime());
                            break;
                        case 8:
                            this.drink8.setText(list.get(i).getNormalDateTime());
                            break;
                        case 9:
                            this.drink9.setText(list.get(i).getNormalDateTime());
                            break;
                    }
                } else {
                    switch (i) {
                        case 0:
                            this.drink0.setText("");
                            break;
                        case 1:
                            this.drink1.setText("");
                            break;
                        case 2:
                            this.drink2.setText("");
                            break;
                        case 3:
                            this.drink3.setText("");
                            break;
                        case 4:
                            this.drink4.setText("");
                            break;
                        case 5:
                            this.drink5.setText("");
                            break;
                        case 6:
                            this.drink6.setText("");
                            break;
                        case 7:
                            this.drink7.setText("");
                            break;
                        case 8:
                            this.drink8.setText("");
                            break;
                        case 9:
                            this.drink9.setText("");
                            break;
                    }
                }
            }
        }
    }*/
}
