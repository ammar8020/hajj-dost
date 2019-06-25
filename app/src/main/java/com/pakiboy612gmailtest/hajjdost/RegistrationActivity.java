package com.pakiboy612gmailtest.hajjdost;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pakiboy612gmailtest.hajjdost.ui.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    Context context = RegistrationActivity.this;
    String fullName, age, address, group, gender, phoneNumber;
    RadioGroup radioGroup;
    AutoCompleteTextView groupET;
    String groupInput, groupString;
    List<String> groupsList;

    EditText nameET, addressET, ageET, phoneNumberET;

    DatabaseReference myRef;

    SharedPreferences userKey;

    String lat = "0.0";
    String lon = "0.0";
    String accuracy = "0.0";


    Button btnChatroom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        AdView mAdView = findViewById(R.id.adViewRegistration);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        nameET = findViewById(R.id.full_name_ET);
        addressET = findViewById(R.id.address_ET);
        ageET = findViewById(R.id.age_ET);
        groupET = findViewById(R.id.group_name_ET);
        phoneNumberET = findViewById(R.id.phoneNum_ET);


        radioGroup = findViewById(R.id.radio_group);


        btnChatroom = findViewById(R.id.btn_chatroom);


        btnChatroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

            }
        });


        // getSelectedGender();  //****************************************************************** function call to get user's gender.


        suggestGroupAutoFillET(); // to load list of all groups names

        findViewById(R.id.create_group).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatNewGroup(); //***************************************************************** function call to create new group
            }
        });

        myRef = FirebaseDatabase.getInstance().getReference();

        findViewById(R.id.register_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addUserInDataBase();
                } catch (Exception e) {
                    Toast.makeText(context, "Please connect to internet..!!", Toast.LENGTH_LONG).show();
                    Log.d("exception", "onClick: "+e.toString());
                }


            }
        });

    }

    public void creatNewGroup() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View popUp = inflater.inflate(R.layout.create_group_popup, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(popUp);

        final EditText newGroupNameInputET = popUp.findViewById(R.id.editTextDialogUserInput); //**** EditText at alert dialog to get new group name

        // set dialog message
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Create",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                groupInput = newGroupNameInputET.getText().toString();

                                if (newGroupNameInputET.getText().toString().isEmpty()) {    // one minut let me check my code...ok
                                    dialog.cancel();
                                    creatNewGroup();
                                    Toast.makeText(context, "Enter Group Name or Cancel", Toast.LENGTH_LONG).show();

                                    //groupsList.contains(groupInput)

                                } else if (1 == 2) {   //****************************************** check if group name already exist
                                    Toast.makeText(context, "Group name is already exist. try an other group name", Toast.LENGTH_LONG).show();
                                    dialog.cancel();
                                    creatNewGroup();
                                } else {
                                    groupET.setText(groupInput);
                                    myRef.child("Group").child(groupInput).setValue(groupInput);
                                    Toast.makeText(context, groupInput + ": created successfully", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    private void getSelectedGender() {
        int selectedRB = radioGroup.getCheckedRadioButtonId();
        if (selectedRB == findViewById(R.id.male_RB).getId()) {
            gender = "male";
        } else if (selectedRB == findViewById(R.id.fe_male_RB).getId()) {
            gender = "female";
        } else {
            gender = "other";
        }
    }


    private void suggestGroupAutoFillET() {
        myRef.child("Group").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                groupsList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    int i = 0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Log.d("dataSnapshot", "onDataChange: "+snapshot);
                        String group = snapshot.getValue().toString();
                        groupsList.add(group);
                    }
                    Log.d("dataToSuggest", "onDataChange: " + groupsList);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegistrationActivity.this, android.R.layout.simple_dropdown_item_1line, groupsList);
                    groupET.setThreshold(1);
                    groupET.setAdapter(adapter);
                }

                Log.d("groupString", "onDataChange: " + groupString);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addUserInDataBase() {
        fullName = nameET.getText().toString();
        age = ageET.getText().toString();
        address = addressET.getText().toString();
        phoneNumber = phoneNumberET.getText().toString();
        group = groupET.getText().toString();
        getSelectedGender();
        String groupInputFinal = groupET.getText().toString();
        //String userName,String address, String groupName , String gender , Double age

        if (fullName.isEmpty() || age.isEmpty() || address.isEmpty() || group.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_LONG).show();
        } else if (groupsList.contains(groupInputFinal)) {
            User user = new User(fullName, address, group, gender, age, phoneNumber);
            String key = myRef.push().getKey();

            userKey = getSharedPreferences("PREF_KEY", MODE_PRIVATE); //************************ Store User Key and group in SharedPreferences
            SharedPreferences.Editor editor = userKey.edit();
            editor.putString("userKey", key);
            editor.putString("userGroup", group);
            editor.commit();

            Log.d("checkTag", "addUserInDataBase: going to put data on FB database");

            myRef.child("Users").child(key).setValue(user);
            myRef.child("Latlong").child(key).child("userNames").setValue(fullName);
            Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show();
            setUserLoggedIn();
            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(context, "invalid Group name...!\nCreate new group or Select 'valid group name' ", Toast.LENGTH_LONG).show();
        }
    }

    private void setUserLoggedIn() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply();
    }

}
