package it.ghiglienodigital.docandroid.rest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class MainActivity extends Activity {

    private final static String TAG = "rest";
    private final static String ECHO_END_POINT =
            "http://alfonsodomenici.it:8080/ping/resources/pings/echo/hello";

    private final static String INFO_END_POINT =
            "http://alfonsodomenici.it:8080/ping/resources/health/os-info";

    private TextView lblPing,lblInfo;

    interface RestCallback{
        void onResponse(String response);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblPing = (TextView) findViewById(R.id.lblPing);
        lblInfo = (TextView) findViewById(R.id.lblInfo);
    }

    public void onPing(View view) {
        Log.d(TAG, "onPing");
         new Call(new RestCallback() {
             @Override
             public void onResponse(String response) {
                 lblPing.setText(response + " -- " + System.currentTimeMillis());
             }
         }).execute(ECHO_END_POINT);
    }

    public void onInfo(View view) {
        Log.d(TAG, "onInfo");
        new Call(new RestCallback() {
            @Override
            public void onResponse(String response) {
                try {
                    lblInfo.setText("");
                    JSONObject json = new JSONObject(response);
                    Iterator<String> it = json.keys();
                    while(it.hasNext()){
                        String key = it.next();
                        lblInfo.setText(lblInfo.getText() + "\n" + key + ": " + json.getString(key));
                        Log.d(TAG,key + ": " + json.getString(key));
                    }
                } catch (JSONException e) {
                    Log.e(TAG,e.getMessage());
                }
            }
        }).execute(INFO_END_POINT);
    }


    private class Call extends AsyncTask<String, String, String> {

        private RestCallback callback;

        public Call(RestCallback callback){
            this.callback = callback;
        }

        @Override
        protected String doInBackground(String... params) {
            BufferedReader fromServer;
            StringBuilder response = new StringBuilder();
            try {
                Log.d(TAG, params[0]);
                URL url = new URL(params[0]);
                HttpURLConnection cn = (HttpURLConnection) url.openConnection();
                fromServer = new BufferedReader(new InputStreamReader(cn.getInputStream(), "UTF-8"));
                char[] buffer = new char[1024];
                int bytesRead = 0;
                while ((bytesRead = fromServer.read(buffer)) != -1) {
                    response.append(buffer, 0, bytesRead);
                    Log.d(TAG, response.toString());
                }
            } catch (Exception e) {

                Log.e(TAG, e.getMessage());

                return e.getMessage();

            }

            return response.toString();

        }

        @Override
        protected void onPostExecute(String response) {
            Log.d(TAG, "Response from server: " + response);
            callback.onResponse(response);
        }
    }
}
