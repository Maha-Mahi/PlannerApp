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

public class makeup extends AppCompatActivity implements Dialogue.DialogueListener {

    FirebaseFirestore firestore;
    FirebaseAuth mauth;
    Button view1,view2,meet1,meet2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeup);

        //firebase
        mauth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        //casting values of buttons
        view1=findViewById(R.id.nabviewid );
        view2=findViewById( R.id.mizviewid );
        meet1=findViewById( R.id.nabmeetid);
        meet2=findViewById( R.id.mizmeetid );


        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(makeup.this,nabsalon.class);
                startActivity(intent1);
            }
        });
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(makeup.this,mizka.class);
                startActivity(intent1);
            }
        });

        meet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meet2.setEnabled( false );
                openDialouge();
            }
        });
        meet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meet1.setEnabled( false );
                openDialouge();
            }
        });

    }
    private void openDialouge() {
        Dialogue dialogue=new Dialogue();
        dialogue.show( getSupportFragmentManager(),"Dialogue" );
    }


    @Override
    public void applytext(final String timemeet, final String datemeet) {


        Toast.makeText( makeup.this,"Moving you to your Inbox",Toast.LENGTH_LONG ).show();


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


                    if(meet1.isEnabled()) {
                        //sms sending to the vendor
                        sms_intent.putExtra( "address", new String( phone1 ) );
                        sms_intent.putExtra( "sms_body", value + "\n" + timemeet + "\n" + datemeet );
                        sms_intent.setType( "vnd.android-dir/mms-sms" );
                        startActivity( sms_intent );
                        Toast.makeText( makeup.this, "Message Send", Toast.LENGTH_LONG ).show();
                        finish();
                    }
                    if (meet2.isEnabled()){
                        //sms sending to the vendor
                        sms_intent.putExtra( "address",new String( phone2 ));
                        sms_intent.putExtra( "sms_body", value + "\n" + timemeet + "\n" + datemeet );
                        sms_intent.setType( "vnd.android-dir/mms-sms" );
                        startActivity( sms_intent );
                        Toast.makeText( makeup.this, "Message Send", Toast.LENGTH_LONG ).show();
                        finish();
                    }
                }
            }
        } );

    }
}