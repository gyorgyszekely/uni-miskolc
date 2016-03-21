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

/**
 * Ez az adott activity, ami kiirja, hogy a felhasznalo milyen idopontokat valasztott
 * az ivashoz azon kivul, hogy bellithatja, hogy milyen idokozonkent legyenek ezek az adott idopontok
 * amikor az adott rendszer figyelmezteti, hogy igyon.
 */

public class DrinkTimeList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_time_list);
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
    }




    /**
     * menu importalasa, mivel alapbol nem volt
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
}
