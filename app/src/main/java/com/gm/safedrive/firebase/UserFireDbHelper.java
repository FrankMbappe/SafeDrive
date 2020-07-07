package com.gm.safedrive.firebase;

import androidx.annotation.NonNull;

import com.gm.safedrive.models.User;
import com.google.android.gms.tasks.OnSuccessListener;
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
    DatabaseReference usersDbReference;
    List<User> users;

    public interface OnlineDataStatus{
        void DataIsLoaded(List<User> users, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public UserFireDbHelper(){
        database = FirebaseDatabase.getInstance();
        usersDbReference = database.getReference(PATH);
    }

    public void add(User user, final OnlineDataStatus dataStatus){
        String key = usersDbReference.push().getKey();
        assert key != null;
        usersDbReference.child(key).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }
    public void update(String id, User user){
        usersDbReference.child(id).setValue(user);
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
                     users.add(dbObject.getValue(User.class));
                }
                dataStatus.DataIsLoaded(users, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public boolean userAccountIsOkay(User user, List<User> users){
        // Pourra être modifié si je veux ajouter de nouveaux paramètres qui déterminent qu'un uti-
        // lisateur est valide
        boolean isOkay = true;
        if(users != null && user != null){
            for(User u : users){
                if(user.getEmail().toLowerCase().equals(u.getEmail().toLowerCase())){
                    isOkay = false;
                }
            }
        }
        return isOkay;
    }
    public User getUserByEmailAndPassword(String email, String password, List<User> listOfUsers){
        if(listOfUsers != null && email != null && password != null){
            for(User user : listOfUsers){
                if(user.getEmail().toLowerCase().equals(email.toLowerCase())
                        && user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }
}
