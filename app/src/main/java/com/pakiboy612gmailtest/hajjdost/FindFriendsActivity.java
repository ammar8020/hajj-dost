package com.pakiboy612gmailtest.hajjdost;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindFriendsActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener {

    private MapView findFriendMV ;
    private GoogleMap gMap;

    private ArrayList<String> latList , longList;
    private ArrayList<LatLongClass> latLongList;

    private String userGroup;

    private DatabaseReference DBreferance;

    private UiSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        SharedPreferences sharedPreferences = getSharedPreferences("PREF_KEY",MODE_PRIVATE);
        userGroup = sharedPreferences.getString("userGroup",null);

        latLongList = new ArrayList<>();

        findFriendMV = findViewById(R.id.findFriendsMV);
        findFriendMV.onCreate(savedInstanceState);
        findFriendMV.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("", "onMapReady");
                gMap = googleMap;
                settings = gMap.getUiSettings();
                gMap.setOnMarkerClickListener(FindFriendsActivity.this);
                initMap();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DBreferance =  firebaseDatabase.getReference();

                getDataFromDatabase();
            }
        });



    }


    @Override
    protected void onResume() {
        super.onResume();
        findFriendMV.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        findFriendMV.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        findFriendMV.onLowMemory();
    }

    @Override
    protected void onPause() {
        super.onPause();
        findFriendMV.onPause();
    }


    private void initMap() {
        int googlePlayStatus = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (googlePlayStatus != ConnectionResult.SUCCESS) {
            GooglePlayServicesUtil.getErrorDialog(googlePlayStatus, this, -1).show();
            finish();
        } else {
            if (gMap != null) {
                gMap.getUiSettings().setAllGesturesEnabled(true);
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                gMap.setMyLocationEnabled(true);

                settings.setCompassEnabled(true);
                settings.setZoomControlsEnabled(true);
                settings.setMyLocationButtonEnabled(true);
                settings.setScrollGesturesEnabled(true);

            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }


    private void getDataFromDatabase() {
        Log.d("datafromDB", "getDataFromDatabase: is called.."+userGroup.toString());
        DBreferance.child("Latlong").orderByChild("myGroupis").equalTo(userGroup.toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("ondatachange", "onDataChange: entered");

                if (dataSnapshot.exists()) {
                   // List<LatLongClass> data = new ArrayList<>();
                    Log.d("ondatachange", "onDataChange: datasnapshot.exist true");
                    String[] values = {"Kamal","Talha","Adnan","Mubeen","Hamza", "Ammar"};
                    int i = 0;
                    gMap.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.d("ondatachange", "onDataChange: loop is running");
                        LatLongClass element = snapshot.getValue(LatLongClass.class);
                        latLongList.add(element);
                        LatLng latLng = new LatLng(element.latitudeee,element.longitudeee);
                        LatLng latLng2 = new LatLng(-33.852, 151.211);

                        gMap.addMarker(new MarkerOptions().position(latLng).title(values[i]).visible(true)).showInfoWindow();
//                        gMap.addMarker(new MarkerOptions().position(latLng2).title(element.userNames));
                        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,19f));
                        i+=1;
                        if (i == 6){
                            i = 0;
                        }
                        Log.d("latlanginObj", "onDataChange: "+element.latitudeee+"  "+element.longitudeee + " " + element.userNames);
                    }


//                    latList.clear();
//                    longList.clear();
//                    for (LatLongClass latLongClass : data) {
//
//                        String latStr = latLongClass.laat;
//                        latList.add(latStr);
//                        String longStr = latLongClass.loong;
//                        longList.add(longStr);
//                        Log.d("usersData", "onDataChange: "+latStr+"  "+longStr);
//                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
