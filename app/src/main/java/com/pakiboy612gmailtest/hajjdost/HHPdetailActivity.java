package com.pakiboy612gmailtest.hajjdost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class HHPdetailActivity extends AppCompatActivity {
    ImageView imageView;
    TextView hhpNameTV, hhpDetailTV;

    int valuee;

    Intent intent;

//    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hhpdetail);

        AdView mAdView = findViewById(R.id.adViewHHPdetail);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Bundle bundle = getIntent().getExtras();
        valuee = bundle.getInt("value");
        Log.d("valuee", "onCreate: "+valuee);
        intent = new Intent(HHPdetailActivity.this,HHPlocationActivity.class);

//        viewPager = findViewById(R.id.view_pager);
//        viewPager.setAdapter(new SlidesAdapter(getSupportFragmentManager()));

        //******************************************************************************************
        //  when clicked,,show current location to selected hhp location on map.
        //__________________________________________________________________________________________

        findViewById(R.id.direction_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (valuee){
                    case 1:
                        intent.putExtra("hhplocationNum",1);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("hhplocationNum",2);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra("hhplocationNum",3);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.putExtra("hhplocationNum",4);
                        startActivity(intent);
                        break;
                    case 5:
                        intent.putExtra("hhplocationNum",5);
                        startActivity(intent);
                        break;
                    case 6:
                        intent.putExtra("hhplocationNum",6);
                        startActivity(intent);
                        break;
                    case 7:
                        intent.putExtra("hhplocationNum",7);
                        startActivity(intent);
                        break;
                    case 8:
                        intent.putExtra("hhplocationNum",8);
                        startActivity(intent);
                        break;
                    case 9:
                        intent.putExtra("hhplocationNum",9);
                        startActivity(intent);
                        break;
                    case 10:
                        intent.putExtra("hhplocationNum",10);
                        startActivity(intent);
                        break;
                    case 11:
                        intent.putExtra("hhplocationNum",11);
                        startActivity(intent);
                        break;
                    case 12:
                        intent.putExtra("hhplocationNum",12);
                        startActivity(intent);
                        break;
                    case 13:
                        intent.putExtra("hhplocationNum",13);
                        startActivity(intent);
                        break;
                    case 14:
                        intent.putExtra("hhplocationNum",14);
                        startActivity(intent);
                        break;
                    case 21:
                        intent.putExtra("hhplocationNum",21);
                        startActivity(intent);
                        break;
                    case 22:
                        intent.putExtra("hhplocationNum",22);
                        startActivity(intent);
                        break;
                    case 23:
                        intent.putExtra("hhplocationNum",23);
                        startActivity(intent);
                        break;
                    case 24:
                        intent.putExtra("hhplocationNum",24);
                        startActivity(intent);
                        break;
                    case 25:
                        intent.putExtra("hhplocationNum",25);
                        startActivity(intent);
                        break;
                    case 26:
                        intent.putExtra("hhplocationNum",26);
                        startActivity(intent);
                        break;
                    case 27:
                        intent.putExtra("hhplocationNum",27);
                        startActivity(intent);
                        break;
                    case 28:
                        intent.putExtra("hhplocationNum",28);
                        startActivity(intent);
                        break;
                    case 29:
                        intent.putExtra("hhplocationNum",29);
                        startActivity(intent);
                        break;
                    case 30:
                        intent.putExtra("hhplocationNum",30);
                        startActivity(intent);
                        break;
                    case 31:
                        intent.putExtra("hhplocationNum",31);
                        startActivity(intent);
                        break;
                    case 32:
                        intent.putExtra("hhplocationNum",32);
                        startActivity(intent);
                        break;
                    case 33:
                        intent.putExtra("hhplocationNum",33);
                        startActivity(intent);
                        break;
                    default:
                        intent.putExtra("hhplocationNum",34);
                        startActivity(intent);
                }
            }
        });

        //******************************************************************************************
        //  image , name ,and detail set karny kelye
        //__________________________________________________________________________________________
        imageView = findViewById(R.id.image_view);
        hhpNameTV = findViewById(R.id.name_hhp_TV);
        hhpDetailTV = findViewById(R.id.detail_hhp_TV);

        setDataInDetail();

    }

    private void setDataInDetail() {
        switch (valuee){
            case 1:
                imageView.setBackgroundResource(R.drawable.alharam);
                hhpNameTV.setText("Al-Haram Mosque");
                hhpDetailTV.setText(R.string.alHaram);
                break;
            case 2:
                imageView.setBackgroundResource(R.drawable.masjid_aisha);
                hhpNameTV.setText("Aisha Mosque");
                hhpDetailTV.setText(R.string.masjidAisha);
                break;
            case 3:
                imageView.setBackgroundResource(R.drawable.masjid_jinn);
                hhpNameTV.setText("Al_Jinn mosque");
                hhpDetailTV.setText(R.string.masjidJin);
                break;
            case 4:
                imageView.setBackgroundResource(R.drawable.masjid_bilal);
                hhpNameTV.setText("Bilal Mosque");
                hhpDetailTV.setText(R.string.masjidBilal);
                break;
            case 5:
                imageView.setBackgroundResource(R.drawable.al_khayf_mosque);
                hhpNameTV.setText("Al-Khayf Mosque");
                hhpDetailTV.setText(R.string.alKhayfMosque);
                break;
            case 6:
                imageView.setBackgroundResource(R.drawable.muzdalifah);
                hhpNameTV.setText("Muzdalifa");
                hhpDetailTV.setText(R.string.Muzdalifa);
                break;
            case 7:
                imageView.setBackgroundResource(R.drawable.jamaraat);
                hhpNameTV.setText("Jamaraat");
                hhpDetailTV.setText(R.string.Jamaraat);
                break;
            case 8:
                imageView.setBackgroundResource(R.drawable.mount_abu_qubais);
                hhpNameTV.setText("Mount Abu Qubais");
                hhpDetailTV.setText(R.string.mountAbuQubais);
                break;
            case 9:
                imageView.setBackgroundResource(R.drawable.birth_placee);
                hhpNameTV.setText("Birth Place of the Holy Prophet");
                hhpDetailTV.setText(R.string.birthPlace);
                break;
            case 10:
                imageView.setBackgroundResource(R.drawable.cave_hira);
                hhpNameTV.setText("Cave of Hira");
                hhpDetailTV.setText(R.string.caveHira);
                break;
            case 11:
                imageView.setBackgroundResource(R.drawable.cave_thawr);
                hhpNameTV.setText("Cave of Thawr");
                hhpDetailTV.setText(R.string.caveThawr);
                break;
            case 12:
                imageView.setBackgroundResource(R.drawable.jabal_noor);
                hhpNameTV.setText("Jabal Al-Noor");
                hhpDetailTV.setText(R.string.jabalNoor);
                break;
            case 13:
                imageView.setBackgroundResource(R.drawable.jannat_mualla);
                hhpNameTV.setText("Jannat al-Mualla ");
                hhpDetailTV.setText(R.string.jannatMualla);
                break;
            case 14:
                imageView.setBackgroundResource(R.drawable.jabal_alrehma);
                hhpNameTV.setText("Jabal Al-Rehma");
                hhpDetailTV.setText(R.string.jabalRehma);
                break;
            case 21:
                imageView.setBackgroundResource(R.drawable.masjid_nabvi);
                hhpNameTV.setText("Masjid-e-Nabvi");
                hhpDetailTV.setText(R.string.masjidNabvii);
                break;
            case 22:
                imageView.setBackgroundResource(R.drawable.masjid_quba);
                hhpNameTV.setText("Masjid-e-Quba");
                hhpDetailTV.setText(R.string.masjidQuba);
                break;
            case 26:
                imageView.setBackgroundResource(R.drawable.masjid_qiblatain);
                hhpNameTV.setText("Masjid-e-Qiblatain");
                hhpDetailTV.setText(R.string.masjidQiblatain);
                break;
            case 23:
                imageView.setBackgroundResource(R.drawable.masjid_juma);
                hhpNameTV.setText("Masjid Al-Juma");
                hhpDetailTV.setText(R.string.masjidJuma);
                break;
            case 24:
                imageView.setBackgroundResource(R.drawable.masjid_bilal_medina);
                hhpNameTV.setText("Masjid Al-Bilal");
                hhpDetailTV.setText(R.string.masjidBilalMedina);
                break;
            case 25:
                imageView.setBackgroundResource(R.drawable.masjid_abu_bakar);
                hhpNameTV.setText("Masjid Abu Bakar");
                hhpDetailTV.setText(R.string.masjidAbuBakar);
                break;
            case 27:
                imageView.setBackgroundResource(R.drawable.roza_rasool);
                hhpNameTV.setText("Roza-e-Rasol");
                hhpDetailTV.setText(R.string.rozaRasol);
                break;
            case 28:
                imageView.setBackgroundResource(R.drawable.riaad_uljannah);
                hhpNameTV.setText("Riyaad-Ul-Jannah");
                hhpDetailTV.setText(R.string.riyaadUlJannah);
                break;
            case 29:
                imageView.setBackgroundResource(R.drawable.bir_uthman);
                hhpNameTV.setText("Bir-e-Uthman");
                hhpDetailTV.setText(R.string.birUthman);
                break;
            case 30:
                imageView.setBackgroundResource(R.drawable.bir_ali);
                hhpNameTV.setText("Bir-e-ALi");
                hhpDetailTV.setText(R.string.birAli);
                break;
            case 31:
                imageView.setBackgroundResource(R.drawable.khandak);
                hhpNameTV.setText("Site of Battle of Trench\n (Khandaq)");
                hhpDetailTV.setText(R.string.khandak);
                break;
            case 32:
                imageView.setBackgroundResource(R.drawable.jannat_ul_baqi);
                hhpNameTV.setText("Jannat-Ul-Baqi");
                hhpDetailTV.setText(R.string.jannatUlBaqi);
                break;
            case 33:
                imageView.setBackgroundResource(R.drawable.mountain_heaven_hell);
                hhpNameTV.setText("Mountains of Heaven and Hell");
                hhpDetailTV.setText(R.string.mountainHeavenHell);
                break;
                default:
                    imageView.setBackgroundResource(R.drawable.wadi_jinn);
                    hhpNameTV.setText("Wadi-e-Jinn");
                    hhpDetailTV.setText(R.string.wadiJinn);
        }
    }


//
//    private class SlidesAdapter extends FragmentPagerAdapter{
//
//        public SlidesAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            if (position == 0) {
//                return new ImageFragmentOne();
//            } else if (position == 1) {
//                return new ImageFragmentTwo();
//            } else {
//                return new ImageFragmentThree();
//            }
//        }
//
//        @Override
//        public int getCount() {
//            return 3;
//        }
//    }
}
