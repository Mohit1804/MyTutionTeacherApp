package com.example.mytutionteacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class registerStart extends AppCompatActivity {

    private EditText txtName, txtPass, txtMail, txtPhone, txtSub;
    private Button register2;
    private Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_start);

        txtName=findViewById(R.id.editTextName);
        txtMail=findViewById(R.id.editTextMail);
        txtPass=findViewById(R.id.editTextPassword);
        txtPhone=findViewById(R.id.editTextPhone);
        txtSub=findViewById(R.id.editTextSubjects);
        register2=findViewById(R.id.btn_register2);
        mSwitch=findViewById(R.id.switchTeacher);


        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=txtName.getText().toString();
                String email=txtMail.getText().toString();
                String password=txtPass.getText().toString();
                String phone=txtPhone.getText().toString();
                String subject2 = txtSub.getText().toString();

                if(name.length()==0 || password.length() == 0 || email.length() == 0 ){
                    Toast.makeText(registerStart.this, "Registration credentials cannot be empty", Toast.LENGTH_SHORT).show();
                }else if(password.length()<6){
                    Toast.makeText(registerStart.this, "Password is too short", Toast.LENGTH_SHORT).show();
                }else if(mSwitch.isChecked()){
                    Firebase mFirebase=new Firebase();
                    mFirebase.registerTeacher(email, password, name, phone, subject2);
                    Toast.makeText(registerStart.this, "Successfully registered as teacher.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(registerStart.this, loginStart.class));
                    finish();
                }else{
                    Firebase mFirebase=new Firebase();
                    mFirebase.registerStudents(email, password, name, phone, subject2);
                    Toast.makeText(registerStart.this, "Successfully registered as student.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(registerStart.this, loginStart.class));
                    finish();
                }

            }
        });

    }



}