package com.pakiboy612gmailtest.hajjdost;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, OnMapReadyCallback {

    private final static String TAG = "MapsActivity";

//    private GoogleMap mMap;


    MapFragment mMap;

    private GoogleMap mMap2;


    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Circle mCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        buildGoogleApiClient();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }


    protected synchronized void buildGoogleApiClient() {
        Toast.makeText(this, "buildGoogleApiClient", Toast.LENGTH_SHORT).show();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "onConnected", Toast.LENGTH_SHORT).show();

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(2 * 1000);
        mLocationRequest.setFastestInterval(2 * 1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(0.1F);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
//            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
//                    .getMap();



            mMap = (MapFragment) getFragmentManager()
                    .findFragmentById(R.id.map);

//            mMap = (MapFragment) getChildFragmentManager()
//                    .findFragmentById(R.id.map);



            mMap.getMapAsync(this);



            // Check if we were successful in obtaining the map.
//            if (mMap != null) {
//                setUpMap();
//            }
        }
    }

    private void setUpMap() {
        mMap2.getUiSettings().setMapToolbarEnabled(true);
        mMap2.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap2.setMyLocationEnabled(true);

        circle();

    }

    public void circle()
    {
        double radiusInMeters = 1000.0;
        int strokeColor = 0xffff0000; //red outline
        int shadeColor = 0x44ff0000; //opaque red fill

//        lat = 37.9614
//        longitude = -122.105

//        33.621606400000005
//        73.0710016

//        32.9085
//        73.7654


//        32.907316
//        73.750176

        mCircle = mMap2.addCircle (new CircleOptions()
                .center(new LatLng(32.907316, 73.750176))
                .radius(radiusInMeters)
                .fillColor(shadeColor)
                .strokeColor(strokeColor)
                .strokeWidth(1));


        Log.d(TAG, "onLocationChanged: Center of Cirlcle: Latitude: " + mCircle.getCenter().latitude
                + " Longitude: " + mCircle.getCenter().longitude);



    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this,"onConnectionSuspended",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this,"onConnectionFailed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {

        Log.d("locationtesting",  "lat: " + location.getLatitude() + " lon: " + location.getLongitude());

        Toast.makeText(this,"Location Changed",Toast.LENGTH_SHORT).show();

        Log.d(TAG, "onLocationChanged: Current Location: Latitude: " + location.getLatitude()
                + " Longitude: " + location.getLongitude());

        Log.d(TAG, "onLocationChanged: Center of Cirlcle: Latitude: " + mCircle.getCenter().latitude
                + " Longitude: " + mCircle.getCenter().longitude);

        float[] distance = new float[2];

        Location.distanceBetween( location.getLatitude(), location.getLongitude(),
                mCircle.getCenter().latitude, mCircle.getCenter().longitude, distance);

        if( distance[0] < mCircle.getRadius() ){
            //current location is within circle
            //start new activity
//            Intent i = new Intent(this, OtherActivity.class);
//            startActivity(i);

            Log.d(TAG, "onLocationChanged: You are inside circle");

            Toast.makeText(this, "You are inside circle", Toast.LENGTH_SHORT).show();

        } else {

            Log.d(TAG, "onLocationChanged: You are outside circle");

            Toast.makeText(this, "You are outside circle", Toast.LENGTH_SHORT).show();


        }
    }


    @Override
    public void onMapReady(GoogleMap map) {

//        googleMap = map;
        mMap2 = map;

        setUpMap();

    }


}
