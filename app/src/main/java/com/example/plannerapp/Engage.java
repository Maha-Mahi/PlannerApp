package com.example.plannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.HashMap;
import java.util.Map;

public class Engage extends AppCompatActivity {

    ViewPager viewPager;
    DotsIndicator dotsIndicator;
    ViewAdaptor viewAdaptor;



    FirebaseFirestore firestore;
    FirebaseAuth mauth;
    CollectionReference collectionReference;

    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;

    EditText editText;

    Button btn12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engage);

        firestore=FirebaseFirestore.getInstance();
        mauth=FirebaseAuth.getInstance();


        textView=findViewById(R.id.texteng1);
        textView1=findViewById(R.id.texteng2);
        textView2=findViewById(R.id.texteng3);
        textView3=findViewById(R.id.texteng4);

        editText=findViewById(R.id.texteng0);

        btn12=findViewById(R.id.bookid);

        viewPager=findViewById(R.id.viewpager11);
        dotsIndicator=findViewById(R.id.dot11);
        viewAdaptor=new ViewAdaptor(Engage.this);
        viewPager.setAdapter(viewAdaptor);
        dotsIndicator.setViewPager(viewPager);

        textView.setText(  " FOOD\n" +
                "  CATERING\n" +
                "  DECOR\n" +
                "  LIGHTS\n" +
                "  GENERATOR\n" +
                "  DJ\n" +
                "  DANCE FLOOR");

        textView1.setText("DEAL PRIZE:RS 250,000\n");


        final String phone="03368069650";
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentphone1= new Intent(Intent.ACTION_VIEW);
                intentphone1.setData( Uri.parse( phone ) );
                    startActivity(intentphone1);
            }
        });


        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DocumentReference documentReference = firestore.collection("User")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document=task.getResult();
                            final Map<String,Object> value=document.getData();
                            value.get("Conatct");

                            String dealname=editText.getText().toString();

                            collectionReference=firestore.collection("OrderDeals");
                            Map<String,Object> userData=new HashMap<>();
                            userData.put("Person Information:",value);
                            userData.put("Deal Name:",dealname);
                            collectionReference.document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .set(userData);

                            AlertDialog.Builder alertDialog=new AlertDialog.Builder(Engage.this);
                            alertDialog.setTitle("Thankyou!");
                            alertDialog.setMessage("Your order is Recieved.\n We will COntact you in 24hours.. ");
                            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            });
                            alertDialog.create();
                            alertDialog.show();



                        }else {
                            Toast.makeText(Engage.this,"Fail",Toast.LENGTH_LONG).show();
                        }
                    }


                });


            }
        });

        }

       }