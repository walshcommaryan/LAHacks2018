package com.example.ryan.laparking;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class RetrieveFeedTask extends AsyncTask<Void, Void, Void> {
    private String str_url;
    private String json;

    public RetrieveFeedTask(String new_url)
    {
        str_url = new_url;
    }

    // Obtains the geojson file from LOTS_GJSON url
    // and returns it as a String.
    private void readMapServer() throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(str_url);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            json = buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        try
        {
            readMapServer();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public String getJson()
    {
        return json;
    }
}
