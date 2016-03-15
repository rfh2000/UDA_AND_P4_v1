package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.JokeProvider;
import com.example.android.libandroidjokedisplay.JokeActivity;

public class MainActivity extends ActionBarActivity {

    private static final String LOG_TAG = "MainActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Ritchie"));
        //new JokeEndpointsAsyncTask().execute();
        //Log.v(LOG_TAG, "Executing JokeAsyncTask");
        //new JokeEndpointsAsyncTask().execute(this);

//        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
//        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
//        //textView.setText("Your Device IP Address: " + ipAddress);
//        Log.v(LOG_TAG, "Your Device IP Address: " + ipAddress);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void tellJoke(View view){
//        JokeProvider jokeProvider = new JokeProvider();
//        String jokeString = jokeProvider.getJoke();
//        //Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, jokeString, Toast.LENGTH_SHORT).show();
//    }

    public void launchAndroidLibraryActivity(View view){
//        JokeProvider jokeProvider = new JokeProvider();
//        String jokeString = jokeProvider.getJoke();
//        Intent intent = new Intent(this, JokeActivity.class);
//        intent.putExtra("JOKE", jokeString);
//        startActivity(intent);
        new JokeEndpointsAsyncTask().execute(this);
    }

}
