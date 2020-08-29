package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class romanmehndi extends AppCompatActivity {

    TextView textView;
    RatingBar barrat;
    TextView contacttext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_romanmehndi );

        textView=findViewById( R.id.romantext );
        contacttext=findViewById(R.id.contactidroman);
        barrat=findViewById(R.id.rateidroman);

        //setting up the functions
        textView.setText( "  Bridal Makeup\n" +
                "  Family makeup\n" +
                "  Party Makeup\n" +
                "  Hairstyles \n"+ "Eye Makeup\n" );
        contacttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcontact=new Intent(romanmehndi.this,contactroman.class);
                startActivity(intentcontact);
            }
        });
        barrat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(romanmehndi.this, rating+"Star", Toast.LENGTH_SHORT).show();
            }
        });

    }
}