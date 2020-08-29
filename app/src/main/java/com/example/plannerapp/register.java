package com.example.plannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    DatabaseHelper db;
    EditText email,password,phone,name;
    TextView loginpage;
    Button btn1;
    FirebaseAuth mauth;
    FirebaseFirestore firestore;
    CollectionReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mauth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        email=findViewById(R.id.emailid);
        password=findViewById(R.id.regpassid);
        name=findViewById(R.id.userid);
        loginpage=findViewById(R.id.registerlogin);


        loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(register.this,LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
        phone=findViewById(R.id.phone);

        btn1=findViewById(R.id.btnregister);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startUserRegisteration();
            }

        });


//        db=new DatabaseHelper(this);
//        regusertext=findViewById(R.id.userid);
//        regpasstext=findViewById(R.id.regpassid);
//        regbtn=findViewById(R.id.btnregister);
//        confrmpasstext=findViewById(R.id.confirmid);
//        registertext=findViewById(R.id.registerlogin);
//        registertext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent regintent=new Intent(register.this,LoginPage.class);
//                startActivity(regintent);
//            }
//        });
//
//        regbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String user=regusertext.getText().toString().trim();
//                String pwd=regpasstext.getText().toString().trim();
//                String cfrmpwd=confrmpasstext.getText().toString().trim();
//
//
//                if (TextUtils.isEmpty(user)){
//                    Toast.makeText(register.this,"please enter username",Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(pwd)){
//                    Toast.makeText(register.this,"please enter username",Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(cfrmpwd)){
//                    Toast.makeText(register.this,"please enter username",Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                    if (pwd.length()<8){
//                        Toast.makeText(register.this,"Password is too short",Toast.LENGTH_LONG).show();
//                        return;
//                    }
//
//                if(pwd.equals(cfrmpwd)){
//
//                    long val=db.addUser(user,pwd);
//                    if (val > 0) {
//                        Toast.makeText(register.this,"you are registered succesfully",Toast.LENGTH_LONG).show();
//                        Intent movetologin=new Intent(register.this,LoginPage.class);
//                        startActivity(movetologin);
//                    }
//                    else {
//                        Toast.makeText(register.this,"you are unable to register",Toast.LENGTH_LONG).show();
//                    }
//                }
//                else {
//                    Toast.makeText(register.this,"password not matching",Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });
//
}
    private void startUserRegisteration() {

        final String email1=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        final String username=name.getText().toString().trim();
        final String phone1=phone.getText().toString();

        if (email1.isEmpty()){
            Toast.makeText(register.this,"Please enter email",Toast.LENGTH_LONG).show();
        }else if (pass.isEmpty()){
            Toast.makeText(register.this,"Please enter password",Toast.LENGTH_LONG).show();
        }else if (pass.length()<5){
            Toast.makeText(register.this,"Password is too short",Toast.LENGTH_LONG).show();
        }else if (phone1.isEmpty()){
            Toast.makeText(register.this,"Please enter phone",Toast.LENGTH_LONG).show();
        }else if (username.isEmpty()){
            Toast.makeText(register.this,"Please enter username",Toast.LENGTH_LONG).show();
        }
        else{
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Registeringg");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mauth.createUserWithEmailAndPassword(email1,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    FirebaseUser user=authResult.getUser();
                    reference=firestore.collection("User");
                    Map<String,Object> userData=new HashMap<>();
                    userData.put("Username",username);
                    userData.put("Contact",phone1);
                    progressDialog.setMessage("Saving data....");
                    reference.document(user.getUid()).set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            progressDialog.dismiss();
                            Toast.makeText(register.this,"User Registered Successfully",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(register.this,LoginPage.class);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(register.this,"Button unable to register",Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    AlertDialog.Builder builder=new AlertDialog.Builder( register.this );
                    builder.setTitle( "Error" );
                    builder.setMessage( "Failed to Register. \nEmail is already resgistered. \nTry again" );
                    builder.setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    } );
                    builder.create();
                    builder.show();


                }
            });
        }
    }


}