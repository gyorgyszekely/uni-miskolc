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
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import hu.miskolc.uni.iit.hydrominder.Drink.DrinkTime;
import hu.miskolc.uni.iit.hydrominder.Drink.InnerData;
import hu.miskolc.uni.iit.hydrominder.Drink.UserData;

/**
 * Ez az adott activity, ami kiirja, hogy a felhasznalo milyen idopontokat valasztott
 * az ivashoz azon kivul, hogy bellithatja, hogy milyen idokozonkent legyenek ezek az adott idopontok
 * amikor az adott rendszer figyelmezteti, hogy igyon.
 */

public class DrinkTimeList extends AppCompatActivity {

    private String fileName = "localUser";

    private UserData userData;

    private TextView userStat;

    private TextView drink0;
    private TextView drink1;
    private TextView drink2;
    private TextView drink3;
    private TextView drink4;
    private TextView drink5;
    private TextView drink6;
    private TextView drink7;
    private TextView drink8;
    private TextView drink9;

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

        this.userStat = (TextView) findViewById(R.id.userStat);
        this.drink0 = (TextView) findViewById(R.id.txtDrink0);
        this.drink1 = (TextView) findViewById(R.id.txtDrink1);
        this.drink2 = (TextView) findViewById(R.id.txtDrink2);
        this.drink3 = (TextView) findViewById(R.id.txtDrink3);
        this.drink4 = (TextView) findViewById(R.id.txtDrink4);
        this.drink5 = (TextView) findViewById(R.id.txtDrink5);
        this.drink6 = (TextView) findViewById(R.id.txtDrink6);
        this.drink7 = (TextView) findViewById(R.id.txtDrink7);
        this.drink8 = (TextView) findViewById(R.id.txtDrink8);
        this.drink9 = (TextView) findViewById(R.id.txtDrink9);
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

    @Override
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
            List<DrinkTime> list = this.userData.getDrinks();
            for(int i= 0; i < 10; i++) {
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
    }
}
