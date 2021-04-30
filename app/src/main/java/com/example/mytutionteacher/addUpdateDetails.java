package com.example.mytutionteacher;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class addUpdateDetails extends AppCompatActivity {
    private static final String TAG = "addUpdateDetails";
    private static final int IMAGE_REQUEST=2;
    private Uri imageUri;
    private EditText txtName, txtSubjects, txtPhone;
    private Button update,select;
    private Switch upSwitch;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_details);

        txtName=findViewById(R.id.updateName);
        txtSubjects=findViewById(R.id.updatesubjects);
        txtPhone=findViewById(R.id.updatePhone);
        upSwitch=findViewById(R.id.switchUpdate);
        update=findViewById(R.id.btnUpdate);
        mImageView=findViewById(R.id.imageView);
        select=findViewById(R.id.btnSelect);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: image Uri "+imageUri);
                if(imageUri!=null) {
                    uploadImage(imageUri);
                }
                String name=txtName.getText().toString();
                String subjects=txtSubjects.getText().toString();
                String phone=txtPhone.getText().toString();
                FirebaseAuth mAuth=FirebaseAuth.getInstance();
                if(upSwitch.isChecked()){
                    DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();
                    DocumentReference mFirestore=FirebaseFirestore.getInstance().collection("teachers").document(mAuth.getCurrentUser().getUid());
                    if(!TextUtils.isEmpty(name)){
                        HashMap<String, Object>map=new HashMap<>();
                        map.put("name", name);
                        mRootRef.child("teachers").child(mAuth.getCurrentUser().getUid()).updateChildren(map);

                        mFirestore.update("name", name).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(addUpdateDetails.this, "Name updated successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(addUpdateDetails.this,displayDetails.class));
                            }
                        });
                    }
                    if(!TextUtils.isEmpty(subjects)){
                        HashMap<String, Object>map=new HashMap<>();
                        map.put("subjects", subjects);
                        mRootRef.child("teachers").child(mAuth.getCurrentUser().getUid()).updateChildren(map);

                        mFirestore.update("subjects", subjects).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(addUpdateDetails.this, "Subjects updated successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(addUpdateDetails.this,displayDetails.class));
                            }
                        });
                    }
                    if(!TextUtils.isEmpty(phone)){
                        HashMap<String, Object>map=new HashMap<>();
                        map.put("phone", phone);
                        mRootRef.child("teachers").child(mAuth.getCurrentUser().getUid()).updateChildren(map);

                        mFirestore.update("phone", phone).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(addUpdateDetails.this, "Phone Number updated successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(addUpdateDetails.this,displayDetails.class));
                            }
                        });
                    }
                }else{
                    DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();
                    DocumentReference mFirestore=FirebaseFirestore.getInstance().collection("students").document(mAuth.getCurrentUser().getUid());

                    if(!TextUtils.isEmpty(name)){
                        HashMap<String, Object>map=new HashMap<>();
                        map.put("name", name);
                        mRootRef.child("students").child(mAuth.getCurrentUser().getUid()).updateChildren(map);

                        mFirestore.update("name", name).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(addUpdateDetails.this, "Name updated successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(addUpdateDetails.this,displayDetails.class));
                            }
                        });
                    }
                    if(!TextUtils.isEmpty(subjects)){
                        HashMap<String, Object>map=new HashMap<>();
                        map.put("subjects", subjects);
                        mRootRef.child("students").child(mAuth.getCurrentUser().getUid()).updateChildren(map);

                        mFirestore.update("subjects", subjects).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(addUpdateDetails.this, "Subjects updated successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(addUpdateDetails.this,displayDetails.class));
                            }
                        });
                    }
                    if(!TextUtils.isEmpty(phone)){
                        HashMap<String, Object>map=new HashMap<>();
                        map.put("phone", phone);
                        mRootRef.child("students").child(mAuth.getCurrentUser().getUid()).updateChildren(map);

                        mFirestore.update("phone", phone).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(addUpdateDetails.this, "Phone Number updated successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(addUpdateDetails.this,displayDetails.class));
                            }
                        });
                    }
                }
            }
        });
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void openImage(){
        Intent intent=new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_REQUEST && resultCode==RESULT_OK){
            imageUri=data.getData();
            mImageView.setImageURI(imageUri);

//            uploadImage(imageUri);
        }

    }

    private void uploadImage(Uri imageUri) {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        if(imageUri!=null){
            StorageReference fileReference= FirebaseStorage.getInstance().getReference().child("uploads").child(mAuth.getCurrentUser().getUid()).child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            fileReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url=uri.toString();
                            if(upSwitch.isChecked()){
                                HashMap<String, Object>map=new HashMap<>();
                                map.put("url", url);
                                mRootRef.child("teachers").child(mAuth.getCurrentUser().getUid()).updateChildren(map);

                                DocumentReference mFirestore=FirebaseFirestore.getInstance().collection("teachers").document(mAuth.getCurrentUser().getUid());
                                DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();

                                mRootRef.child("teachers").child(mAuth.getCurrentUser().getUid()).updateChildren(map);
                                mFirestore.update("url",url);
                                pd.dismiss();
                                Toast.makeText(addUpdateDetails.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(addUpdateDetails.this, displayDetails.class));
                            }else{
                                HashMap<String, Object>map=new HashMap<>();
                                map.put("url", url);
                                mRootRef.child("students").child(mAuth.getCurrentUser().getUid()).updateChildren(map);
                                DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();

                                mRootRef.child("students").child(mAuth.getCurrentUser().getUid()).updateChildren(map);

                                DocumentReference mFirestore=FirebaseFirestore.getInstance().collection("students").document(mAuth.getCurrentUser().getUid());
                                mFirestore.update("url",url);
                                pd.dismiss();
                                Toast.makeText(addUpdateDetails.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(addUpdateDetails.this, displayDetails.class));
                            }
                        }
                    });
                }
            });
        }
    }
}