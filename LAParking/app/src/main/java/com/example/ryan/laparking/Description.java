package com.example.ryan.laparking;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
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

        //Get lat long dest
        final double lat_dest = i.getDoubleExtra("Lat_dest", 0);
        final double lng_dest = i.getDoubleExtra("Lon_dest", 0);

        //Get lat long src
        final double lat_src = i.getDoubleExtra("Lat_src", 0);
        final double lng_src = i.getDoubleExtra("Lon_src", 0);
        
        desc.setText(Html.fromHtml("<b><big>" + lot + "</big></b>" +  "<br />" +
                "<small>" +  dist + "<br />" +
                "Price: " + price + "</small>" + "<br />" +
                "Hours: " + hours + "</small>" + "<br />" +
                "Status: " + status + "</small>" + "<br />" +
                "Address: " + address + "</small>" + "<br />" +
                "Convenient To: " + conv + "</small>" + "<br />" +
                "Total Spaces: " + spaces + "</small>" + "<br />"));

        Button btn_google = (Button) findViewById(R.id.btn_google);
        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format("https://www.google.com/maps/dir/%s,%s/%s,%s/",
                        lat_src, lng_src, lat_dest, lng_dest);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
    }
}
