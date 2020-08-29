package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Decoration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoration);

        ImageButton main1;
        main1=findViewById(R.id.imagebaloon);
        main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Decoration.this,BaloonDecore.class);
                startActivity(intent1);
            }
        });

        ImageButton main2;
        main2=findViewById(R.id.imageflower);
        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Decoration.this,FlowerDecore.class);
                startActivity(intent1);
            }
        });


    }
}