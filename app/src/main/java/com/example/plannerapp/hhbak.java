package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class hhbak extends AppCompatActivity {
    TextView textView;
    private ViewFlipper flipper;
    RatingBar barrat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_hhbak );

        barrat=findViewById(R.id.rateidhh);
        textView=findViewById( R.id.hhtext );
        textView.setText( "  Delivery to Home: Yes\n" +
                "  Icecream Cake\n" +
                "  Wedding Cake\n" +
                "  Birthday Cakes\n" +
                "  Cupcakes\n" +
                "  Fondant Cake\n" +
                "  Sponge Cakes\n" +
                "  Stacked Cake\n" +
                "  Anniversary Cakes\n" +
                "  Customized Cake  " );
        flipper=findViewById(R.id.viewFlipperhh);
        int images[]={R.drawable.hh1,R.drawable.hh2,R.drawable.hhtitle1};

        for (int image:images){
            flipperImage(image);
        }

        barrat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(hhbak.this, rating+"Star", Toast.LENGTH_SHORT).show();
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

}