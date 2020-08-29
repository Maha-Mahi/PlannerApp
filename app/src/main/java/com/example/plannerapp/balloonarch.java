package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class balloonarch extends AppCompatActivity {

    RatingBar barrat;
    TextView contacttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balloonarch);

        contacttext=findViewById(R.id.contactidarch);
        contacttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcontact=new Intent(balloonarch.this,contactArch.class);
                startActivity(intentcontact);
            }
        });

        barrat=findViewById(R.id.rateidarch);
        barrat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(balloonarch.this, rating+"Star", Toast.LENGTH_SHORT).show();
            }
        });


    }
}