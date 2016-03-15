package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.example.android.libandroidjokedisplay.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


/**
 * Created by lecoq on 14/03/2016.
 */

//class JokeEndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
//class JokeEndpointsAsyncTask extends AsyncTask<Void, Void, String> {
class JokeEndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private static final String LOG_TAG = "LOG___________TAG";

    @Override
    //protected String doInBackground(Pair<Context, String>... params) {
    //protected String doInBackground(Void... voids) {
    //protected String doInBackground(Context c) {
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://stately-gist-125111.appspot.com/_ah/api/"); // This is the project id in Google Developers Console
            myApiService = builder.build();
        }

        context = params[0];
        //context = c;
        //context = params[0].first;
        //String name = params[0].second;

        try {
            //return myApiService.sayHi(name).execute().getData();
            String s = myApiService.getJoke().execute().getData();
            Log.v(LOG_TAG, "The joke is " + s);
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        startJokeActivity(result);
    }

    private void startJokeActivity(String joke){
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra("JOKE", joke);
        context.startActivity(intent);
    }

}