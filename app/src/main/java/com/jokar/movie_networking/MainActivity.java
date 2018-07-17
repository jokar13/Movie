package com.jokar.movie_networking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(new Runnable() {
            @Override
            public void run() {


                URL url = null;
                try {
                    url = new URL("http://google.com");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                try {
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    Log.e("Response code", String.valueOf(urlConnection.getResponseCode()));
                } catch (IOException e) {
                    Log.d("error", e.getMessage(), e);
                    e.printStackTrace();
                }


            }
        }).start();

    }
}
