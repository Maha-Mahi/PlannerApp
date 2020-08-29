package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Baloonpartpopper1 extends AppCompatActivity {
    RatingBar barrat;
    TextView contacttext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baloonpartpopper1);

        contacttext=findViewById(R.id.contactid);
        contacttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcontact=new Intent(Baloonpartpopper1.this,ContactPortfolio.class);
                startActivity(intentcontact);
            }
        });
        TextView text=findViewById(R.id.reviewid);
        registerForContextMenu(text);

        barrat=findViewById(R.id.rateid);
        barrat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(Baloonpartpopper1.this, rating+"Star", Toast.LENGTH_SHORT).show();
            }
        });
    }

}