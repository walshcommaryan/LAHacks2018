package com.example.ryan.laparking;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;


import com.example.ryan.laparking.R;

import java.util.Calendar;

public class Preferences extends Activity {

    Button btnNextScreen;
    Button getLocation;
    LocationManager mLocationManager;
    boolean using_current = false;
    double current_lat = 0;
    double current_lng = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_preferernces);
        setupUI(findViewById(R.id.menu));

        btnNextScreen = (Button) findViewById(R.id.button_go);
        btnNextScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), LotList.class);
                EditText editText = (EditText) findViewById(R.id.editText);

                nextScreen.putExtra("UseCurrentLoc", using_current);
                if (using_current)
                {
                    nextScreen.putExtra("CurrentLat", current_lat);
                    nextScreen.putExtra("CurrentLng", current_lng);
                }
                else
                    nextScreen.putExtra("Dest", editText.getText().toString());

                // starting new activity
                startActivity(nextScreen);
            }
        });

        getLocation = (Button) findViewById(R.id.btn_currentloc);

        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
            return;
        }

        mLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 1, 1000, new MyLocationListener());

        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabCurrentLocation();
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

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(Preferences.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public void grabCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
            return;
        }
        Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        System.out.println("REACHED");
        if(location != null) {
            current_lat = location.getLatitude();
            current_lng = location.getLongitude();
            System.out.print("LATLONG:");
            System.out.println(current_lat);
            System.out.println(current_lng);
            using_current = true;
            btnNextScreen.performClick();
        }

        /*
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
            return;
        }

        Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null)
        {
            System.out.print("LOCATION: ");
            System.out.println(location.getTime());
            System.out.print("TIME: ");
            System.out.println(Calendar.getInstance().getTimeInMillis());
        }
        if(location != null && location.getTime() > Calendar.getInstance().getTimeInMillis() - 2 * 60 * 1000) {
            current_lat = location.getLatitude();
            current_lng = location.getLongitude();
            System.out.print("LATLONG:");
            System.out.println(current_lat);
            System.out.println(current_lng);
            using_current = true;
        }
        else {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
        */
    }

    private class MyLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location) {
            /*
            if (location != null) {
                Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());
                mLocationManager.removeUpdates(this);
            }
            */
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
}
