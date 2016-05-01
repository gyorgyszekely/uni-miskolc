package hu.miskolc.uni.iit.hydrominder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import hu.miskolc.uni.iit.hydrominder.Drink.InnerData;

public class Settings extends AppCompatActivity {

    private Switch unitChoser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        this.unitChoser = (Switch) findViewById(R.id.swUnitChooser);
        this.unitChoser.setChecked(InnerData.getMetrics());
    }

    /**
     * menu betoltese a settingsbe, mivel alapbol nem volt
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
            intent.setClassName("hu.miskolc.uni.iit.hydrominder", "hu.miskolc.uni.iit.hydrominder.Settings");
            startActivity(intent);
            return true;
        }

        /**
         * Drinklistbe valo atlepes
         */
        if (id == R.id.action_drinklist) {
            Intent intent = new Intent();
            intent.setClassName("hu.miskolc.uni.iit.hydrominder", "hu.miskolc.uni.iit.hydrominder.DrinkTimeList");
            startActivity(intent);
            return true;
        }

        /**
         * Foablakba valo atlepes
         */
        if (id == R.id.action_itemMain) {
            Intent intent = new Intent();
            intent.setClassName("hu.miskolc.uni.iit.hydrominder", "hu.miskolc.uni.iit.hydrominder.MainActivity");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
