package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Artist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        ImageButton main1;
        main1=findViewById(R.id.mehndiimage);
        main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Artist.this,Mehndi.class);
                startActivity(intent1);
            }
        });

        ImageButton main2;
        main2=findViewById(R.id.makeupimage);
        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Artist.this,makeup.class);
                startActivity(intent1);
            }
        });


    }
}