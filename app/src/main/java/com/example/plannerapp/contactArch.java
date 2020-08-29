package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class contactArch extends AppCompatActivity implements OnMapReadyCallback {

    private static final int DEFAULT_ZOOM = 15;
    private ViewFlipper flipper;
    private ImageButton btnimage;
    double bottombound11=24.9180;
    double leftbuttom11=67.0971;


    //mapsska kamm
    GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_contact_arch );

        if (gMap!=null){
            double bottombound=24.916663;
            double leftbuttom=67.083333;
            double topbuttom=24.920795;
            double rightbuttom=67.092670;
            LatLngBounds gulshan=new LatLngBounds(
                    new LatLng(bottombound,leftbuttom ),
                    new LatLng( topbuttom,rightbuttom )
            );
            gMap.animateCamera( CameraUpdateFactory.newLatLngBounds( gulshan,2 ) );
            showMarker( gulshan.getCenter() );
        }


        SupportMapFragment supportMapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.maparch);
        supportMapFragment.getMapAsync( this);

        btnimage=findViewById(R.id.phoneimagebtnarch);
        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentphone= new Intent(Intent.ACTION_VIEW);
                intentphone.setData( Uri.parse("tel:03368069650"));
                startActivity(intentphone);
                //Toast.makeText(ContactPortfolio.this,"clicked,",Toast.LENGTH_LONG).show();
            }
        });
        flipper=findViewById(R.id.viewFlipperarch);
        int images[]={R.drawable.archballon1,R.drawable.archbaloon2,R.drawable.archbaloon3};

        for (int image:images){
            flipperImage(image);
        }


    }

    public void flipperImage(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        flipper.addView(imageView);
        flipper.setFlipInterval(2000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }


    private void gotoLOcation(double lat,double lng){
        LatLng latLng=new LatLng( lat,lng );
        CameraUpdate cameraUpdateFactory=CameraUpdateFactory.newLatLngZoom( latLng,DEFAULT_ZOOM );
        showMarker( latLng );
        gMap.moveCamera( cameraUpdateFactory );
        gMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap=googleMap;
        gotoLOcation(bottombound11,leftbuttom11  );
//        LatLng islamabad=new LatLng(24.860303,67.001678);
//        googleMap.addMarker(new MarkerOptions().position(islamabad).title("Karachi"));
    }
    private void showMarker(LatLng latLng){
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title( "Arch Balloons" );
        gMap.addMarker(markerOptions);

    }
}