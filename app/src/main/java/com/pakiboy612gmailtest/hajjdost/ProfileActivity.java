package com.pakiboy612gmailtest.hajjdost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    TextView name_tv, age_tv, gender_tv, address_tv, group_tv, phoneNum_tv;

    String uName,uAge,uGender,uAddress,uGroup;

    DatabaseReference databaseReference;
    String userRKey, userData;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AdView mAdView = findViewById(R.id.adViewProfile);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        name_tv = findViewById(R.id.name_tv);
        age_tv = findViewById(R.id.age_tv);
        gender_tv = findViewById(R.id.gender_tv);
        address_tv = findViewById(R.id.address_tv);
        phoneNum_tv = findViewById(R.id.phNum_tv);
        group_tv = findViewById(R.id.group_tv);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        SharedPreferences sharedPreferences = getSharedPreferences("PREF_KEY",MODE_PRIVATE);
        userRKey = sharedPreferences.getString("userKey",null);

        Log.d("userkey", "onCreate: "+userRKey);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        getDataFromDatabase();

        findViewById(R.id.edit_profile_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });


    }

    private void getDataFromDatabase() {
        myRef.child("Users").child(userRKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                   // User data = new User();
                    List<String> data = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String element = snapshot.getValue(String.class);
                        data.add(element);
                        Log.d("snapshot", "in Loop: "+data);
                    }
                    Log.d("snapshot", "out of Loop: "+data);
                    for (int i =0; i<data.size();i++) {
                        switch (i) {
                            case 0:
                                address_tv.setText(data.get(i));
                                break;
                            case 1:
                                age_tv.setText(data.get(i));
                                break;
                            case 2:
                                gender_tv.setText(data.get(i));
                                break;
                            case 3:
                                group_tv.setText(data.get(i));
                                break;
                            case 4:
                                    name_tv.setText(data.get(i));
                                    break;
                            default:
                                phoneNum_tv.setText(data.get(i));
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
