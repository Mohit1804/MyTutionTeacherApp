package com.example.mytutionteacher;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class Firebase {
    private static final String TAG = "Firebase";
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private FirebaseFirestore mFirebaseFirestore=FirebaseFirestore.getInstance();
    private DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();

    void registerTeacher(String mail, String pass,  String name, String phone, String sub ){
        mAuth.createUserWithEmailAndPassword(mail, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                HashMap<String, Object> map=new HashMap<>();
                map.put("mail", mail);
                map.put("name", name);
                map.put("occupation", "teacher");
                map.put("phone", phone);
                map.put("subjects", sub);
                map.put("url", "na");
                mFirebaseFirestore.collection("teachers").document(mAuth.getCurrentUser().getUid()).set(map);
                mRootRef.child("teachers").child(mAuth.getCurrentUser().getUid()).updateChildren(map);
            }
        });
    }

    void registerStudents(String mail, String pass,  String name, String phone, String sub ){
        mAuth.createUserWithEmailAndPassword(mail, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                HashMap<String, Object> map=new HashMap<>();
                map.put("mail", mail);
                map.put("name", name);
                map.put("occupation", "student");
                map.put("phone", phone);
                map.put("subjects", sub);
                map.put("url", "na");
                mFirebaseFirestore.collection("students").document(mAuth.getCurrentUser().getUid()).set(map);
                mRootRef.child("students").child(mAuth.getCurrentUser().getUid()).updateChildren(map);
            }
        });
    }
    
    void logoutUser(){
        mAuth.signOut();
    }

    public ArrayList<Users> readUsers() {
        ArrayList<Users> list=new ArrayList<>();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("teachers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    Users users=snapshot.getValue(Users.class);
                    list.add(users);
                    Log.d(TAG, "onDataChange: "+ list);
                    Log.d(TAG, "onDataChange: "+ list.size());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return list;
    }
}
