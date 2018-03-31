package com.example.ryan.laparking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class LotList extends Activity{
    Model modelItems[];
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lot_list);
        lv = (ListView) findViewById(R.id.listView1);

        Intent i = getIntent();

        modelItems = new Model[100];

        CustomAdapter adapter = new CustomAdapter(this, modelItems);
        lv.setAdapter(adapter);
    }
}
