package com.example.plannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {
    TextView hire,contactid,about,term;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //casting value
        hire=findViewById(R.id.hire);
        contactid=findViewById(R.id.contact);
        about=findViewById(R.id.aboutus);
        term=findViewById(R.id.terms);


        contactid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this,Booking.class);
                startActivity(intent);
            }
        });



        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this,AboutUs.class);
                startActivity(intent);
            }
        });

        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Menu.this,Terms_Conditions.class);
                startActivity(intent);
            }
        });

      //  ListView(Context context){this.context=context;}
        ListView listView=new ListView(this);
        List<String> data=new ArrayList<>();
        data.add("Engaement deal");
        data.add("Barat deal");
        data.add("Valima deal");

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);

        AlertDialog.Builder builder=new AlertDialog.Builder(Menu.this);
        builder.setTitle("Choose Deal:");
        builder.setCancelable(true);
        builder.setView(listView);
        final AlertDialog dialog=builder.create();

        hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

                }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent intent=new Intent(Menu.this,Engage.class);
                    startActivity(intent);

                }
                if (position==1){
                    Intent intent=new Intent(Menu.this,Barat.class);
                    startActivity(intent);

                }
                if (position==2){
                    Intent intent=new Intent(Menu.this,Valima.class);
                    startActivity(intent);
                }
            }
        });


        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomnavigation);
        bottomNavigationView.setSelectedItemId(R.id.Moree);
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
                        startActivity(new Intent(getApplicationContext(),Vendors.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Moree:
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder alert=new AlertDialog.Builder( Menu.this );
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