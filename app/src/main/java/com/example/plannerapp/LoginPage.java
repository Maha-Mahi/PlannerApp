package com.example.plannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.net.PasswordAuthentication;

public class LoginPage extends AppCompatActivity {


    EditText name11,pass11;
    Button btn;
    TextView register11;
    FirebaseAuth mauth;
    FirebaseFirestore firestore;
    CollectionReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mauth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        name11 = findViewById(R.id.username11);
        pass11 = findViewById(R.id.password11);
        register11=findViewById(R.id.registerid);
        btn = (Button) findViewById(R.id.btnlogin);



        register11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginPage.this,register.class);
                startActivity(intent);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user1 = name11.getText().toString().trim();
                String pass1 = pass11.getText().toString().trim();

                if (TextUtils.isEmpty(user1)) {
                    Toast.makeText(LoginPage.this, "enter user name please", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(pass1)) {
                    Toast.makeText(LoginPage.this, "enter password please", Toast.LENGTH_LONG).show();
                    return;
                }

                mauth.signInWithEmailAndPassword(user1, pass1).addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(LoginPage.this, "Succesfully! Logged In", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent( LoginPage.this,Home.class );
                            startActivity( intent );
                            finish();
                        } else {
                            Toast.makeText(LoginPage.this, "failedd", Toast.LENGTH_LONG).show();

                        }
                    }
                });


            }
        });

    }
}