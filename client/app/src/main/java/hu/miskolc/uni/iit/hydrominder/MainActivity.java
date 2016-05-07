package hu.miskolc.uni.iit.hydrominder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import hu.miskolc.uni.iit.hydrominder.Drink.DrinkTime;
import hu.miskolc.uni.iit.hydrominder.Drink.InnerData;
import hu.miskolc.uni.iit.hydrominder.Drink.UserData;

/**
 * Az adott foablak az alkalmazasban
 *
 * Feladata, hogy informaciot biztositson a legfontosabb dolgokrol.
 * Ilyen dolog lehet peldaul a kovetkezo ivas idopontja,
 * az eddig bevitt folyadekmennyiseg az adott nap �s
 * az adott folyad�kbevitel mennyis�ge.
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private static boolean isInic = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private TextView txtFreq;

    private TextView nextDrink;

    private UserData userData;

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


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        this.nextDrink = (TextView) findViewById(R.id.NextDrink);
        this.txtFreq = (TextView) findViewById(R.id.txtFreq);
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

    private UserData setTemporaryUser() {
        List<DrinkTime> l = new LinkedList<DrinkTime>();
        l.add(new DrinkTime(2016,4,25,07,30,0));
        l.add(new DrinkTime(2016,4,25,8,30,0));
        l.add(new DrinkTime(2016,4,25,9,30,0));
        l.add(new DrinkTime(2016,4,25,10,30,0));
        l.add(new DrinkTime(2016,4,25,12,30,0));
        l.add(new DrinkTime(2016,4,25,15,30,0));
        l.add(new DrinkTime(2016,4,25,18,30,0));
        l.add(new DrinkTime(2016,4,25,20,30,0));
        DrinkTime dt = new DrinkTime(2016,4,25,16,30,0);
        if (!(dt.setDateWithFormat("2015-04-14T12:12:13"))) {
            throw new RuntimeException();
        }
        return new UserData("defaultBéla",l,"fastDrink");
    }

    private String fileName = "localUser";

    private void inicializeInnerStorage() {
        /*
        File dir = getFilesDir();
        File[] files = dir.listFiles();
        boolean iscreated = false;
        for (File i : files) {
            if(i.getName().equalsIgnoreCase("localUser")) {
                iscreated = true;
            }
        }

        if (!iscreated) {
            UserData ud = setTemporaryUser();
            this.userData = ud;
            try {
                FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(ud);
            } catch (java.io.IOException e) {

            }
        } else {
            try {
                FileInputStream fis = openFileInput(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.userData = (UserData) ois.readObject();
            } catch (Exception e) {

            }
        }
        */
        if (isInic == false) {
            UserData ud = setTemporaryUser();
            this.userData = ud;
            InnerData.setData(ud);
            InnerData.setMetrics(true);
            try {
                FileOutputStream fos = openFileOutput(fileName, Context.MODE_APPEND);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(ud);
                isInic = true;
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://hu.miskolc.uni.iit.hydrominder/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
        inicializeInnerStorage();
        if (this.userData == null) {
            this.txtFreq.setText("?");
            this.nextDrink.setText("????");
        } else {
            this.txtFreq.setText(String.valueOf(this.userData.getDrinkFrequency()));
            this.nextDrink.setText(this.userData.getNewestDrink().calculateNextDrinkTime(userData.getDrinkFrequency(), true));
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://hu.miskolc.uni.iit.hydrominder/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
