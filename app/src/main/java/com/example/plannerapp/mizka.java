package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class mizka extends AppCompatActivity {

    TextView textView;
    RatingBar barrat;
    TextView contacttext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mizka );


        textView=findViewById( R.id.miztext );
        contacttext=findViewById(R.id.contactidmiz);
        barrat=findViewById(R.id.rateidmiz);

        //setting up the functions
        textView.setText( "   Bridal Makeup\n" +
                "  Party Makeup\n" +
                "  Hairstyles Eye Makeup\n" +
                "  Airbrush Makeup" );
        contacttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcontact=new Intent(mizka.this,contactmizka.class);
                startActivity(intentcontact);
            }
        });
        barrat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(mizka.this, rating+"Star", Toast.LENGTH_SHORT).show();
            }
        });

    }
}