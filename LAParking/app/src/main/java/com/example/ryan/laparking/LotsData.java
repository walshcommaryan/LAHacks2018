package com.example.ryan.laparking;


public class LotsData
{
    String type = "N/A";    // Data stored type (i.e. "FeatureCollection")
    ParkingLot[] features;  // An Array of all parking lots in LA
}

class ParkingLot
{
    String type  = "N/A";    // Lot information Type
    LotProperties properties;   // Lot properties
    LotsGeometry geometry;
}

class LotProperties
{
    int ID; // Lot ID: (e.g. 250)
    String FacilityID = "N/A";
    String LotName = "N/A"; // Name of the parking lot (e.g. "Friar & Sylmar Parking Garage")
    String Community = "N/A";   // e.g. "Van Nuys"
    String CD = "N/A";
    String Address = "N/A"; // Address of parking lot (e.g. "14401 Friar St")
    String City = "N/A";    // e.g. "Van Nuys"
    String State = "N/A";   // usually just "CA"
    String Zipcode = "N/A"; // ranges 91401-2125
    double Lat = 0; // latitude
    double Lon = 0; // longitude
    String ConvenientTo = "N/A";    // Areas around parking lot (e.g. "San Fernando Valley Civic Center & shops")
    String Entrance = "N/A";    // Where entrance is located (e.g. "Friar St east of Van Nuys Blvd")
    String Operator = "N/A";    // e.g. "by General Services Dept"
    String Type = "N/A";    // Kind of operator (e.g. "Operated")
    String Phone = "N/A";   // Parking lot phone number
    String Hours = "N/A";   // Opening hours (e.g. "5:30am-7pm Mon-Fri")
    String HourlyCost = "N/A";  // Formatted with "$" (e.g. $1.80")
    String DailyCost = "N/A";
    String MonthlyCost = "N/A";
    String SpecialFeatures = "N/A"; // Special info (e.g. "Closed nightly & Sat-Sun all day; max height clearance 6 feet 9 inches")
    String Spaces = "N/A";  // Total number of spaces in parking lot
    String Status = "N/A";  // Operational status (e.g. "Operational")
    String TOOLTIP = "N/A"; // String containing all address info of parking lot (e.g. "Friar & Sylmar Parking Garage\\n14401 Friar St, Van Nuys CA 91401-2125")
    String nla_url = "N/A"; // ignore

    double dist_to_dest = 0;    // Distance to the user's set destination, unit: meters

    public String formatDistance()
    {
        double miles = dist_to_dest / 1609.344;
        int scale = (int) Math.pow(10, 1);
        miles = (double) Math.round(miles * scale) / scale;

        return Double.toString(miles);
    }
}

class LotsGeometry
{
    String type = "N/A";    // Type of geojson (usually "Point")
    double[] coordinates;   // index 1 is latitude, index 0 is longitude
}
