package com.example.ryan.laparking;

public class AddressGeo {
    AddrComponents[] results;
    String status;
}

class AddrComponents {
    AddrInfo[] address_components;
    String formatted_address;
    AddrGeometry geometry;
    String place_id;
    String[] types;
}

class AddrInfo {
    String long_name;
    String short_name;
    String[] types;
}

class AddrGeometry {
    LatLongPair location;
    String location_type;
    Viewport viewport;
}

class Viewport {
    LatLongPair northeast;
    LatLongPair southwest;
}

class LatLongPair {
    double lat;
    double lng;
}


