package com.example.ryan.laparking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

public class LotList extends Activity{
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lot_list);
        lv = (ListView) findViewById(R.id.listView1);

        //Get intent data dest
        Intent i = getIntent();
        String dest = i.getStringExtra("Dest");
        System.out.println(dest);

        // Grab GEOJSON data from API
        RetrieveFeedTask get_geojson = new RetrieveFeedTask();
        get_geojson.execute();
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        String geojson = get_geojson.getGson();
        ParkingLot[] pl_list = new Gson().fromJson(geojson, LotsData.class).features;

        CustomAdapter adapter = new CustomAdapter(this, pl_list);
        lv.setAdapter(adapter);



    }

}
