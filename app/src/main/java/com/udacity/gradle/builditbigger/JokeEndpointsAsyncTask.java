package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.libandroidjokedisplay.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.lang.ref.WeakReference;


/**
 * Created by lecoq on 14/03/2016.
 */

class JokeEndpointsAsyncTask extends AsyncTask<Context, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private WeakReference<ProgressBar> progressBarWeakReference;

    public JokeEndpointsAsyncTask(){

    }

    public JokeEndpointsAsyncTask(ProgressBar progressBar){
        this.progressBarWeakReference = new WeakReference<ProgressBar>(progressBar);
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://stately-gist-125111.appspot.com/_ah/api/"); // This is the project id in Google Developers Console
            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ProgressBar progressBar = progressBarWeakReference.get();
        if (progressBar != null){
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPostExecute(String result) {
        ProgressBar progressBar = progressBarWeakReference.get();
        if (progressBar != null){
            progressBar.setVisibility(View.GONE);
        }
        startJokeActivity(result);
    }

    private void startJokeActivity(String joke){
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra("JOKE", joke);
        context.startActivity(intent);
    }

}