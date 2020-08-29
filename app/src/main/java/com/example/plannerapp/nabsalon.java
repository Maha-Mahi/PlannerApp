package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class nabsalon extends AppCompatActivity {

    TextView textView;
    RatingBar barrat;
    TextView contacttext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_nabsalon );

        textView=findViewById( R.id.nabtext );
        contacttext=findViewById(R.id.contactidnab);
        barrat=findViewById(R.id.rateidnab);

        //setting up the functions
        textView.setText( "   Bridal Makeup\n" +
                "  Party Makeup\n" +
                "  Hairstyles Massage\n" +
                "  Eye Makeup\n" +
                "  Airbrush Makeup" );
        contacttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcontact=new Intent(nabsalon.this,contactnab.class);
                startActivity(intentcontact);
            }
        });
        barrat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(nabsalon.this, rating+"Star", Toast.LENGTH_SHORT).show();
            }
        });

    }

}