package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ideaphoto extends AppCompatActivity {


    TextView textView;
    RatingBar barrat;
    TextView contacttext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ideaphoto );

        //casting alues
        textView=findViewById( R.id.ideatext );
        contacttext=findViewById(R.id.contactididea);
        barrat=findViewById(R.id.rateididea);

        //setting up the functions
        textView.setText( " Candid Photographt \n  Albums \n Pre Wedding Shoot \n   Wedding Filmography \n  Out Of City Services : Yes \n  Album will be ready in 3-4 Weeks" );
        contacttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcontact=new Intent(ideaphoto.this,contactidea.class);
                startActivity(intentcontact);
            }
        });
        barrat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(ideaphoto.this, rating+"Star", Toast.LENGTH_SHORT).show();
            }
        });

    }
}