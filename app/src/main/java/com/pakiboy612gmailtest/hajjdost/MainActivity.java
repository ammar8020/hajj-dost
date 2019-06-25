package com.pakiboy612gmailtest.hajjdost;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pakiboy612gmailtest.hajjdost.ui.LoginActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout homeBtn,profileBtn,myLocationBtn,findFriendsBtn,myGroupBtn,callForHelpBtn,hollyPlacesBtn,settingsBtn,userGuideBtn,aboutBtn, twaafCounter, extrasBtn;
    public String userName , userAddress, userAge, userGender, userGroup, userNumber;

    String lat = "0.0";
    String lon = "0.0";
    String accuracy = "0.0";

    DatabaseReference myRef;

    SharedPreferences sharedPreferences , myLatLang,homeLocationPref;

    ArrayList<String> numbersList;

    ImageView playPrayIcon;

    AdView mAdView;


    // to commit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-1621044802376539~3944428066");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        homeBtn = findViewById(R.id.homeLocatinBtn);
        profileBtn = findViewById(R.id.profileBtn);
        myLocationBtn = findViewById(R.id.myLocationBtn);
        findFriendsBtn = findViewById(R.id.findFriendsBtn);
        myGroupBtn = findViewById(R.id.myGroupBtn);
        callForHelpBtn = findViewById(R.id.callForHelpBtn);
        hollyPlacesBtn = findViewById(R.id.hollyPlacesBtn);
        settingsBtn = findViewById(R.id.hajjGuideBtn);
        userGuideBtn = findViewById(R.id.pray);
        aboutBtn = findViewById(R.id.aboutBtn);

        twaafCounter = findViewById(R.id.twaaf_counter);
        extrasBtn = findViewById(R.id.extras);

        homeBtn.setOnClickListener(this);
        profileBtn.setOnClickListener(this);
        myLocationBtn.setOnClickListener(this);
        findFriendsBtn.setOnClickListener(this);
        myGroupBtn.setOnClickListener(this);
        callForHelpBtn.setOnClickListener(this);
        hollyPlacesBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        userGuideBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);

        twaafCounter.setOnClickListener(this);
        extrasBtn.setOnClickListener(this);

        numbersList = new ArrayList<>();

        sharedPreferences = getSharedPreferences("PREF_KEY",MODE_PRIVATE);
        myLatLang = getSharedPreferences("myLatLang",MODE_PRIVATE);



        FirebaseApp.initializeApp(this);



        myRef = FirebaseDatabase.getInstance().getReference();

        getDataFromDatabase();

        Log.d("locationUpdate", "onCreate: lacationUpdate() called");
        locationUpdate();
        startService(new Intent(MainActivity.this,UpdateLatLangService.class)); //**** background service will run

        Calendar calender = Calendar.getInstance();

        calender.set(Calendar.HOUR_OF_DAY,0);
        calender.set(Calendar.MINUTE,14);
        calender.set(Calendar.SECOND,20);

        Intent intent = new Intent(getApplicationContext(),NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calender.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("locationUpdate", "onRestart: locationUpdate() called");
       // locationUpdate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeLocatinBtn:
//
//                Uri gmmIntentUri = Uri.parse("geo:0,0?q=");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);

                if (isNetworkAvailable() || isWiFiAvailable()) { // ******************************** first check if mobile data or wifi is enabled
                    if (locationEnabled()) { //***************************************************** second check if location is enabled
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("You can 'show' and 'change' your Home Location");
                        builder.setPositiveButton("Show Home location", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (isHomeLocationSaved()) { //************************************* if home location is saved thin show on map.
                                    startActivity(new Intent(MainActivity.this,HomeLocationActivityNew.class));
                                } else {
                                    saveHomeLocation(); //****************************************** to save current location as home location.
                                }
                            }
                        });
                        builder.setNegativeButton("Change Home location", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                saveHomeLocation();
                            }
                        });
                        builder.show();
                    }

                }else {
                    openSettingsToEnableNetConnection();
                }
                break;
            case R.id.profileBtn: //**************************************************************** lunch view and edit profile activity
                if (isNetworkAvailable() || isWiFiAvailable()) { // ******************************** first check if mobile data or wifi is enabled
                    startActivity(new Intent(MainActivity.this , ProfileActivity.class));
                }else {
                   openSettingsToEnableNetConnection();
                }
                break;
            case R.id.myLocationBtn: //************************************************************* lunch map activity to show current location.
//                Uri mylocationUri = Uri.parse("geo:0,0?q=");
//                Intent mylocationIntent = new Intent(Intent.ACTION_VIEW, mylocationUri);
//                mylocationIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mylocationIntent);

                if (isNetworkAvailable() || isWiFiAvailable()) { // ******************************** first check if mobile data or wifi is enabled
                    if (locationEnabled()) {
                        startActivity(new Intent(MainActivity.this, CurrentLocationActivityNew.class));
                    }
                }else {
                    openSettingsToEnableNetConnection();
                }
                break;
            case R.id.findFriendsBtn:
                if (isNetworkAvailable() || isWiFiAvailable()) { // ******************************** first check if mobile data or wifi is enabled
                    if (locationEnabled()) {
                        startActivity(new Intent(MainActivity.this,FindFriendsActivity.class));
                    }
                }else {
                    openSettingsToEnableNetConnection();
                }
                break;
            case R.id.myGroupBtn:
                if (isNetworkAvailable() || isWiFiAvailable()) { // ******************************** first check if mobile data or wifi is enabled
                        startActivity(new Intent(MainActivity.this, GroupListActivity.class));
                }else {
                    openSettingsToEnableNetConnection();
                }
                break;
            case R.id.callForHelpBtn:

                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setMessage("Sent SMS to all group members");
                dialog.setPositiveButton("Sent", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        getDataAndSendSms();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        paramDialogInterface.cancel();
                    }
                });
                dialog.show();
                break;
            case R.id.hollyPlacesBtn:
                if (isNetworkAvailable() || isWiFiAvailable()) { // ***********************************first check if mobile data or wifi is enabled
                    startActivity(new Intent(MainActivity.this , HollyPlacesActivity.class));
                }else {
                    openSettingsToEnableNetConnection();
                }
                break;
            case R.id.hajjGuideBtn:
                startActivity(new Intent(MainActivity.this,HajjGuideActivity.class));
                break;
            case R.id.pray:
                startActivity(new Intent(MainActivity.this,PrayActivity.class));
               // Toast.makeText(MainActivity.this, "button clicked", Toast.LENGTH_SHORT).show();
                break;






            case R.id.twaaf_counter:

                Toast.makeText(this, "Twaaf counter clicked", Toast.LENGTH_SHORT).show();

                if (isNetworkAvailable() || isWiFiAvailable()) { // ******************************** first check if mobile data or wifi is enabled
                    if (locationEnabled()) {
                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                    }
                }else {
                    openSettingsToEnableNetConnection();
                }
                break;


            case R.id.extras:
                Toast.makeText(this, "Login clicked", Toast.LENGTH_SHORT).show();

                if (isNetworkAvailable() || isWiFiAvailable()) { // ******************************** first check if mobile data or wifi is enabled
                    if (locationEnabled()) {
//                        startActivity(new Intent(MainActivity.this, com.pakiboy612gmailtest.hajjdost.ui.MainActivity.class));

                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                }else {
                    openSettingsToEnableNetConnection();
                }
                break;








            default:
              startActivity(new Intent(MainActivity.this,HelpActivity.class));
    }
}

    private void getDataAndSendSms() {  // *******************************************************  send sms to all group members who provide number

        getDataFromDatabase();

        SharedPreferences sharedPreferences = getSharedPreferences("PREF_KEY",MODE_PRIVATE);
        String userGroupp = sharedPreferences.getString("userGroup",null);

        Log.d("squence", "getDataAndSendSms: called..");

        myRef.child("Users").orderByChild("groupName").equalTo(userGroupp).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("squence", "onDataChange: called...");

                if (dataSnapshot.exists()) {
                    // User data = new User();
                    List<User> data = new ArrayList<>();
                    Log.d("squence", "onDataChange: if is called...");
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User element = snapshot.getValue(User.class);
                        if (element!=null){
                            data.add(element);
                        }
                    }
                    String usersData = "";
                    numbersList.clear();
                    for (User user : data) {
                        if (user.zphoneNumber != null){
                            numbersList.add(user.zphoneNumber);
                            Log.d("squence", "onDataChange: "+usersData);
                        }

                    }
                    Log.d("squence", "onDataChange: "+numbersList);

                    User uName = new User();

                    SmsManager smsManager = SmsManager.getDefault();
                    String sms = "Hajj Dost\nYour Group Member: "+userName+"\nis call you for Help*";
                    for (String number : numbersList){
                        smsManager.sendTextMessage(number,null,sms,null,null);
                    }


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void openSettingsToEnableNetConnection() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setMessage("Please Enable mobile-data or WiFi to use this function");
        dialog.setPositiveButton(MainActivity.this.getResources().getString(R.string.open_internet_settings), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                //************************************************************************ open GPS setting to Enable GPS Connection
                Intent myIntent = new Intent( Settings.ACTION_SETTINGS);
                MainActivity.this.startActivity(myIntent);
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                paramDialogInterface.cancel();
            }
        });
        dialog.show();
    }

    private void saveHomeLocation() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setMessage("Your Home Location is not selected. To select Home Location, press");
        dialog.setPositiveButton("Set Current Location as Home", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (lat == "0.0" || lon == "0.0") {
                    Toast.makeText(MainActivity.this, "GPS is not avaliable\nPlease try again later", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("latitude",lat);
                    editor.putString("longitude",lon);
                    editor.putString("accuracy",accuracy);
                    editor.commit();
                    Toast.makeText(MainActivity.this, "Home Location save successfully", Toast.LENGTH_SHORT).show();
                    Log.d("latlong", "onClick:save location: Latitude:"+lat+"-Longitude:"+lon+"-accuracy:"+accuracy);
                    dialog.cancel();
                }
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private boolean isHomeLocationSaved() { //*********************************** to check if home location is saved or not
        String latt = sharedPreferences.getString("latitude","0.0");
        String lonn = sharedPreferences.getString("longitude","0.0");
        String accu = sharedPreferences.getString("accuracy","0.0");

        Log.d("latlong", "isHomeLocationSaved: "+latt+" "+lonn);

        if (latt == "0.0" || lonn == "0.0") {
            Log.d("latlong", "isHomeLocationSaved: return = false");
            return false;
        } else {
            Log.d("latlong", "isHomeLocationSaved: return = true");
            return true;
        }
    }

    public void locationUpdate(){
    LocationRequest request = new LocationRequest();
//*****************************************************time interval between Location requests
    request.setInterval(5000);
//*****************************************************Get the most accurate location data available
    request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
    //final String path = getString(R.string.firebase_path);
    int permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION);
//*******************************************************request location updates
        client.requestLocationUpdates(request, new LocationCallback() {
           // String userRKeyy = sharedPreferences.getString("userKey",null);
            @Override
            public void onLocationResult(LocationResult locationResult) {

//Get a reference to the database, so your app can perform read and write operations//

//                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference(path);
                Location location = locationResult.getLastLocation();
                if (location != null) {

//Save the location data to the database//

                    //                       ref.setValue(location);
                    // Log.d("location", "onLocationResult: "+location);
                   // Double ac = Double.parseDouble(new Float(location.getAccuracy()).toString());

                    lat = myLatLang.getString("myLat","0.0");
                    lon = myLatLang.getString("myLang","0.0");

                    Log.d("locationupdate", "onLocationResult: "+lat+" "+lon);




                    //User user = new User(userName,userAddress,userGroup,userGender,userAge);

                  //  myRef.child("Users").child(userRKeyy).setValue(user);

//                    String uusseerr = "-LDNZJJwiMlyW9b_OS12";
//                    myRef.child("Users").child(uusseerr).child("latitudeee").setValue(lat);
//                    myRef.child("Users").child(uusseerr).child("longitudeee").setValue(lon);

//                    myRef.child("Users").child(userRKeyy).child("zzlatitudeee").setValue(lat);
//                    myRef.child("Users").child(userRKeyy).child("zzlongitudeee").setValue(lon);
//                    myRef.child("Users").child(userRKeyy).child("zzaccuracy").setValue(accuracy);


                    Log.d("latlong", "Lat: "+location.getLatitude()+" Long: "+ location.getLongitude()+"accuracy: "+location.getAccuracy());
                }
            }
        }, null);
//    }
}

    private void getDataFromDatabase() {
        String userRKeey = sharedPreferences.getString("userKey",null);
        Log.d("prefKey", "getDataFromDatabase: "+userRKeey);
        try{
            myRef.child("Users").child(userRKeey).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        // User data = new User();
                        List<String> data = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            String element = snapshot.getValue(String.class);
                            String element = snapshot.getValue(String.class);
                            data.add(element);
                            Log.d("snapshot", "in Loop: "+data);
                        }
                        Log.d("snapshot", "out of Loop: "+data);
                        for (int i =0; i<data.size();i++) {
                            switch (i) {
                                case 0:
                                    userAddress = data.get(i).toString();
                                    break;
                                case 1:
                                    userAge = data.get(i).toString();
                                    break;
                                case 2:
                                    userGender = data.get(i).toString();
                                    break;
                                case 3:
                                    userGroup = data.get(i).toString();
                                    break;
                                case 4:
                                    userName = data.get(i).toString();
                                default:
                                    userNumber = data.get(i).toString();
                            }
                        }
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private boolean isWiFiAvailable() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifi.isConnected();
    }


    private boolean isNetworkAvailable() {  // ***************************************************** check if mobile data or wifi is enabled
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        NetworkInfo isWiFiEnabled = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

//        if (activeNetworkInfo!=null && activeNetworkInfo.isConnected() || isWiFiEnabled.isConnected()) {
//            Log.d("iamhere", "isNetworkAvailable: if is called");
//        } else {
//        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() || isWiFiEnabled.isConnected();
    }


    private boolean locationEnabled() {
        LocationManager lm = (LocationManager)MainActivity.this.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled) { // if GPS is not enabled then ask to enable GPS to use app
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setMessage("Please Enable GPS connection to use app");
            dialog.setPositiveButton(MainActivity.this.getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    //************************************************************************ open GPS setting to Enable GPS Connection
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    MainActivity.this.startActivity(myIntent);
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

    public static boolean isSimSupport(Context context)
    {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);  //gets the current TelephonyManager
        return !(tm.getSimState() == TelephonyManager.SIM_STATE_ABSENT);

    }
}
