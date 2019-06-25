package com.pakiboy612gmailtest.hajjdost;

import android.Manifest;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateLatLangService extends Service {


    public String userName, userAddress, userAge, userGender, userGroup;
    private String myGroup;

    double lat = 0.0;
    double lon = 0.0;
    double accuracy = 0.0;

    DatabaseReference myRef;

    SharedPreferences sharedPreferences;

    LocationManager locationManager;

    //********Constructor of this class
    public UpdateLatLangService() {
    }

    //*********called when the instance of this class created
    //********* or when the service start
    @Override
    public void onCreate() {
        super.onCreate();
        locationManager = (LocationManager) UpdateLatLangService.this.getSystemService(LOCATION_SERVICE);
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


        sharedPreferences = getSharedPreferences("PREF_KEY",MODE_PRIVATE);



/*




        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%**%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        // -----------HHP of mecca
        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%**%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent1 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent1.putExtra("code",1);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent1,0);//Al-Haram
        locationManager.addProximityAlert(21.422483, 39.8256657,60.00f,-1, pendingIntent1 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent2 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent2.putExtra("code",2);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent2,0);//Masjid-Aisha
        locationManager.addProximityAlert(21.4675481, 39.8011762,30.00f,-1, pendingIntent2 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent3 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent3.putExtra("code",3);
        PendingIntent pendingIntent3 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent3,0);//Masjid-Jinn
        locationManager.addProximityAlert(21.4334308, 39.8286787,10.00f,-1, pendingIntent3 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent4 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent4.putExtra("code",4);
        PendingIntent pendingIntent4 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent4,0);//Masjid-bilal
        locationManager.addProximityAlert(21.4561798,39.8610589, 10.00f,-1, pendingIntent4 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent5 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent5.putExtra("code",5);
        PendingIntent pendingIntent5 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent5,0);//Masjid-al-khaif
        locationManager.addProximityAlert(21.4146615,39.878514,50.00f,-1, pendingIntent5 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent6 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent6.putExtra("code",6);
        PendingIntent pendingIntent6 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent6,0);//Muzdalifa
        locationManager.addProximityAlert(21.3999302,39.8768512,1000.00f,-1, pendingIntent6 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent7 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent7.putExtra("code",7);
        PendingIntent pendingIntent7 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent7,0);//jamarat
        locationManager.addProximityAlert(21.4204426,39.870471,350.00f,-1, pendingIntent7 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent8 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent8.putExtra("code",8);
        PendingIntent pendingIntent8 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent8,0);//Mount-abu-qubais
        locationManager.addProximityAlert(21.4293505,39.819916,150.00f,-1, pendingIntent8 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent9 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent9.putExtra("code",9);
        PendingIntent pendingIntent9 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent9,0);//Birth-place
        locationManager.addProximityAlert(21.4248752,39.8292718,20.00f,-1, pendingIntent9 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent10 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent10.putExtra("code",10);
        PendingIntent pendingIntent10 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent10,0);//cave-of-hira
        locationManager.addProximityAlert(21.4575864,39.8587284,10.00f,-1, pendingIntent10 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent11 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent11.putExtra("code",11);
        PendingIntent pendingIntent11 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent11,0);//cave-of-thor
        locationManager.addProximityAlert(21.3771902,39.8492092,20.00f,-1, pendingIntent11 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent12 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent12.putExtra("code",12);
        PendingIntent pendingIntent12 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent12,0);//jabal-e-noor
        locationManager.addProximityAlert(21.4566172,39.8565838,10.00f,-1, pendingIntent12 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent13 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent13.putExtra("code",13);
        PendingIntent pendingIntent13 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent13,0);//jannat-mualla
        locationManager.addProximityAlert(21.4332152,39.8277688,15.00f,-1, pendingIntent13 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent14 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent14.putExtra("code",14);
        PendingIntent pendingIntent14 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent14,0);//jabal-e-rahma
        locationManager.addProximityAlert(21.375682,39.9106632,160.00f,-1, pendingIntent14 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%**%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        //------HHP of Medina...
        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%**%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent15 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent15.putExtra("code",15);
        PendingIntent pendingIntent15 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent15,0);//Masjid-nabvi
        locationManager.addProximityAlert(24.4688839,39.6089143,300.00f,-1, pendingIntent15 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent16 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent16.putExtra("code",16);
        PendingIntent pendingIntent16 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent16,0);//Masjid-Quba
        locationManager.addProximityAlert(24.4393542,39.6166848,50.00f,-1, pendingIntent16 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent17 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent17.putExtra("code",17);
        PendingIntent pendingIntent17 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent17,0);//Masjid-Qiblatain
        locationManager.addProximityAlert(24.4841312,39.5782978,50.00f,-1, pendingIntent17 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent18 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent18.putExtra("code",18);
        PendingIntent pendingIntent18 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent18,0);//Masjid-juma
        locationManager.addProximityAlert(24.4454542,39.6146787,30.00f,-1, pendingIntent18 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent19 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent19.putExtra("code",19);
        PendingIntent pendingIntent19 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent19,0);//Masjid-al-bilal
        locationManager.addProximityAlert(24.4614972,39.6112088,40.00f,-1, pendingIntent19 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent20 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent20.putExtra("code",20);
        PendingIntent pendingIntent20 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent20,0);//Masjid-AbuBakar
        locationManager.addProximityAlert(24.4661672,39.6057918,20.00f,-1, pendingIntent20 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        // %%%%% ROZA e Rasol is in masjid nabvi..alert for masjid nabvi is already done.
//        Intent intent21 = new Intent(UpdateLatLangService.this,PrayActivity.class);
////        intent21.putExtra("code",21);
////        PendingIntent pendingIntent21 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent21,0);//Roza-e-rasool
////        locationManager.addProximityAlert(31.9620458, 72.4110000,10.00f,-1, pendingIntent21 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        // %%%%% this place is also part of Masjid nabvi
//        Intent intent22 = new Intent(UpdateLatLangService.this,PrayActivity.class);
//        intent22.putExtra("code",22);
//        PendingIntent pendingIntent22 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent22,0);//Riaaz-zu-jannah
//        locationManager.addProximityAlert(31.9620458, 72.4110000,10.00f,-1, pendingIntent22 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent23 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent23.putExtra("code",23);
        PendingIntent pendingIntent23 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent23,0);//Bir-e-usman
        locationManager.addProximityAlert(24.4932454,39.5817018,50.00f,-1, pendingIntent23 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent24 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent24.putExtra("code",24);
        PendingIntent pendingIntent24 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent24,0);//Bir-e-Ali
        locationManager.addProximityAlert(24.4130307,39.5415767,150.00f,-1, pendingIntent24 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent25 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent25.putExtra("code",25);
        PendingIntent pendingIntent25 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent25,0);//Site of khandak
        locationManager.addProximityAlert(24.4939202,39.5119936,300.00f,-1, pendingIntent25 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        //%%%%%%%%%  this place is also near to masjid nabvi..
//        Intent intent26 = new Intent(UpdateLatLangService.this,PrayActivity.class);
//        intent26.putExtra("code",26);
//        PendingIntent pendingIntent26 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent26,0);//janat-ul-baqi
//        locationManager.addProximityAlert(31.9620458, 72.4110000,10.00f,-1, pendingIntent26 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent27 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent27.putExtra("code",27);
        PendingIntent pendingIntent27 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent27,0);//mountain of heaven and hell
        locationManager.addProximityAlert(24.4710127,39.4752813,500.00f,-1, pendingIntent27 );

        //*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*
        Intent intent28 = new Intent(UpdateLatLangService.this,LeadingActivity.class);
        intent1.putExtra("code",28);
        PendingIntent pendingIntent28 = PendingIntent.getActivity(UpdateLatLangService.this,0,intent28,0);// wadi-e-jinn
        locationManager.addProximityAlert(24.5364299,39.2463513,1200.00f,-1, pendingIntent28 );





*/



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        sharedPreferences = getSharedPreferences("PREF_KEY",MODE_PRIVATE);
        myRef = FirebaseDatabase.getInstance().getReference();

        // return super.onStartCommand(intent, flags, startId);

        locationUpdate();
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void locationUpdate(){
        LocationRequest request = new LocationRequest();
//*****************************************************time interval between Location requests
        request.setInterval(2000);
//*****************************************************Get the most accurate location data available
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        //final String path = getString(R.string.firebase_path);
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        //*******************************************************************************************************request location updates
        client.requestLocationUpdates(request, new LocationCallback() {
            String userRKeyy = sharedPreferences.getString("userKey",null);
            @Override
            public void onLocationResult(LocationResult locationResult) {

                Location location = locationResult.getLastLocation();
                if (location != null) {
//************************************************************************************************** Save the location data to the database

                    myGroup = sharedPreferences.getString("userGroup",null);
                    //                       ref.setValue(location);
                    // Log.d("location", "onLocationResult: "+location);
                    //Double ac = Double.parseDouble(new Float().toString());
                    lat = location.getLatitude();
                    lon = location.getLongitude();
                    accuracy = location.getAccuracy();




                  //  User user = new User(userName,userAddress,userGroup,userGender,userAge);

                    //  myRef.child("Users").child(userRKeyy).setValue(user);

//                    String uusseerr = "-LDNZJJwiMlyW9b_OS12";
//                    myRef.child("Users").child(uusseerr).child("latitudeee").setValue(lat);
//                    myRef.child("Users").child(uusseerr).child("longitudeee").setValue(lon);

                    myRef.child("Latlong").child(userRKeyy).child("latitudeee").setValue(lat);
                    myRef.child("Latlong").child(userRKeyy).child("longitudeee").setValue(lon);
                    //myRef.child("Latlong").child(userRKeyy).child("accuracy").setValue(accuracy);
                    myRef.child("Latlong").child(userRKeyy).child("myGroupis").setValue(myGroup);

                    SharedPreferences myPrefrence = getSharedPreferences("myLatLang",MODE_PRIVATE);
                    SharedPreferences.Editor editor = myPrefrence.edit();

                    editor.putString("myLat",String.valueOf(lat));
                    editor.putString("myLang",String.valueOf(lon));
                    editor.putString("myAcc",String.valueOf(accuracy));
                    editor.commit();


                    Log.d("inService", "in service live Lat: "+lat+" Long: "+lon+"accuracy: "+accuracy);
                }
            }
        }, null);
//    }
    }

}
