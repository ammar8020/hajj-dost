package com.pakiboy612gmailtest.hajjdost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LeadingActivity extends AppCompatActivity {

    private int extra;
    private String currentPlace;

    TextView leadingTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leading);

        leadingTV = findViewById(R.id.descriptionTV);

        Bundle extras = getIntent().getExtras();
        extra = extras.getInt("code",0);
        switch (extra){
            case 1:
                currentPlace = "Al-Haram";
                setText(currentPlace);
                break;
            case 2:
                currentPlace = "Masjid-Aisha";
                setText(currentPlace);
                break;
            case 3:
                currentPlace = "Masjid-Jinn";
                setText(currentPlace);
                break;
            case 4:
                currentPlace = "Masjid-bilal";
                setText(currentPlace);
                break;
            case 5:
                currentPlace = "Masjid-al-khaif";
                setText(currentPlace);
                break;
            case 6:
                currentPlace = "Muzdalifa";
                setText(currentPlace);
                break;
            case 7:
                currentPlace = "jamarat";
                setText(currentPlace);
                break;
            case 8:
                currentPlace = "Mount-abu-qubais";
                setText(currentPlace);
                break;
            case 9:
                currentPlace = "Birth-place";
                setText(currentPlace);
                break;
            case 10:
                currentPlace = "cave-of-hira";
                setText(currentPlace);
                break;
            case 11:
                currentPlace = "cave-of-thor";
                setText(currentPlace);
                break;
            case 12:
                currentPlace = "jabal-e-noor";
                setText(currentPlace);
                break;
            case 13:
                currentPlace = "jannat-mualla";
                setText(currentPlace);
                break;
            case 14:
                currentPlace = "jabal-e-rahma";
                setText(currentPlace);
                break;
            case 15:
                currentPlace = "Masjid-nabvi";
                setText(currentPlace);
                break;
            case 16:
                currentPlace = "Masjid-Quba";
                setText(currentPlace);
                break;
            case 17:
                currentPlace = "Masjid-Qiblatain";
                setText(currentPlace);
                break;
            case 18:
                currentPlace = "Masjid-juma";
                setText(currentPlace);
                break;
            case 19:
                currentPlace = "Masjid-al-bilal";
                setText(currentPlace);
                break;
            case 20:
                currentPlace = "Masjid-AbuBakar";
                setText(currentPlace);
                break;
            case 23:
                currentPlace = "Bir-e-usman";
                setText(currentPlace);
                break;
            case 24:
                currentPlace = "Bir-e-Ali";
                setText(currentPlace);
                break;
            case 25:
                currentPlace = "Site of khandak";
                setText(currentPlace);
                break;
            case 27:
                currentPlace = "mountain of heaven and hell";
                setText(currentPlace);
                break;
            case 28:
                currentPlace = "wadi-e-jin";
                setText(currentPlace);
                break;
                default:
        }
    }

    private void setText(String a){
        String leadingDescription = "Now you reached at '"+a+"'.\n For more detail about this place, Goto Home screen and press 'Holly Places' then select city in wich you are. then select your current place to read more.\nThanks";
        leadingTV.setText(leadingDescription);
    }
}