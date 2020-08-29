package com.example.plannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
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

public class Barat extends AppCompatActivity {

    ViewPager viewPager4;
    DotsIndicator dotsIndicator4;
    ViewAdaptor1 viewAdaptor1;


    FirebaseFirestore firestore;
    FirebaseAuth mauth;
    CollectionReference collectionReference;

    TextView barattextView;
    TextView barattextView1;
    TextView barattextView2;
    TextView barattextView3;

    EditText baratedit;

    Button btnbarat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barat);

        firestore=FirebaseFirestore.getInstance();
        mauth=FirebaseAuth.getInstance();


        barattextView=findViewById(R.id.textbarat1);
        barattextView1=findViewById(R.id.textbarat2);
        barattextView2=findViewById(R.id.textbarat3);
        barattextView3=findViewById(R.id.textbarat4);

        baratedit=findViewById(R.id.editbarat);

        btnbarat=findViewById(R.id.bookbaratid);

        dotsIndicator4 =findViewById(R.id.bbaratdotss);
        viewPager4=findViewById(R.id.pagerbarat);
        viewAdaptor1=new ViewAdaptor1(Barat.this);
        viewPager4.setAdapter(viewAdaptor1);
        dotsIndicator4.setViewPager(viewPager4);

        barattextView.setText("DEAL INCLUDES:\nFOOD\n" +
                "  CATERING\n" +
                "  LIGHTS\n" +
                "  ENTRANCE DECOR\n" +
                "  STAGE DECOR\n" +
                "  GENERATOR\n");

        barattextView1.setText("DEAL PRIZE:    RS 350,000\n" +
                "Per Event\n"
                + "For Details Contact Us:\n");

        barattextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentphone= new Intent(Intent.ACTION_VIEW);
                intentphone.setData(Uri.parse("03308069650"));
                startActivity(intentphone);
            }
        });

        btnbarat.setOnClickListener(new View.OnClickListener() {
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

                            String dealname=baratedit.getText().toString();

                            collectionReference=firestore.collection("OrderDeals");
                            Map<String,Object> userData=new HashMap<>();
                            userData.put("Person Information:",value);
                            userData.put("Deal Name:",dealname);
                            collectionReference.document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .set(userData);

                            AlertDialog.Builder alertDialog=new AlertDialog.Builder(Barat.this);
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
                            Toast.makeText(Barat.this,"Fail",Toast.LENGTH_LONG).show();
                        }
                    }


                });


            }
        });



    }
}