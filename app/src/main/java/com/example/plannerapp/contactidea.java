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
import com.google.android.gms.maps.model.MarkerOptions;

public class contactidea extends AppCompatActivity implements OnMapReadyCallback {
    private static final int DEFAULT_ZOOM = 15;
    private ViewFlipper flipper;
    private ImageButton btnimage;

    //mapsska kamm
    GoogleMap gMap;
    double bottombound11=24.942188;
    double leftbuttom11=67.085915;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_contactidea );
        SupportMapFragment supportMapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapidea);
        supportMapFragment.getMapAsync( this);

        btnimage=findViewById(R.id.phoneimagebtnidea);
        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentphone= new Intent(Intent.ACTION_VIEW);
                intentphone.setData( Uri.parse("tel:03368069650"));
                startActivity(intentphone);
                //Toast.makeText(ContactPortfolio.this,"clicked,",Toast.LENGTH_LONG).show();
            }
        });
        flipper=findViewById(R.id.viewFlipperidea);
        int images[]={R.drawable.idea1,R.drawable.idea2,R.drawable.idea3,R.drawable.idea4,R.drawable.ideatitl};

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
        CameraUpdate cameraUpdateFactory= CameraUpdateFactory.newLatLngZoom( latLng,DEFAULT_ZOOM );
        showMarker( latLng );
        gMap.moveCamera( cameraUpdateFactory );
        gMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap=googleMap;
        gotoLOcation(bottombound11,leftbuttom11);

    }

    private void showMarker(LatLng latLng){
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title( "Ideatic Studio" );
        gMap.addMarker(markerOptions);

    }
}