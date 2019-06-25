package com.pakiboy612gmailtest.hajjdost;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class PrayActivity extends AppCompatActivity {

    TextView prayTV;
    MediaPlayer audioPlayer;
    ImageButton playPrayIc;

    private boolean playpaus = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pray);

        AdView mAdView = findViewById(R.id.adViewPray);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        playPrayIc = findViewById(R.id.play_pray_ic);
        playPrayIc.setImageResource(R.drawable.ic_audio_play);

        prayTV = findViewById(R.id.pray_tv);
        prayTV.setText(R.string.talbiyah);
    }

    public void playPrayAudio(View view) {
        if (playpaus==false) {
            audioPlayer = MediaPlayer.create(this, R.raw.talbiyah);
            playpaus = true;
            playPrayIc.setImageResource(R.drawable.ic_audio_pause);
            audioPlayer.start();
        }else {
            playpaus = false;
            playPrayIc.setImageResource(R.drawable.ic_audio_play);
            audioPlayer.stop();
        }
    }
}
