package com.example.mytutionteacher;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private static final String TAG = "UserAdapter";
    private Context mContext;
    private List<Users> mUsers;
    private FirebaseUser firebaseUser;

    public UserAdapter(Context context, List<Users> users) {
        mContext = context;
        mUsers = users;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.user_item, parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        Users user=mUsers.get(position);
        holder.name.setText(user.getName());
        holder.subjects.setText(user.getSubjects());
        holder.phone.setText(user.getPhone());

        Log.d(TAG, "onBindViewHolder: position = "+ position);
        Log.d(TAG, "onBindViewHolder: name "+ user.getName());
        Log.d(TAG, "onBindViewHolder: subjects:- " +user.getSubjects());

        Picasso.get().load(user.getUrl()).placeholder(R.mipmap.ic_profile).into(holder.imageProfile);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+ mUsers.size());
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageProfile;
        public TextView name, subjects, phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageProfile=itemView.findViewById(R.id.image_profile);
            name= itemView.findViewById(R.id.user_fullname);
            phone =itemView.findViewById(R.id.user_phone);
            subjects=itemView.findViewById(R.id.user_subjects);
        }

    }

}
