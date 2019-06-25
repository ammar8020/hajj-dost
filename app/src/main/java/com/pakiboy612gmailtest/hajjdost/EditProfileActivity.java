package com.pakiboy612gmailtest.hajjdost;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

   private Context context = EditProfileActivity.this;
   private String fullName,age,address,group,gender,phoneNum;
   private RadioGroup radioGroup;
   private AutoCompleteTextView groupET;
   private String  groupInput,groupString ;
   private List<String> groupsList;

   private EditText nameET,addressET,ageET, phoneNumET;

    private DatabaseReference myRef;

    private SharedPreferences userKey;
    private String userRKey;

    private int setdata = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        AdView mAdView = findViewById(R.id.adViewEditProfile);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        nameET = findViewById(R.id.full_name_ETT);
        addressET = findViewById(R.id.address_ETT);
        ageET =  findViewById(R.id.age_ETT);
        groupET = findViewById(R.id.group_name_ETT);
        phoneNumET = findViewById(R.id.number_ETT);

        radioGroup = findViewById(R.id.radio_groupT);
        // getSelectedGender();  //****************************************************************** function call to get user's gender.


        findViewById(R.id.create_groupT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatNewGroup(); //***************************************************************** function call to create new group
            }
        });

        myRef = FirebaseDatabase.getInstance().getReference();

        findViewById(R.id.update_btnT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserInDataBase();
            }
        });

        suggestGroupAutoFillET();
        SharedPreferences sharedPreferences = getSharedPreferences("PREF_KEY",MODE_PRIVATE);
        userRKey = sharedPreferences.getString("userKey",null);
        getDataFromDatabase();
    }

    public void creatNewGroup() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View popUp = inflater.inflate(R.layout.create_group_popup, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(popUp);

        final EditText newGroupNameInputET = popUp.findViewById(R.id.editTextDialogUserInput); //**** EditText at alert dialog to get new group name

        // set dialog message
        alertDialogBuilder.setCancelable(false).setPositiveButton("Create",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        groupInput = newGroupNameInputET.getText().toString();

                        if (newGroupNameInputET.getText().toString().isEmpty()){
                            dialog.cancel();
                            creatNewGroup();
                            Toast.makeText(context, "Enter Group Name or Cancel", Toast.LENGTH_LONG).show();

                        } else if (groupsList.contains(groupInput)) {   //****************************************** check if group name already exist
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
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    private void getSelectedGender(){
        int selectedRB = radioGroup.getCheckedRadioButtonId();
        if (selectedRB == findViewById(R.id.male_RBT).getId()){
            gender = "male";
        }else if (selectedRB == findViewById(R.id.fe_male_RBT).getId()){
            gender = "female";
        }else {
            gender = "other";
        }
    }


    private void suggestGroupAutoFillET() {
        myRef.child("Group").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                groupsList = new ArrayList<>();
                if (dataSnapshot.exists()){
                    int i =0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        // Log.d("dataSnapshot", "onDataChange: "+snapshot);
                        String group = snapshot.getValue().toString();
                        groupsList.add(group);
                    }
                    Log.d("dataToSuggest", "onDataChange: "+groupsList);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,groupsList);
                    groupET.setThreshold(1);
                    groupET.setAdapter(adapter);
                }

                Log.d("groupString", "onDataChange: "+groupString);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addUserInDataBase() {
        fullName = nameET.getText().toString();
        age =ageET.getText().toString();
        address = addressET.getText().toString();
        phoneNum = phoneNumET.getText().toString();
        group = groupET.getText().toString();
        getSelectedGender();
        String groupInputFinal = groupET.getText().toString();
        //String userName,String address, String groupName , String gender , Double age
        if (fullName.isEmpty() || age.isEmpty() || address.isEmpty() || group.isEmpty() || phoneNum.isEmpty()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_LONG).show();
        } else if (groupsList.contains(groupInputFinal)) {

            User user = new User(fullName, address, group, gender, age, phoneNum);
           // String key = myRef.push().getKey();

            userKey = getSharedPreferences("PREF_KEY",MODE_PRIVATE); //************************ Store User Key in SharedPreferences
            SharedPreferences.Editor editor = userKey.edit();
            editor.putString("userGroup",group);
            editor.commit();

            myRef.child("Users").child(userRKey).setValue(user);
            myRef.child("Latlong").child(userRKey).child("userNames").setValue(fullName);
            Toast.makeText(context, "successfully updated", Toast.LENGTH_SHORT).show();
            setUserLoggedIn();
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
           // finish();
        } else {
            Toast.makeText(context, "invalid Group name...!\nCreate new group or Select 'valid group name' ", Toast.LENGTH_LONG).show();
        }
    }
    private void setUserLoggedIn() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply();
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
                        Log.d("setData", "befor set data "+setdata);
                        if (setdata == 0) {
                            for (int i =0; i<data.size();i++) {
                                switch (i) {
                                    case 0:
                                        addressET.setText(data.get(i));
                                        break;
                                    case 1:
                                        ageET.setText(data.get(i));
                                        break;
                                    case 2:
                                        // gender_tv.setText(data.get(i));
                                        break;
                                    case 3:
                                        groupET.setText(data.get(i));
                                        break;
                                    case 4:
                                        nameET.setText(data.get(i));
                                        break;
                                    default:
                                        phoneNumET.setText(data.get(i));
                                }
                            }
                            setdata=11;
                            Log.d("setData", "onDataChange: new Value "+setdata);
                        }
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
}
