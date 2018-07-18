package com.jokar.movie_networking;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new checkConnectionStatus().execute("http://www.androidinhindi.com/logindemo/login.php");


    }

    class checkConnectionStatus extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {


            URL url = null;
            try {
                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);

                // code starts here

                //TODO : builder for getting the input
                Uri.Builder mBuilder = new Uri.Builder()
                        .appendQueryParameter("username", "admin")
                        .appendQueryParameter("password", "admin");

                //TODO : create OutputStream and BufferWriter
                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream),8);
                bufferedWriter.write(mBuilder.build().getEncodedQuery());
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                urlConnection.connect();

                //TODO : create InputStream and BufferReader
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String s = bufferedReader.readLine();
                bufferedReader.close();

                return s;


            } catch (IOException e) {
                Log.d("error", e.getMessage(), e);
                e.printStackTrace();

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mTextView = findViewById(R.id.Text);
            mTextView.setText(s);
        }


    }


}
