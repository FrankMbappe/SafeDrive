package com.gm.safedrive.firebase;

import androidx.annotation.NonNull;

import com.gm.safedrive.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserFireDbHelper {
    public static final String PATH = "users";
    FirebaseDatabase database;
    DatabaseReference dbReference;
    List<User> users;

    public interface OnlineDataStatus{
        void DataIsLoaded(List<User> users, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public UserFireDbHelper(){
        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference(PATH);
    }

    public void create(User user){
        dbReference.setValue(user);
    }
    public void update(String id, User user){
        dbReference.child(id).setValue(user);
    }
    public void remove(String id){
        dbReference.child(id).removeValue();
    }
    public void read(final OnlineDataStatus dataStatus){
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot dbObject : snapshot.getChildren()){
                     keys.add(dbObject.getKey());
                     users.add(dbObject.getValue(User.class));
                }
                dataStatus.DataIsLoaded(users, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
