package com.pakiboy612gmailtest.hajjdost;

import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HHPlocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int hhpKey;

    private Double lat=0.0,lang=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hhplocation);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        hhpKey = getIntent().getExtras().getInt("hhplocationNum");

        Log.d("hhpKey", "onCreate: hhpKey = "+hhpKey);


        switch (hhpKey){
            case 1:
               lat = 21.4224779 ;
               lang = 39.8240889 ;
                break;
            case 2:
                lat = 21.4675431;
                lang = 39.766465 ;
                break;
            case 3:
                lat =  21.4334258;
                lang =  39.8267494;
                break;
            case 4:
                lat = 21.4334249 ;
                lang =  39.8114285;
                break;
            case 5:
                lat =  21.5343253;
                lang =  39.6938655;
                break;
            case 6:
                lat = 21.3874679 ;
                lang = 39.8834888 ;
                break;
            case 7:
                lat = 21.4212557 ;
                lang = 39.8636914 ;
                break;
            case 8:
                lat = 21.4293305 ;
                lang =  39.8133051;
                break;
            case 9:
                lat = 21.4359571 ;
                lang =  39.7064588;
                break;
            case 10:
                lat = 21.4575864 ;
                lang =  39.8570869;
                break;
            case 11:
                lat =  21.4103752;
                lang = 39.8210184 ;
                break;
            case 12:
                lat = 21.4566194 ;
                lang = 39.8483782 ;
                break;
            case 13:
                lat =  21.4347219;
                lang = 39.8264614 ;
                break;
            case 14:
                lat = 21.375682 ;
                lang = 39.9106632 ;
                break;
            case 21:
                lat = 24.4688839 ;
                lang = 39.6089143 ;
                break;
            case 22:
                lat = 24.4393542 ;
                lang = 39.6166848 ;
                break;
            case 23:
                lat = 24.4454542 ;
                lang = 39.6146787 ;
                break;
            case 24:
                lat = 24.4614972 ;
                lang = 39.6112088 ;
                break;
            case 25:
                lat =  24.4661672;
                lang = 39.6057918 ;
                break;
            case 26:
                lat = 24.4841312 ;
                lang = 39.5782978 ;
                break;
            case 27:
                lat =  24.4688839;
                lang = 39.6089143 ;
                break;
            case 28:
                lat = 24.4688839 ;
                lang = 39.6089143 ;
                break;
            case 29:
                lat = 24.4932454 ;
                lang = 39.5817018 ;
                break;
            case 30:
                lat = 24.4130307 ;
                lang = 39.5415767;
                break;
            case 31:
                lat = 24.4939202 ;
                lang = 39.5119936 ;
                break;
            case 32:
                lat = 24.4671219 ;
                lang = 39.6145063 ;
                break;
            case 33:
                lat = 24.4710127 ;
                lang = 39.4752813 ;
                break;
            default:
                lat = 24.5364299;
                lang = 39.2463513;
        }


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        UiSettings settings = mMap.getUiSettings();
        settings.setCompassEnabled(true);
        settings.setZoomControlsEnabled(true);

        LatLng selectedHHP = new LatLng(lat, lang);
        mMap.addMarker(new MarkerOptions().position(selectedHHP).title("Your's selected place"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedHHP,15f));
        final AlertDialog.Builder builder = new AlertDialog.Builder(HHPlocationActivity.this);
        builder.setMessage("To make route,,,,,\nClick red marker then press rout\ni-con appeared at lower right corner");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
