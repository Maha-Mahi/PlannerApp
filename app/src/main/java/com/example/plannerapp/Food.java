package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Food extends AppCompatActivity {

    Button view1,view2,meet1,meet2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);


        //casting values of buttons
        view1=findViewById(R.id.awviewid );
        view2=findViewById( R.id.darbarviewid );
        meet1=findViewById( R.id.awmeetid );
        meet2=findViewById( R.id.darbarmeetid );



        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Food.this,awcate.class);
                startActivity(intent1);
            }
        });
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Food.this,darbarcate.class);
                startActivity(intent1);
            }
        });

        meet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentphone= new Intent(Intent.ACTION_VIEW);
                intentphone.setData( Uri.parse("tel:03368069650"));
                startActivity(intentphone);
                //Toast.makeText(ContactPortfolio.this,"clicked,",Toast.LENGTH_LONG).show();
            }
        });
        meet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentphone= new Intent(Intent.ACTION_VIEW);
                intentphone.setData( Uri.parse("tel:03368069650"));
                startActivity(intentphone);
                //Toast.makeText(ContactPortfolio.this,"clicked,",Toast.LENGTH_LONG).show();
            }
        });


    }
}