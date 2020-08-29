package com.example.plannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    private ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomnavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        return true;

                    case R.id.events:
                        startActivity(new Intent(getApplicationContext(),AllEvents.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.vendors:
                        startActivity(new Intent(getApplicationContext(),Vendors.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Moree:
                        startActivity(new Intent(getApplicationContext(),Menu.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



        //slider code
        flipper=findViewById(R.id.flipper);
        int images[] = {R.drawable.slide1 , R.drawable.slide2 , R.drawable.slide3,R.drawable.slide4};

        for (int image:images){
            flipperImage(image);
        }
    }
    public void flipperImage(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

       flipper.setInAnimation(this, android.R.anim.slide_in_left);
       flipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder alert=new AlertDialog.Builder( this );
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