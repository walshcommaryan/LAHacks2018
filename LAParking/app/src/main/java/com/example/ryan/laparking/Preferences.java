package com.example.ryan.laparking;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.ryan.laparking.R;

public class Preferences extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_preferernces);


        Button btnNextScreen = (Button) findViewById(R.id.button_go);
        btnNextScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), LotList.class);

                //Sending data to another Activity
            /*
            nextScreen.putExtra("len", listLen);

            for (int i = 0; i < listLen; i++) {
                nextScreen.putExtra("symptom " + Integer.toString(i + 1), modelItems[i].getName());
                nextScreen.putExtra("id " + Integer.toString(i + 1), modelItems[i].getId());
                nextScreen.putExtra("val " + Integer.toString(i + 1), modelItems[i].getValue());
            }
            */
                // starting new activity
                startActivity(nextScreen);

            }
        });
    }

    // DEFAULT STUFF PROJECT LOADED IN

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preferernces, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
