package com.example.plannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.HashMap;
import java.util.Map;

public class Valima extends AppCompatActivity {


    ViewPager viewPager2;
    DotsIndicator dotsIndicator2;
    ViewAdaptor2 viewAdaptor2;


    FirebaseFirestore firestore;
    FirebaseAuth mauth;
    CollectionReference collectionReference;

    TextView valimatextView;
    TextView valimatextView1;
    TextView valimatextView2;
    TextView valimatextView3;

    EditText valimaedit;

    Button btnvalima1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valima);

        firestore=FirebaseFirestore.getInstance();
        mauth=FirebaseAuth.getInstance();


        valimatextView=findViewById(R.id.textvalima1);
        valimatextView1=findViewById(R.id.textvalima2);
        valimatextView2=findViewById(R.id.textvalima3);
        valimatextView3=findViewById(R.id.textvalima4);

        valimaedit=findViewById(R.id.editvalima);

        btnvalima1=findViewById(R.id.bookvalimaid);

        viewPager2=findViewById(R.id.viewpagervalima);
        dotsIndicator2 =findViewById(R.id.dotvalima);
        viewAdaptor2=new ViewAdaptor2(Valima.this);
        viewPager2.setAdapter(viewAdaptor2);
        dotsIndicator2.setViewPager(viewPager2);


        valimatextView.setText("DEAL INCLUDES:\nFOOD\n" +
                "  CATERING\n" +
                "  LIGHTS\n" +
                "  ENTRANCE DECOR\n" +
                "  STAGE DECOR\n" +
                "  GENERATOR\n");

        valimatextView1.setText("DEAL PRIZE:    RS 350,000\n" +
                "Per Event\n"
                + "For Details Contact Us:\n");

        valimatextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentphone= new Intent(Intent.ACTION_VIEW);
                intentphone.setData(Uri.parse("03308069650"));
                startActivity(intentphone);
            }
        });

        btnvalima1.setOnClickListener(new View.OnClickListener() {
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

                            String dealname=valimaedit.getText().toString();

                            collectionReference=firestore.collection("OrderDeals");
                            Map<String,Object> userData=new HashMap<>();
                            userData.put("Person Information:",value);
                            userData.put("Deal Name:",dealname);
                            collectionReference.document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .set(userData);

                            AlertDialog.Builder alertDialog=new AlertDialog.Builder(Valima.this);
                            alertDialog.setTitle("Thankyou!");
                            alertDialog.setMessage("Your order is Recieved.\n We will COntact you in 24hours.. ");
                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alertDialog.create();
                            alertDialog.show();
                        }else {
                            Toast.makeText(Valima.this,"Fail",Toast.LENGTH_LONG).show();
                        }
                    }


                });


            }
        });




    }
}