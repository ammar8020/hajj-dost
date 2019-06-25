package com.pakiboy612gmailtest.hajjdost;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class HajjGuideActivity extends AppCompatActivity {

    TextView stepOne, stepTwo, stepThree, stepFour, stepFive, stepSix, stepSeven, stepEight,
            stepNine, stepTen, stepEleven, stepTwelve, stepThirteen, stepFourteen, stepFifteen,
            stepsisteen,stepseventeen,stepeighteen,stepninteen,steptwenty,steptwentyone;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hajj_guide);

        AdView mAdView = findViewById(R.id.adViewHajjGuide);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        intent = new Intent(this,HajjGuidDetailActivity.class);

        stepOne = findViewById(R.id.step_one);
        stepOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",1);
                startActivity(intent);
            }
        });
        stepTwo = findViewById(R.id.step_two);
        stepTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",2);
                startActivity(intent);
            }
        });
        stepThree = findViewById(R.id.step_three);
        stepThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",3);
                startActivity(intent);
            }
        });
        stepFour = findViewById(R.id.step_four);
        stepFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",4);
                startActivity(intent);
            }
        });
        stepFive = findViewById(R.id.step_five);
        stepFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",5);
                startActivity(intent);
            }
        });
        stepSix = findViewById(R.id.step_six);
        stepSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",6);
                startActivity(intent);
            }
        });
        stepSeven = findViewById(R.id.step_seven);
        stepSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",7);
                startActivity(intent);
            }
        });
        stepEight = findViewById(R.id.step_eight);
        stepEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",8);
                startActivity(intent);
            }
        });
        stepNine = findViewById(R.id.step_nine);
        stepNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",9);
                startActivity(intent);
            }
        });
        stepTen = findViewById(R.id.step_ten);
        stepTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",10);
                startActivity(intent);
            }
        });
        stepEleven = findViewById(R.id.step_eleven);
        stepEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",11);
                startActivity(intent);
            }
        });
        stepTwelve = findViewById(R.id.step_twelve);
        stepTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",12);
                startActivity(intent);
            }
        });
        stepThirteen = findViewById(R.id.step_thirteen);
        stepThirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",13);
                startActivity(intent);
            }
        });
        stepFourteen = findViewById(R.id.step_fourteen);
        stepFourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",14);
                startActivity(intent);
            }
        });
        stepFifteen = findViewById(R.id.step_fifteen);
        stepFifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",15);
                startActivity(intent);
            }
        });
        stepsisteen = findViewById(R.id.step_sisteen);
        stepsisteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",16);
                startActivity(intent);
            }
        });
        stepseventeen = findViewById(R.id.step_seventeen);
        stepseventeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",17);
                startActivity(intent);
            }
        });
        stepeighteen = findViewById(R.id.step_eightteen);
        stepeighteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",18);
                startActivity(intent);
            }
        });
        stepninteen = findViewById(R.id.step_nineteen);
        stepninteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",19);
                startActivity(intent);
            }
        });
        steptwenty = findViewById(R.id.step_twenty);
        steptwenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",20);
                startActivity(intent);
            }
        });
        steptwentyone = findViewById(R.id.step_twentyone);
        steptwentyone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("clickedvalue",21);
                startActivity(intent);
            }
        });
    }
}


