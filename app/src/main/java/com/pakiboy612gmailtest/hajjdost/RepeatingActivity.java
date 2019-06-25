package com.pakiboy612gmailtest.hajjdost;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
public class RepeatingActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeating);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.todo_text);
        imageView = findViewById(R.id.img_view);
        int i = 0;
        String my_date = "14/05/2019";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date strDate = null;
        try {
            strDate = sdf.parse(my_date);
            if (System.currentTimeMillis() > strDate.getTime()) {
                my_date = "25/04/2019";
                strDate = sdf.parse(my_date);
                if (System.currentTimeMillis() > strDate.getTime()){
                    my_date = "26/04/2019";
                    strDate = sdf.parse(my_date);
                    if (System.currentTimeMillis() < strDate.getTime()){
                        i = 2;
                    }
                }else{
                    i = 1;
                }

            }
            else{
                i = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setContent(0);
    }


    public void setContent(int i){
        if(i==0){
            textView.setText(getString(R.string.ninth));
            imageView.setImageResource(R.drawable.arafaat);
            button.setVisibility(View.VISIBLE);
            setTitle("9th Dhul-hijjah");
        }else if(i==1){
            textView.setText(getString(R.string.tenth));
            imageView.setImageResource(R.drawable.muzdalifah);
            button.setVisibility(View.VISIBLE);
        }else if(i==2){
            textView.setText(getString(R.string.rest));
            imageView.setImageResource(R.drawable.mina);
            button.setVisibility(View.VISIBLE);
        }
    }

    public void map_click(View view) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=31.5204,74.3587");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
