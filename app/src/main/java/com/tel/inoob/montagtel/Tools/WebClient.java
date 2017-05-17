package com.tel.inoob.montagtel.Tools;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author inoob
 */
public class WebClient {
    private static final String TAG = WebClient.class.getSimpleName();
    private final String LINK;
    private TextView view;

    public WebClient(final String path, TextView view) {
        this.LINK = path;
        this.view = view;
    }

    public WebClient(final String path){
        this.LINK = path;
    }



    public String getJSON(){
        return new JSON().execute(LINK).toString();
    }

    public class JSON extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder sb = new StringBuilder();
            try{
                URL url = new URL(strings[0]);
                URLConnection connection = url.openConnection();
                // This does a GET conn.setDoInput(true);; to do a POST, add conn.setDoOutput(true);
                connection.setDoOutput(true);
                connection.setAllowUserInteraction(true);

                connection.connect();
                // To do a POST, you'd write to conn.getOutputStream());
                // To do a GEt , you'd write to conn.getInputStream();


                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                try{
                    while ((line = reader.readLine()) != null){
                        sb.append(line);
                    }
                    System.out.println(sb.toString());
                } catch (IOException e) {
                    Log.e(TAG,"convertStreamToString IOException: " + e.getMessage());
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(TAG,"convertStreamToString finally IOException: " + e.getMessage());
                    }
                }

            } catch (MalformedURLException e) {
                Log.e(TAG,"MalformedURLException: " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG,"IOException: " + e.getMessage());
            }

            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            view.setText(s);
        }
    }

}
