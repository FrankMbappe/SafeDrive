package com.gm.safedrive.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.gm.safedrive.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserFireDbHelper {
    public static final String TAG = "UserFireDbHelper";
    public static final String PATH = "users";
    FirebaseDatabase database;
    DatabaseReference usersDbReference;
    List<User> users;

    public interface OnlineDataStatus{
        void DataIsLoaded(List<User> users, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public UserFireDbHelper(){
        users = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        usersDbReference = database.getReference(PATH);
    }

    public void add(User user, final OnlineDataStatus dataStatus){
        String key = usersDbReference.push().getKey();
        if(key != null){
            Log.d(TAG, "Key is not null");
            usersDbReference.child(key).setValue(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            dataStatus.DataIsInserted();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "Failed to add user.");
                        }
                    });
        }
    }
    public void update(String key, User user, final OnlineDataStatus dataStatus){
       usersDbReference.child(key).setValue(user)
               .addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                   }
               });
    }
    public void remove(String id){
        usersDbReference.child(id).removeValue();
    }
    public void read(final OnlineDataStatus dataStatus){
        usersDbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot dbObject : snapshot.getChildren()){
                     keys.add(dbObject.getKey());
                     User user = dbObject.getValue(User.class);
                     if(user != null){
                         //user.setId(dbObject.child("uid").getValue(String.class));
                         user.setId(dbObject.getKey());
                         users.add(user);
                     }
                }
                dataStatus.DataIsLoaded(users, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
