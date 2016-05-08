package hu.miskolc.uni.iit.hydrominder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.miskolc.uni.iit.hydrominder.Drink.InnerData;

/**
 * Beállítások activity
 */
public class SettingsActivity extends AppCompatActivity {

    private Switch unitChoser;
    @Bind(R.id.btn_clearReminders) Button btn_clearReminders;
    @Bind(R.id.swNotificationOnOff) Switch swNotificationOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.unitChoser = (Switch) findViewById(R.id.swUnitChooser);
        this.unitChoser.setChecked(InnerData.getMetrics());

        //Összes emlékeztető törlése gomb
        btn_clearReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemindersRepository.ClearReminders(SettingsActivity.this);
                Toast.makeText(getBaseContext(), "Időpontok törölve", Toast.LENGTH_LONG).show();
            }
        });

        //Értesítése ki(be)kapcsolása
        swNotificationOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReminderNotificationManager.CancelReminders(SettingsActivity.this);
                Toast.makeText(getBaseContext(), "Értesítések kikapcsolva", Toast.LENGTH_LONG).show();
            }
        });
    }
}
