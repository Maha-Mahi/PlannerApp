package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class fazeephoto extends AppCompatActivity {
    TextView textView;
    RatingBar barrat;
    TextView contacttext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fazeephoto );

        //casting alues
        textView=findViewById( R.id.fazeetext );
        contacttext=findViewById(R.id.contactidfazee);
        barrat=findViewById(R.id.rateidfazee);

        //setting up the functions
        textView.setText( " Candid Photographt \n  Albums \n Traditional Photography \n  Out Of City Services : Yes \n  Album witll be ready in 3-4 Weeks" );
        contacttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcontact=new Intent(fazeephoto.this,contactfazee.class);
                startActivity(intentcontact);
            }
        });
        barrat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(fazeephoto.this, rating+"Star", Toast.LENGTH_SHORT).show();
            }
        });

    }
}