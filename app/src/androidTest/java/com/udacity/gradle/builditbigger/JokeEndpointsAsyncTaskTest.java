package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.TimeUnit;

/**
 * Created by lecoq on 16/03/2016.
 */
public class JokeEndpointsAsyncTaskTest extends AndroidTestCase {

    JokeEndpointsAsyncTask mJokeTask;
    String mJoke;

    public void testJokeEndpointsAsyncTask() {

        try {
            mJokeTask = new JokeEndpointsAsyncTask();
            mJokeTask.execute(getContext());
            mJoke = mJokeTask.get(30, TimeUnit.SECONDS);
            assertNotNull(mJoke);
            //assertNull(joke);
        } catch (Exception e){
            fail("Timed out");
        }

    }
}
