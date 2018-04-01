package com.example.ryan.laparking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Comparator;

public class LotList extends Activity{
    // Constant URL address for obtaining parking lot data
    private static final String LOTS_GJSON =
            "http://geohub.lacity.org/datasets/be7c8c4ab95b4d82af18255ad1a3212c_2.geojson";
    private static final String GOOGLE_KEY = "&key=AIzaSyCmRXk4wJQSzGWLeN11K3VxjHkWVqjCLx4";
    private String geocode_api = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private ParkingLot[] pl_list;
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
        String geojson = grabJson(LOTS_GJSON);
        pl_list = new Gson().fromJson(geojson, LotsData.class).features;

        try {
            // Get latitude and longitude of the user's
            // input destination address.
            String geocode_url = geocode_api + URLEncoder.encode(dest, "UTF-8") + GOOGLE_KEY;
            String lat_long_json = grabJson(geocode_url);
            AddressGeo address_geo = new Gson().fromJson(lat_long_json, AddressGeo.class);

            // Calculate the distance of parking lots to
            // user's destination address. And then sort
            // the ParkingLots pl_list array based on the
            // distance in ascending order (shortest distance
            // at the front of the array).
            setLotsDistance(address_geo.results[0].geometry.location.lat, address_geo.results[0].geometry.location.lng);

            Arrays.sort(pl_list, new SortByDistance());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ParkingLot[] closestLots = Arrays.copyOfRange(pl_list, 0, 25);
        /*
        System.out.println("SORTED ARRAY: ");
        for (int j = 0; j < closestLots.length; j++)
        {
            System.out.print(closestLots[j].properties.formatDistance() + "\n");
        }
        */

        CustomAdapter adapter = new CustomAdapter(this, closestLots);
        lv.setAdapter(adapter);
    }

    private String grabJson(String str_url)
    {
        RetrieveFeedTask get_geojson = new RetrieveFeedTask(str_url);
        get_geojson.execute();
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return get_geojson.getJson();
    }

    private double calcDistance(double lat, double lng, double lat2, double lng2)
    {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat);
        double lonDistance = Math.toRadians(lng2 - lng);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        return distance;
    }

    private void setLotsDistance(double lat, double lng)
    {
        for (int i = 0; i < pl_list.length; i++)
        {
            pl_list[i].properties.dist_to_dest = calcDistance(lat, lng,
                    pl_list[i].geometry.coordinates[1], pl_list[i].geometry.coordinates[0]);
        }
    }

    class SortByDistance implements Comparator<ParkingLot>
    {
        public int compare(ParkingLot left, ParkingLot right)
        {
            return (int) (left.properties.dist_to_dest - right.properties.dist_to_dest);
        }
    }
}
