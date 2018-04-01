package com.example.ryan.laparking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.Arrays;

public class CustomAdapter extends ArrayAdapter{
    ParkingLot[]modelItems=null;
    Context context;
    double lat, lng;
    String price;

    public CustomAdapter(Context context, ParkingLot[]resources, double lat, double lng)
    {
        super(context, R.layout.rows, resources);
        this.context=context;
        /*
        this.modelItems = Arrays.copyOfRange(resources, 0, 25);
        System.out.println("SORTED ARRAY: ");
        for (int j = 0; j < this.modelItems.length; j++)
        {
            System.out.print(this.modelItems[j].properties.formatDistance() + "\n");
        }*/
        this.modelItems=resources;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView=inflater.inflate(R.layout.rows, parent,false);
        Button btn=(Button) convertView.findViewById(R.id.lot_btn);

        if (!modelItems[position].properties.HourlyCost.equals(""))
            price = modelItems[position].properties.HourlyCost;
        else
            price = "N/A";

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                //Starting a new Intent
                Intent nextScreen = new Intent(context, Description.class);

                nextScreen.putExtra("Lot", modelItems[position].properties.LotName);
                nextScreen.putExtra("Distance", modelItems[position].properties.formatDistance() + " mi");
                nextScreen.putExtra("Price", price);
                nextScreen.putExtra("Hours", modelItems[position].properties.Hours);
                nextScreen.putExtra("Status", modelItems[position].properties.Status);
                nextScreen.putExtra("Address", modelItems[position].properties.Address);
                nextScreen.putExtra("Convenient To", modelItems[position].properties.ConvenientTo);
                nextScreen.putExtra("Total Spaces", modelItems[position].properties.Spaces);

                //Pass lat long dest
                nextScreen.putExtra("Lat_dest", modelItems[position].properties.Lat);
                nextScreen.putExtra("Lon_dest", modelItems[position].properties.Lon);

                //Pass lat long src
                nextScreen.putExtra("Lat_src", lat);
                nextScreen.putExtra("Lon_src", lng);

                // starting new activity
                context.startActivity(nextScreen);
            }
        });

        //btn.setText(modelItems[position].properties.LotName);
        btn.setText(Html.fromHtml("<b><big>" + modelItems[position].properties.LotName + "</big></b>" +  "<br />" +
                "<small>" + modelItems[position].properties.formatDistance() + " mi" + "<br />" + "Price: " + price + "</small>" + "<br />"));
        return convertView;

    }
}


