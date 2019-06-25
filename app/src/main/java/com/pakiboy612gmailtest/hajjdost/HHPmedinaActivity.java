package com.pakiboy612gmailtest.hajjdost;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class HHPmedinaActivity extends AppCompatActivity implements View.OnClickListener {

    Context context = HHPmedinaActivity.this;

    TextView malharm_M, maisha_M, mjin_M, mbilal_M, malKhayf_M, mmuzdilfa, mjamaraat, mmountQais, mbirthPlace, mcaveHira, mcaveThour, mjabalNoor, mjannatAlmualla, mjamalAlrahmah;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hhpmadina);

        AdView mAdView = findViewById(R.id.adViewHHPmadina);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        malharm_M = findViewById(R.id.masjid_al_haramm);
        maisha_M = findViewById(R.id.masjid_aishaa);
        mjin_M = findViewById(R.id.masjid_jinn);
        mbilal_M = findViewById(R.id.masjid_bilall);
        malKhayf_M = findViewById(R.id.masjid_al_khayff);
        mmuzdilfa = findViewById(R.id.muzdalifahh);
        mjamaraat = findViewById(R.id.jamaratt);
        mmountQais = findViewById(R.id.mount_abu_qubaiss);
        mbirthPlace = findViewById(R.id.birth_placee);
        mcaveHira = findViewById(R.id.cave_hiraa);
        mcaveThour = findViewById(R.id.cave_thaurr);
        mjabalNoor = findViewById(R.id.jabal_nourr);
        mjannatAlmualla = findViewById(R.id.janat_almuallaa);
        mjamalAlrahmah = findViewById(R.id.jamal_alrahmahh);

        malharm_M.setOnClickListener(this);
        maisha_M.setOnClickListener(this);
        mjin_M.setOnClickListener(this);
        mbilal_M.setOnClickListener(this);
        malKhayf_M.setOnClickListener(this);
        mmuzdilfa.setOnClickListener(this);
        mjamaraat.setOnClickListener(this);
        mmountQais.setOnClickListener(this);
        mbirthPlace.setOnClickListener(this);
        mcaveHira.setOnClickListener(this);
        mcaveThour.setOnClickListener(this);
        mjabalNoor.setOnClickListener(this);
        mjannatAlmualla.setOnClickListener(this);
        mjamalAlrahmah.setOnClickListener(this);
        intent = new Intent(HHPmedinaActivity.this,HHPdetailActivity.class);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.masjid_al_haramm:
                intent.putExtra("value",21);
                startActivity(intent);
                break;
            case R.id.masjid_aishaa:
                intent.putExtra("value",22);
                startActivity(intent);
                break;
            case R.id.masjid_jinn:
                intent.putExtra("value",23);
                startActivity(intent);
                break;
            case R.id.masjid_bilall:
                intent.putExtra("value",24);
                startActivity(intent);
                break;
            case R.id.masjid_al_khayff:
                intent.putExtra("value",25);
                startActivity(intent);
                break;
            case R.id.muzdalifahh:
                intent.putExtra("value",26);
                startActivity(intent);
                break;
            case R.id.jamaratt:
                intent.putExtra("value",27);
                startActivity(intent);
                break;
            case R.id.mount_abu_qubaiss:
                intent.putExtra("value",28);
                startActivity(intent);
                break;
            case R.id.birth_placee:
                intent.putExtra("value",29);
                startActivity(intent);
                break;
            case R.id.cave_hiraa:
                intent.putExtra("value",30);
                startActivity(intent);
                break;
            case R.id.cave_thaurr:
                intent.putExtra("value",31);
                startActivity(intent);
                break;
            case R.id.jabal_nourr:
                intent.putExtra("value",32);
                startActivity(intent);
                break;
            case R.id.janat_almuallaa:
                intent.putExtra("value",33);
                startActivity(intent);
                break;
            default:
                intent.putExtra("value",34);
                startActivity(intent);
        }
    }
}
