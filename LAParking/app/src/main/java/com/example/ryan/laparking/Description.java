package com.example.ryan.laparking;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

public class Description extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_description);

        Intent i = getIntent();

        Button desc = (Button) findViewById(R.id.descView);
        ParkingLot p = i.getParcelableExtra("Desc");

        String lot = i.getStringExtra("Lot");
        String dist = i.getStringExtra("Distance");
        String price = i.getStringExtra("Price");
        String hours = i.getStringExtra("Hours");
        String status = i.getStringExtra("Status");
        String address = i.getStringExtra("Address");
        String conv = i.getStringExtra("Convenient To");
        String spaces = i.getStringExtra("Total Spaces");
        
        desc.setText(Html.fromHtml("<b><big>" + lot + "</big></b>" +  "<br />" +
                "<small>" +  dist + "<br />" +
                "Price: " + price + "</small>" + "<br />" +
                "Hours: " + hours + "</small>" + "<br />" +
                "Status: " + status + "</small>" + "<br />" +
                "Address: " + address + "</small>" + "<br />" +
                "Convenient To: " + conv + "</small>" + "<br />" +
                "Total Spaces: " + spaces + "</small>" + "<br />"));
        
    }
}
