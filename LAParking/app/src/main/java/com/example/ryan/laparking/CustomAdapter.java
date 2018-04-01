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

public class CustomAdapter extends ArrayAdapter{
    ParkingLot[]modelItems=null;
    Context context;

    public CustomAdapter(Context context, ParkingLot[]resources)
    {
        super(context, R.layout.rows, resources);
        this.context=context;
        this.modelItems=resources;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView=inflater.inflate(R.layout.rows, parent,false);
        Button btn=(Button) convertView.findViewById(R.id.lot_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                //Starting a new Intent
                Intent nextScreen = new Intent(context, Description.class);

                nextScreen.putExtra("Lot", modelItems[position].properties.LotName);
                nextScreen.putExtra("Distance", "Distance: ");
                nextScreen.putExtra("Price", modelItems[position].properties.HourlyCost);
                nextScreen.putExtra("Hours", modelItems[position].properties.Hours);
                nextScreen.putExtra("Status", modelItems[position].properties.Status);
                nextScreen.putExtra("Address", modelItems[position].properties.Address);
                nextScreen.putExtra("Convenient To", modelItems[position].properties.ConvenientTo);
                nextScreen.putExtra("Total Spaces", modelItems[position].properties.Spaces);

                // starting new activity
                context.startActivity(nextScreen);
            }
        });

        //btn.setText(modelItems[position].properties.LotName);
        btn.setText(Html.fromHtml("<b><big>" + modelItems[position].properties.LotName + "</big></b>" +  "<br />" +
                "<small>" + "Distance: " + "<br />" + "Price: " + modelItems[position].properties.HourlyCost + "</small>" + "<br />"));
        return convertView;

    }
}


