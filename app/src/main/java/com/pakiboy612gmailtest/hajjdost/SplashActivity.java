package com.pakiboy612gmailtest.hajjdost;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 1000;
    Context context = SplashActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

       // isAirplaneModeOn(context);

        makeDecisionWhatToDo();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        makeDecisionWhatToDo();
    }

    private void makeDecisionWhatToDo() {

        new Handler().postDelayed(new Runnable() { // ********************************************** this is splash screen timer =2 sec
            @Override
            public void run() {

                if (isLoggedIn()) {
                    startActivity(new Intent(context, MainActivity.class));
                    finish();
                } else {

                    if (isNetworkAvailable() || isWiFiAvailable()) { // ***********************************first check if mobile data or wifi is enabled
                         //********************************************************second check if location is enabled

                            startActivity(new Intent(context, RegistrationActivity.class));
                            finish();

//                new Handler().postDelayed(new Runnable() { // ************this is splash screen timer =2 sec
//                    @Override
//                    public void run() {
//
//                        if (isLoggedIn()) {
//                            startActivity(new Intent(context, MainActivity.class));
//                            finish();
//                        } else {
//                            startActivity(new Intent(context, RegistrationActivity.class));
//                            finish();
//                        }
//
//
//                    }
//                }, SPLASH_TIME_OUT);


                    }else {
                        Log.d("iamhere", "isNetworkAvailable: else is called");
                        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                        dialog.setMessage("Please Enable mobile-data or WiFi to use app");
                        dialog.setPositiveButton(context.getResources().getString(R.string.open_internet_settings), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                //************************************************************************ open GPS setting to Enable GPS Connection
                                Intent myIntent = new Intent( Settings.ACTION_SETTINGS);
                                context.startActivity(myIntent);
                            }
                        });
                        dialog.setNegativeButton("Close app", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                finish();
                            }
                        });
                        dialog.show();
                    }
                }
            }
        }, SPLASH_TIME_OUT);
    }

    private boolean isLoggedIn() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }

    private boolean isWiFiAvailable() { // *****************************************************check if wifi connection established
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifi.isConnected();
    }

    private boolean isNetworkAvailable() {  // *****************************************************check if mobile data or wifi is enabled
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        NetworkInfo isWiFiEnabled = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

//        if (activeNetworkInfo!=null && activeNetworkInfo.isConnected() || isWiFiEnabled.isConnected()) {
//            Log.d("iamhere", "isNetworkAvailable: if is called");
//        } else {
//        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private boolean locationEnabled() {
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled) { //****************************************************** if GPS is not enabled then ask to enable GPS to use app
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage("Please Enable GPS connection to use app");
            dialog.setPositiveButton(context.getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    //************************************************************************ open GPS setting to Enable GPS Connection
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(myIntent);
                }
            });
            dialog.setNegativeButton("Close app", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    finish();
                }
            });
            dialog.show();
        }
        return gps_enabled;
    }
}