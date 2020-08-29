package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class darbarcate extends AppCompatActivity implements OnMapReadyCallback {

    private static final int DEFAULT_ZOOM = 18;
    TextView textView;
    private ViewFlipper flipper;
    RatingBar barrat;

    //mapsska kamm
    GoogleMap gMap;
    double bottombound11=24.883291;
    double leftbuttom11=67.046398;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_darbarcate );


        SupportMapFragment supportMapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapdarbar);
        supportMapFragment.getMapAsync( this);


        barrat=findViewById(R.id.rateiddarbar);
        textView=findViewById( R.id.darbartext );
        textView.setText( "   Generator Backup\n" +
                "  Tent\n" +
                "  DJ and Sound Systems\n" +
                "  Dance Floor\n" +
                "  Stage Decoration\n" +
                "  Lights\n" +
                "  Air Condition\n" +
                "  Heater\n" );

        flipper=findViewById(R.id.viewFlipperdarbar);
        int images[]={R.drawable.darbadr1,R.drawable.darbar2,R.drawable.darbar3};

        for (int image:images){
            flipperImage(image);
        }
        barrat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(darbarcate.this, rating+"Star", Toast.LENGTH_SHORT).show();
            }
        });




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
        markerOptions.title( "Darbar Catering,Jamshaed Quarters-KHI" );
        gMap.addMarker(markerOptions);


    }
}