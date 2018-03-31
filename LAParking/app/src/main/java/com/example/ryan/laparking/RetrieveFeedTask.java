package com.example.ryan.laparking;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class RetrieveFeedTask extends AsyncTask<Void, Void, Void> {
    // Constant URL address for obtaining parking lot data
    private static final String LOTS_GJSON =
            "http://geohub.lacity.org/datasets/be7c8c4ab95b4d82af18255ad1a3212c_2.geojson";
    private String gson;

    // Obtains the geojson file from LOTS_GJSON url
    // and returns it as a String.
    private void readMapServer() throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(LOTS_GJSON);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            gson = buffer.toString();
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

    public String getGson()
    {
        return gson;
    }
}
