package com.pakiboy612gmailtest.hajjdost;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class GroupListActivity extends AppCompatActivity {

    String userGroup;
    ArrayList<String> groupMembers;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);


        groupMembers = new ArrayList<>();

        AdView mAdView = findViewById(R.id.adViewGroupList);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        SharedPreferences sharedPreferences = getSharedPreferences("PREF_KEY",MODE_PRIVATE);
        userGroup = sharedPreferences.getString("userGroup",null);
        Log.d("userGroup", "onCreate: "+userGroup);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        getDataFromDatabase();

    }

    private void getDataFromDatabase() {
        myRef.child("Users").orderByChild("groupName").equalTo(userGroup).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User data = new User();
                    List<User> data = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User element = snapshot.getValue(User.class);
                        data.add(element);
                        Log.d("snapshot", "in Loop: "+data);
                    }
                    Log.d("snapshot", "out of Loop: "+data);

                    String usersData = "";
                    groupMembers.clear();
                    for (User user : data) {

                        String element = user.userName;
                        groupMembers.add(element);
                        Log.d("usersData", "onDataChange: "+usersData);
                    }
                    Log.d("groupMembers", "onDataChange: "+groupMembers);

                    TextView groupMembersTV = findViewById(R.id.group_members_TV);
                    groupMembersTV.setText("Group members ("+groupMembers.size()+")");
                    reFreshGroupList();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void reFreshGroupList() {
        RecyclerView recyclerView = findViewById(R.id.group_list_RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(GroupListActivity.this));
        GroupMembersRVadapter rVadapter = new GroupMembersRVadapter(GroupListActivity.this,groupMembers);
        recyclerView.setAdapter(rVadapter);
    }

    public void re_fresh_GL(View view) {
        reFreshGroupList();
    }
}
