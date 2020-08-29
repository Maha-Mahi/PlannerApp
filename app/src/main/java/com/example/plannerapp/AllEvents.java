package com.example.plannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllEvents extends AppCompatActivity implements Multichoce.onMultiChoiceListener {

    Spinner spinner1;
    Calendar c;
    DatePickerDialog dp;
    EditText name,category,payment,date,phone1;
    Button btnevent;
    FirebaseFirestore firestore;
    FirebaseAuth mauth;
    CollectionReference collectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);

        mauth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        name=findViewById(R.id.eventname);
        payment=findViewById(R.id.eventpayment);
        btnevent=findViewById(R.id.submitevent);

        category=findViewById(R.id.eventcat);
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment multichoice=new Multichoce();
                multichoice.setCancelable(false);
                multichoice.show(getSupportFragmentManager(),"Choose Category");
            }
        });



        date=findViewById(R.id.eventdate);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c=Calendar.getInstance();
                final int day=c.get(Calendar.DAY_OF_MONTH);
                int month=c.get(Calendar.MONTH);
                int year=c.get(Calendar.YEAR);
                dp=new DatePickerDialog(AllEvents.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(year+"/"+month+"/"+day);
                    }
                }, day,month,year);
                dp.updateDate(day,month,year);
                dp.show();
            }
        });



        btnevent.setOnClickListener(new View.OnClickListener() {
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
                            value.get("Conatct");

                            String event=name.getText().toString();
                            String pay=payment.getText().toString();
                            String date1=date.getText().toString();
                            String cate=category.getText().toString();
                            final ProgressDialog progressDialog=new ProgressDialog(AllEvents.this);
                            progressDialog.setMessage("Saving data...");
                            progressDialog.setCancelable(false);
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.show();
                            collectionReference=firestore.collection("Events");
                            Map<String,Object> userData=new HashMap<>();
                            userData.put("Person Information:",value);
                            userData.put("Event Name",event);
                            userData.put("Category",cate);
                            userData.put("Payment",pay);
                            userData.put("Event Date",date1);
                            collectionReference.document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    progressDialog.dismiss();
                                    AlertDialog.Builder builder=new AlertDialog.Builder( AllEvents.this );
                                    builder.setTitle( "Confirmation" );
                                    builder.setMessage( "Your order is recieved. \nWill reach you soon with the best vendor of the town.\nThankyou!" );
                                    builder.setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    } );
                                    builder.create();
                                    builder.show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AllEvents.this,e.toString(),Toast.LENGTH_LONG).show();
                                }
                            });




                        }else {
                            Toast.makeText(AllEvents.this,"Fail",Toast.LENGTH_LONG).show();
                        }
                    }





                });




            }
        });





        //nav bar displaying activitiet code
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomnavigation);
        bottomNavigationView.setSelectedItemId(R.id.events);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.events:
                        return true;

                    case R.id.vendors:
                        startActivity(new Intent(getApplicationContext(),Vendors.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Moree:
                        startActivity(new Intent(getApplicationContext(),Menu.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder alert=new AlertDialog.Builder( AllEvents.this );
            alert.setMessage( "Are you sure you want to do this" );
            alert.setCancelable( false );
            alert.setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();

                }
            } ).setNegativeButton( "No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            } );
            AlertDialog alertDialog=alert.create();
            alert.show();

        }
        return true;

    }

    @Override
    public void onPositiveButtonClicked(String[] list, ArrayList<String> selecteditemsList) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("\n");
        for (String str : selecteditemsList){
            stringBuilder.append(str+"");
        }
        category.setText(stringBuilder);
    }

    @Override
    public void onNegativeButtonClicked() {
    category.setText("Dialogue cancel");
    }
}