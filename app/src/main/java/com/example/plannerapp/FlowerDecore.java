package com.example.plannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import static android.content.Intent.ACTION_VIEW;

public class FlowerDecore extends AppCompatActivity implements Dialogue.DialogueListener {

    FirebaseFirestore firestore;
    FirebaseAuth mauth;
    Button meetbtn1,meetbtn2,viewbtn1,viewbtn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_decore);

        //database fetching
        mauth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        //buttons casting
        meetbtn1=findViewById( R.id.florenzameetid );
        meetbtn2=findViewById( R.id.centralmeetid );
        viewbtn1=findViewById( R.id.florenzaviewid );
        viewbtn2=findViewById( R.id.centralviewid );

        //calling activites from the buttons
        viewbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(FlowerDecore.this,Florenza.class);
                startActivity(intent1);
            }
        });

        viewbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(FlowerDecore.this,CentralFlora.class);
                startActivity(intent1);
            }
        });
        meetbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetbtn2.setEnabled( false );
                openDialouge();
            }
        });

        meetbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetbtn1.setEnabled( false );
                openDialouge();
            }
        });

    }

    //Methods that called in the listenerclasses
    private void openDialouge() {
        Dialogue dialogue=new Dialogue();
        dialogue.show( getSupportFragmentManager(),"Dialogue" );
    }


    @Override
    public void applytext(final String timemeet, final String datemeet) {

        Toast.makeText( FlowerDecore.this,"Moving you to your Inbox",Toast.LENGTH_LONG ).show();


        final String phone1="0338069650";
        final String phone2="03003327862";
        //getting logged in user data from database
        DocumentReference documentReference = firestore.collection("User")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference.get().addOnCompleteListener( new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Map<String, Object> value = document.getData();
                    value.get("Conatct"+"\n"+"Username"+"\n");

                    Intent sms_intent = new Intent( ACTION_VIEW );
                    sms_intent.setData( Uri.parse( "smsto:" ) );


                    if(meetbtn1.isEnabled()) {
                        //sms sending to the vendor
                        sms_intent.putExtra( "address", new String( phone1 ) );
                        sms_intent.putExtra( "sms_body", value + "\n" + timemeet + "\n" + datemeet );
                        sms_intent.setType( "vnd.android-dir/mms-sms" );
                        startActivity( sms_intent );
                        Toast.makeText( FlowerDecore.this, "Message Send", Toast.LENGTH_LONG ).show();
                        finish();
                    }
                    if (meetbtn2.isEnabled()){
                        //sms sending to the vendor
                        sms_intent.putExtra( "address",new String( phone2 ));
                        sms_intent.putExtra( "sms_body", value + "\n" + timemeet + "\n" + datemeet );
                        sms_intent.setType( "vnd.android-dir/mms-sms" );
                        startActivity( sms_intent );
                        Toast.makeText( FlowerDecore.this, "Message Send", Toast.LENGTH_LONG ).show();
                        finish();
                    }
                }
            }
        } );


    }
}