package com.jokar.movie_networking;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new checkConnectionStatus().execute("https://api.themoviedb.org/3/movie/550?api_key=062eab0d14418691ba911d6c731dde4e");


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

                // code starts here


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

            try {
                //TODO : parse object
                JSONObject jsonObject = new JSONObject(s);
                //   mTextView.setText(jsonObject.getString("title"));

               //TODO : parse Array
                Map<Integer,String> companiesMaps = new HashMap<>();
                JSONArray jsonArray = jsonObject.getJSONArray("production_companies");
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);
                    companiesMaps.put( object.getInt("id"),object.getString("name"));
                    mTextView.setText(companiesMaps.get(711));


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }


}
