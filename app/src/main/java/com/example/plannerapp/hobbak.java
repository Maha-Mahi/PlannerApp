package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class hobbak extends AppCompatActivity {

    TextView textView;
    private ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_hobbak );

        textView=findViewById( R.id.hobtext );
        textView.setText( " Deliver to Home:Yes \n " +
                "  Icecream Cake\n" +
                "  Wedding Cake\n" +
                "  Birthday Cakes\n" +
                "  Cupcakes\n" +
                "  Fondant Cake\n" +
                "  Cheese Cake\n" +
                "  Sponge Cakes\n" +
                "  Stacked Cake\n" +
                "  Anniversary Cakes" );

        flipper=findViewById(R.id.viewFlipperhob);
        int images[]={R.drawable.hob1,R.drawable.hob2,R.drawable.hob3};

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

}