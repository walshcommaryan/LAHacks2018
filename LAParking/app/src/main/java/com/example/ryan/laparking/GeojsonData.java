package com.example.ryan.laparking;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GeojsonData
{
    // Constant URL address for obtaining parking lot data
    private static final String LOTS_GJSON =
            "http://geohub.lacity.org/datasets/be7c8c4ab95b4d82af18255ad1a3212c_2.geojson";

    // Obtains the geojson file from LOTS_GJSON url
    // and returns it as a String.
    private static String readMapServer() throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(LOTS_GJSON);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    // main() function primarily used for debuggind and testing.
    // Can delete or comment out at the final build when the
    // GUI is implemented.
    public static void main(String[] args)
    {
        try
        {
            System.out.println("TESTING...");
            String geojson = readMapServer();
            System.out.println(geojson);
            LotsData ld = new Gson().fromJson(geojson, LotsData.class);
            System.out.println(ld.type);
            System.out.println(ld.features[0].properties.Address);
            System.out.println(ld.features[0].geometry.coordinates[0]);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("END OF TESTING");
    }
}


class LotsData
{
    String type;    // Data stored type (i.e. "FeatureCollection")
    ParkingLot[] features;  // An Array of all parking lots in LA
}

class ParkingLot
{
    String type;    // Lot information Type
    LotProperties properties;   // Lot properties
    LotsGeometry geometry;
}

class LotProperties
{
    int ID; // Lot ID: (e.g. 250)
    String FacilityID;
    String LotName; // Name of the parking lot (e.g. "Friar & Sylmar Parking Garage")
    String Community;   // e.g. "Van Nuys"
    String CD;
    String Address; // Address of parking lot (e.g. "14401 Friar St")
    String City;    // e.g. "Van Nuys"
    String State;   // usually just "CA"
    String Zipcode; // ranges 91401-2125
    double Lat; // latitude
    double Lon; // longitude
    String ConvenientTo;    // Areas around parking lot (e.g. "San Fernando Valley Civic Center & shops")
    String Entrance;    // Where entrance is located (e.g. "Friar St east of Van Nuys Blvd")
    String Operator;    // e.g. "by General Services Dept"
    String Type;    // Kind of operator (e.g. "Operated")
    String Phone;   // Parking lot phone number
    String Hours;   // Opening hours (e.g. "5:30am-7pm Mon-Fri")
    String HourlyCost;  // Formatted with "$" (e.g. $1.80")
    String DailyCost;
    String MonthlyCost;
    String SpecialFeatures; // Special info (e.g. "Closed nightly & Sat-Sun all day; max height clearance 6 feet 9 inches")
    String Spaces;  // Total number of spaces in parking lot
    String Status;  // Operational status (e.g. "Operational")
    String TOOLTIP; // String containing all address info of parking lot (e.g. "Friar & Sylmar Parking Garage\\n14401 Friar St, Van Nuys CA 91401-2125")
    String nla_url; // ignore
}

class LotsGeometry
{
    String type;    // Type of geojson (usually "Point")
    double[] coordinates;
}
