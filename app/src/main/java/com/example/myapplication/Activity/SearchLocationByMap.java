package com.example.myapplication.Activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.myapplication.APIController.TourAPIController;
import com.example.myapplication.ProblemDomain.Location;
import com.example.myapplication.ProblemDomain.TourData;
import com.example.myapplication.R;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static com.example.myapplication.ProblemDomain.Constants.GET_LOCATION;
import static com.example.myapplication.ProblemDomain.Constants.LOCATION_TOUR_CODE;

public class SearchLocationByMap extends AppCompatActivity  implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private LocationListener locationListener;
    private LocationManager lm;

    private TourAPIController tourAPIController;
    private String query = "";

    private ArrayList<Location> locations;
    private ArrayList<TourData> temp = null;

    private double myLocX, myLocY;

    private Location selectedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location_by_map);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(android.location.Location location) {
                lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                myLocX = location.getLongitude();
                myLocY = location.getLatitude();
            }
        };
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        locations = new ArrayList<Location>();
        Intent intent = getIntent();

        String type = intent.getStringExtra("TYPE");
        tourAPIController = TourAPIController.getToruAPIController();
        query += type;
        query += "&mapX=";
        query += Double.toString(128.608722);
        query += "&mapY=";
        query +=  Double.toString(35.886876);
        query += "&radius=1500";;

        temp = tourAPIController.queryAPI(LOCATION_TOUR_CODE, query);
        Log.d("test", "temp count : " + temp.size());
        query = "";

        LatLng myLocation = new LatLng(35.886876, 128.608722);

        MarkerOptions myMarker = new MarkerOptions();
        myMarker.position(myLocation);
        myMarker.title("현재 위치");
        myMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

        Marker marker = googleMap.addMarker(myMarker);
        marker.setTag(-1);

        Log.d("test", "location count : " + temp.size());

        for(int i=0; i<temp.size(); i++){
            MarkerOptions markOption = new MarkerOptions();
            markOption.position(new LatLng(temp.get(i).getMapY(), temp.get(i).getMapX()));
            markOption.title(temp.get(i).getTitle());
            Marker tempMarker = googleMap.addMarker(markOption);
            tempMarker.showInfoWindow();
            tempMarker.setTag(i);
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13));

        googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        int index = (int) marker.getTag();

        if(index > -1){

            selectedLocation = temp.get(index).toLocation();
            Intent intent = new Intent();
            intent.putExtra("result", selectedLocation);
            setResult(GET_LOCATION, intent);
            Log.d("test", "[ SearchLocationByMapActivity ] selected Location : " + selectedLocation.toString());
            finish();
        }
        return false;
    }
}
