package com.example.ryan.laparking;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class LotList extends Activity{
    Model modelItems[];
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lot_list);
        lv = (ListView) findViewById(R.id.listView1);

        Intent i = getIntent();

        System.out.println("TESTING");

        modelItems = new Model[100];

        CustomAdapter adapter = new CustomAdapter(this, modelItems);
        lv.setAdapter(adapter);

        // Grab GEOJSON data from API
        RetrieveFeedTask get_geojson = new RetrieveFeedTask();
        get_geojson.execute();

        try
        {
            Thread.sleep(2500);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        String geojson = get_geojson.getGson();

        System.out.println(geojson);
        System.out.println("END OF TESTING");
    }
}
