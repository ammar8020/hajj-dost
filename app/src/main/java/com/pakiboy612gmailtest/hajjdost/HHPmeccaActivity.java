package com.pakiboy612gmailtest.hajjdost;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class HHPmeccaActivity extends AppCompatActivity implements View.OnClickListener{

    Context context = HHPmeccaActivity.this;

    TextView alharm_M,aisha_M,jin_M,bilal_M,alKhayf_M,muzdilfa,jamaraat,mountQais,birthPlace,caveHira,caveThour,jabalNoor,jannatAlmualla,jamalAlrahmah;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hhpmakkah);

        AdView mAdView = findViewById(R.id.adViewHHPmakkeh);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        alharm_M = findViewById(R.id.masjid_al_haramm);
        aisha_M = findViewById(R.id.masjid_aishaa);
        jin_M = findViewById(R.id.masjid_jinn);
        bilal_M = findViewById(R.id.masjid_bilall);
        alKhayf_M = findViewById(R.id.masjid_al_khayff);
        muzdilfa = findViewById(R.id.muzdalifahh);
        jamaraat = findViewById(R.id.jamaratt);
        mountQais = findViewById(R.id.mount_abu_qubaiss);
        birthPlace = findViewById(R.id.birth_placee);
        caveHira = findViewById(R.id.cave_hiraa);
        caveThour = findViewById(R.id.cave_thaurr);
        jabalNoor = findViewById(R.id.jabal_nourr);
        jannatAlmualla = findViewById(R.id.janat_almuallaa);
        jamalAlrahmah = findViewById(R.id.jamal_alrahmahh);

        alharm_M.setOnClickListener(this);
        aisha_M.setOnClickListener(this);
        jin_M.setOnClickListener(this);
        bilal_M.setOnClickListener(this);
        alKhayf_M.setOnClickListener(this);
        muzdilfa.setOnClickListener(this);
        jamaraat.setOnClickListener(this);
        mountQais.setOnClickListener(this);
        birthPlace.setOnClickListener(this);
        caveHira.setOnClickListener(this);
        caveThour.setOnClickListener(this);
        jabalNoor.setOnClickListener(this);
        jannatAlmualla.setOnClickListener(this);
        jamalAlrahmah.setOnClickListener(this);
        intent = new Intent(HHPmeccaActivity.this,HHPdetailActivity.class);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.masjid_al_haramm:
               // Intent intent = new Intent(HHPmeccaActivity.this,HHPdetailActivity.class);
                intent.putExtra("value",1);
                startActivity(intent);
                break;
            case R.id.masjid_aishaa:
                intent.putExtra("value",2);
                startActivity(intent);
                break;
            case R.id.masjid_jinn:
                intent.putExtra("value",3);
                startActivity(intent);
                break;
            case R.id.masjid_bilall:
                intent.putExtra("value",4);
                startActivity(intent);
                break;
            case R.id.masjid_al_khayff:
                intent.putExtra("value",5);
                startActivity(intent);
                break;
            case R.id.muzdalifahh:
                intent.putExtra("value",6);
                startActivity(intent);
                break;
            case R.id.jamaratt:
                intent.putExtra("value",7);
                startActivity(intent);
                break;
            case R.id.mount_abu_qubaiss:
                intent.putExtra("value",8);
                startActivity(intent);
                break;
            case R.id.birth_placee:
                intent.putExtra("value",9);
                startActivity(intent);
                break;
            case R.id.cave_hiraa:
                intent.putExtra("value",10);
                startActivity(intent);
                break;
            case R.id.cave_thaurr:
                intent.putExtra("value",11);
                startActivity(intent);
                break;
            case R.id.jabal_nourr:
                intent.putExtra("value",12);
                startActivity(intent);
                break;
            case R.id.janat_almuallaa:
                intent.putExtra("value",13);
                startActivity(intent);
                break;
                default:
                    intent.putExtra("value",14);
                    startActivity(intent);

        }
    }
}
