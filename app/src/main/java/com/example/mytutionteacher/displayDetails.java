package com.example.mytutionteacher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class displayDetails extends AppCompatActivity {
    private Button logout;
    private Button update;
    private static final String TAG = "displayDetails";
    public ArrayList<Users> mUsers;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("teachers");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                mUsers.clear();
//                for(DataSnapshot snapshot1:snapshot.getChildren()){
////                    Log.d(TAG, "onDataChange: snapshot values "+snapshot1.getValue().toString());
//                    Users users=snapshot1.getValue(Users.class);
////                    Log.d(TAG, "onDataChange: name "+ users.getName());
////                    Log.d(TAG, "onDataChange: users variable "+users);
//                    mUsers.add(users);
////                    Log.d(TAG, "onDataChange: list "+ mUsers);
////                    Log.d(TAG, "onDataChange: list size "+ mUsers.size());
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_details);




        recyclerView=findViewById(R.id.recycler_view_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUsers=new ArrayList<>();

//        Firebase firebase=new Firebase();
//        mUsers=firebase.readUsers();

        //manually calling data of databases

//        ArrayList<Users> list=new ArrayList<>();

//        mUsers=list;

        //till here

        Log.d(TAG, "onCreate: "+ mUsers);
        Log.d(TAG, "onCreate: "+mUsers.size());
        userAdapter = new UserAdapter(this, mUsers);
//        userAdapter.notifyDataSetChanged();
//        recyclerView.setAdapter(userAdapter);

        readUsers();

        logout=findViewById(R.id.btnLogout);
        update=findViewById(R.id.btnUpdateOrAdd);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase mFirebase=new Firebase();
                mFirebase.logoutUser();
                startActivity(new Intent(displayDetails.this, loginStart.class));
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(displayDetails.this, addUpdateDetails.class));
            }
        });
    }

    private void readUsers() {
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("teachers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUsers.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    Log.d(TAG, "onDataChange: snapshot values "+snapshot1.getValue().toString());
                    Users users=snapshot1.getValue(Users.class);
                    Log.d(TAG, "onDataChange: name "+ users.getName());
                    Log.d(TAG, "onDataChange: users variable "+users);
                    mUsers.add(users);
                    Log.d(TAG, "onDataChange: list "+ mUsers);
                    Log.d(TAG, "onDataChange: list size "+ mUsers.size());
                }
                userAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(userAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}