package com.example.ryan.laparking;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.ryan.laparking.R;

public class CustomAdapter extends ArrayAdapter{
    Model[]modelItems=null;
    Context context;
    public CustomAdapter(Context context, Model[]resources)
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

        return convertView;

    }


}


