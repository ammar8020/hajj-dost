package com.pakiboy612gmailtest.hajjdost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class HajjGuidDetailActivity extends AppCompatActivity {

    TextView headingTV , detailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hajj_guid_detail);

        AdView mAdView = findViewById(R.id.adViewHajjGuideDetail);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Bundle clickedValueExtra = getIntent().getExtras();
        int clickedValue = clickedValueExtra.getInt("clickedvalue");

        // **************get UI references
        headingTV = findViewById(R.id.heading_hgd_tv);
        detailTV = findViewById(R.id.detail_hgd_tv);
        switch (clickedValue){
            case 1:
                headingTV.setText("How to Perform Hajj");
                detailTV.setText(R.string.How_to_Perform_Hajj_step_by_step);
                break;
            case 2:
                headingTV.setText("be sure you are ready to perform hajj");
                detailTV.setText(R.string.be_sure_you_are_ready_to_perform_hajj);
                break;
            case 3:
                headingTV.setText("Decide which type of Hajj you will undertake");
                detailTV.setText(R.string.Decide_which_type_of_Hajj_you_will_undertake);
                break;
            case 4:
                headingTV.setText("Plan your trip to Saudi Arabia");
                detailTV.setText(R.string.Plan_your_trip_to_Saudi_Arabia);
                break;
            case 5:
                headingTV.setText("Prepare to be immersed in religion");
                detailTV.setText(R.string.Prepare_to_be_immersed_in_religion);
                break;
            case 6:
                headingTV.setText("Assume Ihram");
                detailTV.setText(R.string.Assume_Ihram);
                break;
            case 7:
                headingTV.setText("Declare your intention and say the Talbiyah");
                detailTV.setText(R.string.Declare_your_intention_and_say_the_Talbiyah);
                break;
            case 8:
                headingTV.setText("Proceed towards the Kabah The most sacred location in Islam");
                detailTV.setText(R.string.Proceed_towards_the_Kabah_The_most_sacred_location_in_Islam);
                break;
            case 9:
                headingTV.setText("Perform the Tawaf");
                detailTV.setText(R.string.Perform_the_Tawaf);
                break;
            case 10:
                headingTV.setText("Perform Saey");
                detailTV.setText(R.string.Perform_Saey);
                break;
            case 11:
                headingTV.setText("Have your hair shaved or clipped");
                detailTV.setText(R.string.Have_your_hair_shaved_or_clipped);
                break;
            case 12:
                headingTV.setText("Reassume Ihram and declare your intention to perform Hajj");
                detailTV.setText(R.string.Reassume_Ihram_and_declare_your_intention_to_perform_Hajj);
                break;
            case 13:
                headingTV.setText("Head to Mina");
                detailTV.setText(R.string.Head_to_Mina);
                break;
            case 14:
                headingTV.setText("Head to Arafat and perform Waquf");
                detailTV.setText(R.string.Head_to_Arafat_and_perform_Waquf);
                break;
            case 15:
                headingTV.setText("Pray in Muzdalifah");
                detailTV.setText(R.string.Pray_in_Muzdalifah);
                break;
            case 16:
                headingTV.setText("Perform Ramy in Mina");
                detailTV.setText(R.string.Perform_Ramy_in_Mina);
                break;
            case 17:
                headingTV.setText("Offer a sacrifice");
                detailTV.setText(R.string.Offer_a_sacrifice);
                break;
            case 18:
                headingTV.setText("Get your hair cut or shaved");
                detailTV.setText(R.string.Get_your_hair_cut_or_shaved);
                break;
            case 19:
                headingTV.setText("Perform the Tawaf and Saey");
                detailTV.setText(R.string.Perform_the_Tawaf_and_Saey);
                break;
            case 20:
                headingTV.setText("Repeat Ramy after sundown on the fourth and fifth days");
                detailTV.setText(R.string.Repeat_Ramy_after_sundown_on_the_fourth_and_fifth_days);
                break;
            default:
                headingTV.setText("Perform the Farewell Tawaf");
                detailTV.setText(R.string.Perform_the_Farewell_Tawaf);
                break;
        }

    }
}
