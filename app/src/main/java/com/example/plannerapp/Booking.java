package com.example.plannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Booking extends AppCompatActivity {

    FirebaseFirestore firestore;
    DocumentReference documentReference;
    FirebaseAuth mauth;


    EditText email,message;
    Button btnsbmt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        mauth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        email=findViewById(R.id.editemail);
        message=findViewById(R.id.editmessage);

        btnsbmt=findViewById(R.id.btnsubmit);
        btnsbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = firestore.collection("User")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid());

            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if (task.isSuccessful()){
                        DocumentSnapshot document=task.getResult();
                        Map<String,Object> value=document.getData();
                        value.get("Contact");

                        String email1=email.getText().toString().trim();
                        String message1=message.getText().toString().trim();

                        CollectionReference collectionReference = firestore.collection("ReachUsCustomer");
                        Map<String,Object> userData=new HashMap<>();
                        userData.put("Person Information:",value);
                        userData.put("Email Address",email1);
                        userData.put("Message:",message1);

                        collectionReference.document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                AlertDialog.Builder builder=new AlertDialog.Builder(Booking.this);
                                builder.setTitle("Thankyou");
                                builder.setMessage("Your Message is Recieved \n We will reach to your side soon!");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                builder.create();
                                builder.show();


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Booking.this,e.toString(),Toast.LENGTH_LONG).show();
                            }
                        });

                    }else {
                        Toast.makeText(Booking.this,"Button not working. Sorry!",Toast
                                .LENGTH_LONG).show();
                    }


                }
            })    .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Booking.this,"Fail to submit your query. Sorry!",Toast
                    .LENGTH_LONG).show();
                }
            });



            }
        });
    }
}