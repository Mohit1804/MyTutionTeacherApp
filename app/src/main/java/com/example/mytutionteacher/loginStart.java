package com.example.mytutionteacher;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginStart extends AppCompatActivity {
    private EditText txtMail, txtPass;
    private Button login, regView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_start);

        txtMail=findViewById(R.id.editTextMailLogin);
        txtPass=findViewById(R.id.editTextPassLogin);
        login=findViewById(R.id.btnLoginLog);
        regView=findViewById(R.id.btnRegView);

        regView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginStart.this,registerStart.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email=txtMail.getText().toString();
                password=txtPass.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(loginStart.this, "Login credentials cannot be empty", Toast.LENGTH_SHORT).show();
                }else{
//                    Firebase mFirebase=new Firebase();
//                    mFirebase.loginUser(email, password);
//                    Toast.makeText(loginStart.this, "You have logged in successfully", Toast.LENGTH_SHORT).show();

                    FirebaseAuth mAuth=FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(loginStart.this, "Successfully logged in.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(loginStart.this, displayDetails.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(loginStart.this, "Error:- "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}