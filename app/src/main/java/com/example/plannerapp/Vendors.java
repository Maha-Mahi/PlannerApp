package com.example.plannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Vendors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors);

        //open new activities by clicking imagebutton code
        ImageButton main;
        main=findViewById(R.id.decorimage);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Vendors.this,Decoration.class);
                startActivity(intent1);
            }
        });

        ImageButton main1;
        main1=findViewById(R.id.cateimage);

        main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Vendors.this,Catering.class);
                startActivity(intent1);
            }
        });

        ImageButton main3;
        main3=findViewById(R.id.artimage);

        main3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Vendors.this,Artist.class);
                startActivity(intent1);
            }
        });

        ImageButton main4;
        main4=findViewById(R.id.photoimage);

        main4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Vendors.this,Photography.class);
                startActivity(intent1);
            }
        });



//bottom nav bar code
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomnavigation);
        bottomNavigationView.setSelectedItemId(R.id.vendors);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.events:
                        startActivity(new Intent(getApplicationContext(),AllEvents.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.vendors:
                        return true;

                    case R.id.Moree:
                        startActivity(new Intent(getApplicationContext(),Menu.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder alert=new AlertDialog.Builder( Vendors.this );
            alert.setMessage( "Are you sure you want to do this" );
            alert.setCancelable( false );
            alert.setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();

                }
            } ).setNegativeButton( "No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            } );
            AlertDialog alertDialog=alert.create();
            alert.show();

        }
        return true;

    }
}