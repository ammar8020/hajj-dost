package com.pakiboy612gmailtest.hajjdost;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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

public class CurrentLocationActivityNew extends AppCompatActivity implements GoogleMap.OnMarkerClickListener {

    private static final String TAG = CurrentLocationActivityNew.class.getCanonicalName();

    private MapView mMapView;
    private GoogleMap mGoogleMap;

    private UiSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location_new);

        mMapView = findViewById(R.id.currentLocationMV);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG, "onMapReady");
                mGoogleMap = googleMap;
                settings = mGoogleMap.getUiSettings();
                mGoogleMap.setOnMarkerClickListener(CurrentLocationActivityNew.this);
                initMap();
            }
        });

        AdView mAdView = findViewById(R.id.adViewCurrentLocation);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    private void initMap() {
        int googlePlayStatus = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (googlePlayStatus != ConnectionResult.SUCCESS) {
            GooglePlayServicesUtil.getErrorDialog(googlePlayStatus, this, -1).show();
            finish();
        } else {
            if (mGoogleMap != null) {
                mGoogleMap.getUiSettings().setAllGesturesEnabled(true);
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    //   here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mGoogleMap.setMyLocationEnabled(true);

                settings.setCompassEnabled(true);
                settings.setZoomControlsEnabled(true);
                settings.setMyLocationButtonEnabled(true);
                settings.setScrollGesturesEnabled(true);

                SharedPreferences myLatLang = getSharedPreferences("myLatLang",MODE_PRIVATE);
                String lat = myLatLang.getString("myLat","21.4224888");
                String lang = myLatLang.getString("myLang","39.8261643");


                // Add a marker in myCurrentLocation mecca and move the camera
                LatLng myCurrentLocation = new LatLng(Float.valueOf(lat),Float.valueOf(lang));
                mGoogleMap.addMarker(new MarkerOptions().position(myCurrentLocation).title("Last Known location"));
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myCurrentLocation,19f));
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
